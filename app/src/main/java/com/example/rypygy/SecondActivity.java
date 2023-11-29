package com.example.rypygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rypygy.models.Character;
import com.google.android.material.snackbar.Snackbar;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SHOP_INVENTORY_KEY = "shop_inv";
    private TextView tvName, tvHp, tvXp, tvLevel, tvAttack, tvDefense, tvMoney;
    private Button btnForest, btnShop, btnInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvName = findViewById(R.id.tvName);
        tvHp = findViewById(R.id.tvHp);
        tvXp = findViewById(R.id.tvXp);
        tvLevel = findViewById(R.id.tvLevel);
        tvAttack = findViewById(R.id.tvAttack);
        tvDefense = findViewById(R.id.tvDefense);
        tvMoney = findViewById(R.id.tvMoney);
        btnForest = findViewById(R.id.btnForest);
        btnShop = findViewById(R.id.btnShop);
        btnInventory = findViewById(R.id.btnInventory);

        btnForest.setOnClickListener(this);
        btnShop.setOnClickListener(this);
        btnInventory.setOnClickListener(this);

        tvName.setText(Character.getName());
        tvHp.setText("HP: " + Character.getCurHP() + "/" + Character.getMaxHP());
        tvXp.setText("XP: " + Character.getXp());
        tvLevel.setText("Level: " + Character.getLevel());
        tvAttack.setText("Strength: " + Character.getStrength());
        tvDefense.setText("Dexterity: " + Character.getDexterity());
        tvMoney.setText("Money: " + Character.getMoney());

        if(Character.getXp() >= 100) {
            startActivity(new Intent(SecondActivity.this, LevelUpActivity.class));
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itSave) {
            Snackbar.make(findViewById(R.id.btnInventory), "Progress saved", Snackbar.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    //TODO: zaimplementować mechanikę encounteru (za pomocą klasy Encounter)

    @Override
    public void onClick(@NonNull View view) {
        switch (getResources().getResourceEntryName(view.getId())) {
            case ("btnForest"):
                startActivity(new Intent(SecondActivity.this, FightActivity.class));
                finish();
                break;
            case "btnShop":
                startActivity(new Intent(SecondActivity.this, ShopInventoryActivity.class).putExtra(SHOP_INVENTORY_KEY, "shop"));
                finish();
                break;
            case "btnInventory":
                startActivity(new Intent(SecondActivity.this, ShopInventoryActivity.class).putExtra(SHOP_INVENTORY_KEY, "inventory"));
                finish();
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}