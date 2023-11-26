package com.example.rypygy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.rypygy.adapter.ShopAdapter;
import com.example.rypygy.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Item> items = new ArrayList<>();
    private ShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        for (Item.PredefinedItems pre : Item.PredefinedItems.values()) {
            items.add(new Item(pre, 1));
        }

//        Log.d("pre", items.toString());

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(ShopActivity.this));
        adapter = new ShopAdapter(items, ShopActivity.this);
        recyclerView.setAdapter(adapter);
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
}