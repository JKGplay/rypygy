package com.example.rypygy.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rypygy.R;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Item;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ItemViewHolder> {

    private List<Item> items;
    private Context context;

    public ShopAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
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
        private TextView tvItemName, tvItemDesc, tvItemPrice;
        private Button btnBuy;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemDesc = itemView.findViewById(R.id.tvItemDesc);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            btnBuy = itemView.findViewById(R.id.btnBuy);
        }

        public void bind(Item item) {
            tvItemName.setText(item.getName());
            tvItemPrice.setText("Price: " + item.getPrice());

            switch (item.getCategory()) {
                case WEAPON:
                    tvItemDesc.setText("Damage: " + item.getAttributes().get(Item.Attribute.MIN_DMG).intValue() + " - " + item.getAttributes().get(Item.Attribute.MAX_DMG).intValue());
                    break;
                case ARMOR:
                    tvItemDesc.setText("Armor Class: " + item.getAttributes().get(Item.Attribute.MIN_AC).intValue() + " - " + item.getAttributes().get(Item.Attribute.MAX_AC).intValue());
                    break;
                case POTION:
                    tvItemDesc.setText("Heal: " + item.getAttributes().get(Item.Attribute.HEAL).intValue() + "hp");
                    break;
                case SCROLL:
                    tvItemDesc.setText("Magic Scroll");
                    break;
            }

            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Character.getMoney() < item.getPrice()) {
                        Snackbar.make(view, "You don't have enough money", Snackbar.LENGTH_SHORT).show();
                    } else {
                        new MaterialAlertDialogBuilder(context)
                                .setMessage("Are you sure you want to buy " + item.getName() + "?")
                                .setCancelable(true)
                                .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Snackbar.make(view, "You bought " + item.getName(), Snackbar.LENGTH_SHORT).show();
                                        Log.d("amount", String.valueOf(item.getAmount()));
                                        Log.d("item", item.toString());
                                        Character.removeMoney(item.getPrice());
                                        Character.addItem(item, 1);
                                    }
                                })
                                .setNegativeButton("Cancel", null)
                                .show();
                    }
                }
            });
        }
    }
}
