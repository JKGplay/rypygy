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

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName, tvHp, tvXp, tvLevel, tvAttack, tvDefense, tvMoney;
    private Button btnForest, btnShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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

        Character character = new Character();

        tvName.setText(character.getName());
        tvHp.setText("HP: " + character.getCurhp() + "/" + character.getMaxhp());
        tvXp.setText("XP: " + character.getXp());
        tvLevel.setText("Level: " + character.getLevel());
        tvAttack.setText("Attack: " + character.getStrength());
        tvDefense.setText("Defense: " + character.getDexterity());
        tvMoney.setText("Money: " + character.getMoney());

        if(character.getXp() >= 100) {
            startActivity(new Intent(MainActivity2.this, LevelUpActivity.class));
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
                startActivity(new Intent(MainActivity2.this, ForestActivity.class));
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