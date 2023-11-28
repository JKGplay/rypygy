package com.example.rypygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.rypygy.adapter.InventoryAdapter;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Item;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity implements ChipGroup.OnCheckedStateChangeListener {

    private ChipGroup chipGroup;
    private RecyclerView recyclerView;
    private List<Item> allItems = new ArrayList<>();
    private List<Item> showedItems = new ArrayList<>();
    private InventoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        chipGroup = findViewById(R.id.chipGroup);
        recyclerView = findViewById(R.id.recyclerView);
        chipGroup.setOnCheckedStateChangeListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(InventoryActivity.this));
//TODO: połączyć inventory i shop w jedną aktywność (mam nadzieję, że później nie będę musiał tego zmieniać...
        allItems.addAll(Character.getInventory());
        showedItems.addAll(Character.getInventory());
        adapter = new InventoryAdapter(showedItems, InventoryActivity.this);
        setTitle("Wrath of Kunczka - Inventory");
//
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
        refreshList(checkedIds);
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(InventoryActivity.this, SecondActivity.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(InventoryActivity.this, SecondActivity.class));
        finish();
//        super.onBackPressed();
    }

    private void refreshList(List<Integer> checkedIds) {
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
        adapter.notifyDataSetChanged();
    }
}