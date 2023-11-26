package com.example.rypygy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.rypygy.adapter.InventoryAdapter;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Item;

import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Item> items = Character.getInventory();
    private InventoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(InventoryActivity.this));
        adapter = new InventoryAdapter(items, InventoryActivity.this);
        recyclerView.setAdapter(adapter);
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

}