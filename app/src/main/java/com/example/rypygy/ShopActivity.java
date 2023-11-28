package com.example.rypygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import com.example.rypygy.adapter.ShopAdapter;
import com.example.rypygy.models.Item;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity implements ChipGroup.OnCheckedStateChangeListener {

    private ChipGroup chipGroup;
    private RecyclerView recyclerView;
    private List<Item> allItems = new ArrayList<>();
    private List<Item> showedItems = new ArrayList<>();
    private ShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        chipGroup = findViewById(R.id.chipGroup);
        recyclerView = findViewById(R.id.recyclerView);
        chipGroup.setOnCheckedStateChangeListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShopActivity.this));
//TODO: połączyć inventory i shop w jedną aktywność (mam nadzieję, że później nie będę musiał tego zmieniać...

        for (Item.PredefinedItems pre : Item.PredefinedItems.values()) {
            allItems.add(new Item(pre, 1));
        }
        showedItems.addAll(allItems);
        adapter = new ShopAdapter(showedItems, ShopActivity.this);
        setTitle("Wrath of Kunczka - Shop");
//
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
        refreshList(checkedIds);
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(ShopActivity.this, SecondActivity.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ShopActivity.this, SecondActivity.class));
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