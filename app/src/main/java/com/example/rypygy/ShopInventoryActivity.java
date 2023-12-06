package com.example.rypygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.rypygy.adapter.InventoryAdapter;
import com.example.rypygy.adapter.ShopAdapter;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Item;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopInventoryActivity extends AppCompatActivity implements ChipGroup.OnCheckedStateChangeListener {

    private ChipGroup chipGroup;
    private RecyclerView recyclerView;
    private List<Item> allItems = new ArrayList<>();
    private List<Item> showedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_inventory);

        chipGroup = findViewById(R.id.chipGroup);
        recyclerView = findViewById(R.id.recyclerView);
        chipGroup.setOnCheckedStateChangeListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShopInventoryActivity.this));

        if (getIntent().hasExtra(SecondActivity.SHOP_INVENTORY_KEY)) {
            if (Objects.equals(getIntent().getSerializableExtra(SecondActivity.SHOP_INVENTORY_KEY), SecondActivity.ShopInv.SHOP)) {
                for (Item.PredefinedItems pre : Item.PredefinedItems.values()) {
                    allItems.add(new Item(pre, 1));
                }
                showedItems.addAll(allItems);
                ShopAdapter shopAdapter = new ShopAdapter(showedItems, ShopInventoryActivity.this);
                setTitle("Wrath of Kunczka - Shop");
                recyclerView.setAdapter(shopAdapter);
            } else if (Objects.equals(getIntent().getSerializableExtra(SecondActivity.SHOP_INVENTORY_KEY), SecondActivity.ShopInv.INVENTORY)) {
                allItems.addAll(Character.getInventory());
                showedItems.addAll(Character.getInventory());
                InventoryAdapter inventoryAdapter = new InventoryAdapter(showedItems, ShopInventoryActivity.this);
                setTitle("Wrath of Kunczka - Inventory");
                recyclerView.setAdapter(inventoryAdapter);
            }
        }
    }

    @Override
    public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
        refreshList(checkedIds);
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(ShopInventoryActivity.this, SecondActivity.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ShopInventoryActivity.this, SecondActivity.class));
        finish();
//        super.onBackPressed();
    }

    private void refreshList(@NonNull List<Integer> checkedIds) {
        showedItems.clear();
        if (checkedIds.contains(R.id.chipWeapon)) {
            for (Item item : allItems) {
                if (item.getCategory().equals(Item.Category.WEAPON)) {
                    showedItems.add(item);
                }
            }
        }
        if (checkedIds.contains(R.id.chipArmor)) {
            for (Item item : allItems) {
                if (item.getCategory().equals(Item.Category.ARMOR)) {
                    showedItems.add(item);
                }
            }
        }
        if (checkedIds.contains(R.id.chipPotion)) {
            for (Item item : allItems) {
                if (item.getCategory().equals(Item.Category.POTION)) {
                    showedItems.add(item);
                }
            }
        }
        if (checkedIds.contains(R.id.chipScroll)) {
            for (Item item : allItems) {
                if (item.getCategory().equals(Item.Category.SCROLL)) {
                    showedItems.add(item);
                }
            }
        }
        if (!checkedIds.contains(R.id.chipWeapon) && !checkedIds.contains(R.id.chipArmor) && !checkedIds.contains(R.id.chipPotion) && !checkedIds.contains(R.id.chipScroll) && showedItems.isEmpty()) {
            showedItems.addAll(allItems);
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}