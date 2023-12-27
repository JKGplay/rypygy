package com.example.rypygy.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rypygy.R;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Inventory;
import com.example.rypygy.models.Item;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ItemViewHolder> {

    private List<Item> items;
    private Context context;

    public InventoryAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemName, tvItemAmount, tvItemDesc;
        private ImageButton btnDelete;
        private Button btnAction;

        ItemViewHolder(View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemAmount = itemView.findViewById(R.id.tvItemAmount);
            tvItemDesc = itemView.findViewById(R.id.tvItemDesc);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnAction = itemView.findViewById(R.id.btnAction);
        }

        public void bind(@NonNull Item item) {
            tvItemName.setText(item.getName());
            tvItemAmount.setText("x" + item.getAmount());

            switch (item.getCategory()) {
                case WEAPON:
                    tvItemDesc.setText("Damage: " + item.getAttributes().get(Item.Attribute.MIN_DMG).intValue() + "-" + item.getAttributes().get(Item.Attribute.MAX_DMG).intValue());
                    if (Inventory.isEquipped(item)) {
                        btnAction.setText("Equipped");
                        btnAction.setEnabled(false);
                    } else {
                        btnAction.setText("Equip");
                        btnAction.setEnabled(true);
                    }
                    break;
                case ARMOR:
                    tvItemDesc.setText("Armor Class: " + item.getAttributes().get(Item.Attribute.MIN_AC).intValue() + "-" + item.getAttributes().get(Item.Attribute.MAX_AC).intValue());
                    btnAction.setText("Equip");
                    if (Inventory.isEquipped(item)) {
                        btnAction.setText("Equipped");
                        btnAction.setEnabled(false);
                    } else {
                        btnAction.setText("Equip");
                        btnAction.setEnabled(true);
                    }
                    break;
                case POTION:
                    tvItemDesc.setText("Heal: " + item.getAttributes().get(Item.Attribute.HEAL).intValue() + "hp");
                    btnAction.setText("Use");
                    btnAction.setEnabled(true);
                    break;
                case SCROLL:
                    tvItemDesc.setText("Magic Scroll");
                    btnAction.setVisibility(View.GONE);
                    break;
            }

            btnAction.setOnClickListener(v -> {
                switch (item.getCategory()) {
                    case WEAPON:
                    case ARMOR:
                        Snackbar.make(v, item.getName() + " equipped", Snackbar.LENGTH_SHORT).show();
                        Inventory.equipItem(item);
                        notifyDataSetChanged();
                        break;
                    case POTION:
                        if (Character.getCurHP() == Character.getMaxHP()) {
                            Snackbar.make(v, "You can't use potion at full HP", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Snackbar.make(v, "You drank potion and healed " + Math.min(Character.getMaxHP() - Character.getCurHP(), item.getAttributes().get(Item.Attribute.HEAL).intValue()) + " HP", Snackbar.LENGTH_SHORT).show();
                            Character.setCurHP(Math.min(Character.getMaxHP(), Character.getCurHP() + item.getAttributes().get(Item.Attribute.HEAL).intValue()));

                            List<Item> temp = new ArrayList<>(items);
                            Inventory.removeItem(Inventory.getInventory().get(getAdapterPosition()));

                            if (temp.size() > items.size()) {
                                notifyItemRemoved(getAdapterPosition());
                                notifyItemRangeChanged(getAdapterPosition(), items.size());
                            } else {
                                tvItemAmount.setText("x" + item.getAmount());
                            }
                        }
                        break;
                }

            });

            btnDelete.setOnClickListener(v -> new MaterialAlertDialogBuilder(context)
                    .setMessage("Are you sure you want to throw away " + item.getName() + "?")
                    .setCancelable(true)
                    .setPositiveButton("Delete", (dialogInterface, i) -> {
                        List<Item> temp = new ArrayList<>(items);
                        Snackbar.make(v, "You threw " + item.getName() + " away", Snackbar.LENGTH_SHORT).show();
                        if (Inventory.isEquipped(item)) {
                            Inventory.unEquipItem(item.getCategory());
                        }
                        Inventory.removeItem(Inventory.getInventory().get(getAdapterPosition()));
//                                    notifyDataSetChanged();
                        if (temp.size() > Inventory.getInventory().size()) {
                            items.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            notifyItemRangeChanged(getAdapterPosition(), items.size());
                        } else {
                            tvItemAmount.setText("x" + item.getAmount());
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show());
        }
    }
}
