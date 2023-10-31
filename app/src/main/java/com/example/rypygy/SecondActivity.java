package com.example.rypygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rypygy.models.Character;
import com.example.rypygy.models.Item;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName, tvHp, tvXp, tvLevel, tvAttack, tvDefense, tvMoney;
    private Button btnForest, btnShop;

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

        btnForest.setOnClickListener(this);
        btnShop.setOnClickListener(this);

        tvName.setText(Character.getName());
        tvHp.setText("HP: " + Character.getCurhp() + "/" + Character.getMaxhp());
        tvXp.setText("XP: " + Character.getXp());
        tvLevel.setText("Level: " + Character.getLevel());
        tvAttack.setText("Attack: " + Character.getStrength());
        tvDefense.setText("Defense: " + Character.getDexterity());
        tvMoney.setText("Money: " + Character.getMoney());

        if(Character.getXp() >= 100) {
            startActivity(new Intent(SecondActivity.this, LevelUpActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    public void onClick(@NonNull View view) {
        switch (getResources().getResourceEntryName(view.getId())) {
            case "btnForest":
                startActivity(new Intent(SecondActivity.this, ForestActivity.class));
                finish();
                break;
            case "btnShop":
//                startActivity(new Intent(MainActivity2.this, ShopActivity.class));
                Toast.makeText(this, "IN PROGRESS", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}