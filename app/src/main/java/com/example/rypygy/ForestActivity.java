package com.example.rypygy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rypygy.models.Character;
import com.example.rypygy.models.Item;
import com.example.rypygy.models.Rnd;
import com.example.rypygy.models.enemies.FirstYear;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ForestActivity extends AppCompatActivity {

    private TextView tvName, tvHp, tvPlayerInfo, tvEnemyInfo, tvEnemyName, tvEnemyHp;
    private Button btnAttack, btnDefend, btnItem;

    private long mLastClickTime = 0;

    private List<String> itemNames = new ArrayList<>();
    private int checkedItem;
    private Item.Category checkedItemCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest);

        tvName = findViewById(R.id.tvName);
        tvHp = findViewById(R.id.tvHp);
        tvPlayerInfo = findViewById(R.id.tvPlayerInfo);
        tvEnemyInfo = findViewById(R.id.tvEnemyInfo);
        tvEnemyName = findViewById(R.id.tvEnemyName);
        tvEnemyHp = findViewById(R.id.tvEnemyHp);
        btnAttack = findViewById(R.id.btnAttack);
        btnDefend = findViewById(R.id.btnDefend);
        btnItem = findViewById(R.id.btnItem);

        FirstYear monster = new FirstYear();

        btnDefend.setVisibility(View.GONE);

        tvPlayerInfo.setText("You encounter a First-Year!");
        tvEnemyInfo.setText("Prepare to attack!");

        tvName.setText(Character.getName());
        tvHp.setText("HP: " + Character.getCurhp() + "/" + Character.getMaxhp());

        tvEnemyName.setText(monster.getName());
        tvEnemyHp.setText("HP: " + monster.getCurHP() + "/" + monster.getMaxHP());

        btnAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                if (Character.toHit(monster.getAc())) {
                    /*
                        Ternary operator checks if Character has a weapon and if weapon has MinDMG and MaxDMG attribute.
                        If yes then dmg = Character.getDamage() + rnd(min, max);
                        If not then dmg = Character.getDamage();
                     */
                    int dmg = (Character.getWeapon() != null && Character.getWeapon().getAttributes().containsKey(Item.Attribute.MinDMG) && Character.getWeapon().getAttributes().containsKey(Item.Attribute.MaxDMG)) ? Character.getDamage() + Rnd.rnd(Character.getWeapon().getAttributes().get(Item.Attribute.MinDMG).intValue(), Character.getWeapon().getAttributes().get(Item.Attribute.MaxDMG).intValue()) : Character.getDamage();
                    tvPlayerInfo.setText("You attacked and dealt " + dmg + " damage!");
                    monster.setCurHP(monster.getCurHP() - dmg);
                    if (monster.getCurHP() < 0) {
                        monster.setCurHP(0);
                    }
                    tvEnemyHp.setText("HP: " + monster.getCurHP() + "/" + monster.getMaxHP());
                } else {
                    tvPlayerInfo.setText("You attacked and missed!");
                }

                if (monster.getCurHP() == 0) {
                    Character.setXp(Character.getXp() + 10);
                    new MaterialAlertDialogBuilder(ForestActivity.this)
                            .setTitle("You won!")
                            .setMessage("You get 10 xp")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (Character.getXp() >= 100) {
                                        startActivity(new Intent(ForestActivity.this, LevelUpActivity.class));
                                        finish();
                                    } else {
                                        startActivity(new Intent(ForestActivity.this, SecondActivity.class));
                                        finish();
                                    }
                                }
                            })
                            .show();

                } else {
                    /*
                        Character AC is set as given:
                        Ternary operator checks if Character has a armor and if armor has MinAC and MaxAC attribute.
                        If yes then ac = Character.getAc() + rnd(min, max);
                        If not then ac = Character.getAc();
                     */
                    if (monster.toHit(Character.getLevel(), ((Character.getArmor() != null && Character.getArmor().getAttributes().containsKey(Item.Attribute.MinAC) && Character.getArmor().getAttributes().containsKey(Item.Attribute.MaxAC)) ? Character.getAc() + Rnd.rnd(Character.getArmor().getAttributes().get(Item.Attribute.MinAC).intValue(), Character.getArmor().getAttributes().get(Item.Attribute.MaxAC).intValue()) : Character.getAc()))) {
                        int dmg = monster.damage();
                        tvEnemyInfo.setText(monster.getName() + " attacked you and dealt " + dmg + " damage!");
                        Character.setCurhp(Character.getCurhp() - dmg);
                        tvHp.setText("HP: " + Character.getCurhp() + "/" + Character.getMaxhp());
                    } else {
                        tvEnemyInfo.setText(monster.getName() + " attacked you and missed!");
                    }
                }
            }
        });

        btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                if (Character.getInventory().size() == 2) {
//                    Toast.makeText(ForestActivity.this, "You don't have any items to use", Toast.LENGTH_SHORT).show();
                    Snackbar.make(view, "You don't have any items to use", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                checkedItem = -1;
                checkedItemCategory = null;
                itemNames.clear();
                for (Item item: Character.getInventory()) {
                    if (item.getCategory() == Item.Category.POTION || item.getCategory() == Item.Category.SCROLL) {
                        itemNames.add(item.getName());
                    }
                }

                new MaterialAlertDialogBuilder(ForestActivity.this)
                        .setTitle("Inventory")
                        .setSingleChoiceItems(itemNames.toArray(new String[0]), checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                checkedItem = which;
                                checkedItemCategory = Character.getInventory().get(Character.getIndexOf(itemNames.get(checkedItem))).getCategory();
                            }
                        })
                        .setPositiveButton("Use", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (checkedItem == -1) {
                                    return;
                                }

                                if (checkedItemCategory == Item.Category.POTION) {
                                    if (Character.getCurhp() == Character.getMaxhp()) {
                                        Toast.makeText(ForestActivity.this, "You can't use potion at full HP", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        Character.setCurhp(Math.min(70, Character.getCurhp() + Character.getInventory().get(Character.getIndexOf(itemNames.get(checkedItem))).heal()));
                                        tvHp.setText("HP: " + Character.getCurhp() + "/" + Character.getMaxhp());
                                        Character.removeItem(Character.getIndexOf(itemNames.get(checkedItem)));
                                        Log.d("uzyto potki", itemNames.get(checkedItem));
                                    }
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();


//                if (Character.getCurhp() == Character.getMaxhp()) {
//                    Toast.makeText(ForestActivity.this, "You can't use potion at full HP", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (Character.getIndexOf("Small Potion") == -1) {
//
//                }
//                Character.setCurhp(Math.min(70, Character.getCurhp() + Character.getInventory().get(Character.getIndexOf("Small Potion")).heal()));
//                tvHp.setText("HP: " + Character.getCurhp() + "/" + Character.getMaxhp());
//                Character.removeItem(Character.getIndexOf("Small Potion"));
            }
        });
    }

    public static void characterAttack() {

    }

    public static void characterDefend() {

    }

    public static void characterUseItem() {

    }

    public static void enemyAttack() {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}