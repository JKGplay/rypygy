package com.example.rypygy;

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

import com.example.rypygy.models.Character;
import com.example.rypygy.models.Item;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> items;
    private Context context;

    ItemAdapter(List<Item> items, Context context) {
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

    class ItemViewHolder extends RecyclerView.ViewHolder {
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

        public void bind(Item item) {
            tvItemName.setText(item.getName());
            tvItemAmount.setText("x" + item.getAmount());

            switch (item.getCategory()) {
                case WEAPON:
                    tvItemDesc.setText("Damage: " + item.getAttributes().get(Item.Attribute.MinDMG).intValue() + "-" + item.getAttributes().get(Item.Attribute.MaxDMG).intValue());
                    if (item.equals(Character.getWeapon())) {
                        btnAction.setText("Equipped");
                        btnAction.setEnabled(false);
                    } else {
                        btnAction.setText("Equip");
                        btnAction.setEnabled(true);
                    }
                    break;
                case ARMOR:
                    tvItemDesc.setText("Armor Class: " + item.getAttributes().get(Item.Attribute.MinAC).intValue() + "-" + item.getAttributes().get(Item.Attribute.MaxAC).intValue());
                    btnAction.setText("Equip");
                    if (item.equals(Character.getArmor())) {
                        btnAction.setText("Equipped");
                        btnAction.setEnabled(false);
                    } else {
                        btnAction.setText("Equip");
                        btnAction.setEnabled(true);
                    }
                    break;
                case POTION:
                    tvItemDesc.setText("Heal: " + item.heal() + "hp");
                    btnAction.setText("Use");
                    btnAction.setEnabled(true);
                    break;
                case SCROLL:
                    tvItemDesc.setText("Magic Scroll");
                    btnAction.setVisibility(View.GONE);
                    break;
            }

            btnAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (item.getCategory()) {
                        case WEAPON:
                            Snackbar.make(view, item.getName() + " equipped", Snackbar.LENGTH_SHORT).show();
                            Character.setWeapon(item);
                            break;
                        case ARMOR:
                            Snackbar.make(view, item.getName() + " equipped", Snackbar.LENGTH_SHORT).show();
                            Character.setArmor(item);
                            break;
                        case POTION:
                            if (Character.getCurHP() == Character.getMaxHP()) {
                                Snackbar.make(view, "You can't use potion at full HP", Snackbar.LENGTH_SHORT).show();
                            } else {
                                Snackbar.make(view, "You drank potion and healed " + Math.min(Character.getMaxHP() - Character.getCurHP(), item.heal()) + " HP", Snackbar.LENGTH_SHORT).show();
                                Character.setCurHP(Math.min(Character.getMaxHP(), Character.getCurHP() + item.heal()));

//                                List<Item> temp = new ArrayList<>(items);
                                Character.removeItem(getAdapterPosition());

//                                if (temp.size() > items.size()) {
//                                    notifyItemRemoved(getAdapterPosition());
//                                    notifyItemRangeChanged(getAdapterPosition(), items.size());
//                                } else {
//                                    tvItemAmount.setText("x" + item.getAmount());
//                                }
                            }
                            break;
                    }
                    notifyDataSetChanged();
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new MaterialAlertDialogBuilder(context)
                            .setMessage("Are you sure you want to throw away " + item.getName() + "?")
                            .setCancelable(true)
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    List<Item> temp = new ArrayList<>(items);
                                    Snackbar.make(view, "You threw " + item.getName() + " away", Snackbar.LENGTH_SHORT).show();
                                    if(item.equals(Character.getWeapon())) {
                                        Character.setWeapon(null);
                                    }
                                    if (item.equals(Character.getArmor())) {
                                        Character.setArmor(null);
                                    }
                                    Character.removeItem(getAdapterPosition());
                                    notifyDataSetChanged();
//                                    if (temp.size() > items.size()) {
//                                        notifyItemRemoved(getAdapterPosition());
//                                        notifyItemRangeChanged(getAdapterPosition(), items.size());
//                                    } else {
//                                        tvItemAmount.setText("x" + item.getAmount());
//                                    }
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                }
            });
        }
    }


}
