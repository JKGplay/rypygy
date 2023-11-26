package com.example.rypygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rypygy.models.Character;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

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

//        Log.d("vit", "vitality: " + Character.getVitality());
//        Log.d("hp", "maxhp: " + Character.getMaxHP());
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    //TODO: zaimplementować mechanikę encounteru (za pomocą klasy Encounter)

    @Override
    public void onClick(@NonNull View view) {
        switch (getResources().getResourceEntryName(view.getId())) {
            case "btnForest":
                startActivity(new Intent(SecondActivity.this, FightActivity.class));
                finish();
                break;
            case "btnShop":
                startActivity(new Intent(SecondActivity.this, ShopActivity.class));
                finish();
                break;
            case "btnInventory":
                startActivity(new Intent(SecondActivity.this, InventoryActivity.class));
                finish();
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}