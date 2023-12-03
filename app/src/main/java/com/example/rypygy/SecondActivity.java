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
import com.example.rypygy.models.Encounter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SHOP_INVENTORY_KEY = "shop_inv";
    public static final String ENCOUNTER_LOCATION_KEY = "encounter_location";
    public static final String ENCOUNTER_TYPE_KEY = "encounter_type";
    private TextView tvName, tvHp, tvXp, tvLevel, tvAttack, tvDefense, tvMoney;
    private Button btnForest, btnShop, btnInventory;
    private List<Button> buttons = new ArrayList<>();
    private enum ShopInv {
        SHOP,
        INVENTORY
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        buttons.add(findViewById(R.id.btnForest));
        buttons.add(findViewById(R.id.btnGarages));
        buttons.add(findViewById(R.id.btnToilets));
        buttons.add(findViewById(R.id.btnComputerLab));
        buttons.add(findViewById(R.id.btnDormitory));
        buttons.add(findViewById(R.id.btnCourtyard));
        buttons.add(findViewById(R.id.btnKaczyce));

        for (int i = 0; i < buttons.size(); i++) {
            if (i < Character.getLevel()) {
                buttons.get(i).setOnClickListener(this);
            } else {
                buttons.get(i).setVisibility(View.GONE);
            }
        }

        tvName = findViewById(R.id.tvName);
        tvHp = findViewById(R.id.tvHp);
        tvXp = findViewById(R.id.tvXp);
        tvLevel = findViewById(R.id.tvLevel);
        tvAttack = findViewById(R.id.tvAttack);
        tvDefense = findViewById(R.id.tvDefense);
        tvMoney = findViewById(R.id.tvMoney);
        btnShop = findViewById(R.id.btnShop);
        btnInventory = findViewById(R.id.btnInventory);

        btnShop.setOnClickListener(view -> {
            shopInv(ShopInv.SHOP);
        });
        btnInventory.setOnClickListener(view -> {
            shopInv(ShopInv.INVENTORY);
        });

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

    public void shopInv(@NonNull ShopInv shopInv) {
        switch (shopInv) {
            case SHOP:
                startActivity(new Intent(SecondActivity.this, ShopInventoryActivity.class).putExtra(SHOP_INVENTORY_KEY, "shop"));
                break;
            case INVENTORY:
                startActivity(new Intent(SecondActivity.this, ShopInventoryActivity.class).putExtra(SHOP_INVENTORY_KEY, "inventory"));
                break;
        }
        finish();
    }


    //TODO: zaimplementować mechanikę encounteru (za pomocą klasy Encounter)

    @Override
    public void onClick(@NonNull View view) {
        for (Button btn : buttons) {
            if (btn.equals(view)) {
                startActivity(new Intent(SecondActivity.this, EncounterActivity.class).putExtra(ENCOUNTER_LOCATION_KEY, btn.getText().toString().toLowerCase()));
                finish();
            }
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
}