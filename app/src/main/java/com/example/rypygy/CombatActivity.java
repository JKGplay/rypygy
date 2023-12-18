package com.example.rypygy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rypygy.enums.EnemyType;
import com.example.rypygy.enums.Location;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Enemy;
import com.example.rypygy.models.Item;
import com.example.rypygy.models.Rnd;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CombatActivity extends AppCompatActivity {

    private TextView tvName, tvHp, tvPlayerInfo, tvEnemyInfo, tvEnemyName, tvEnemyHp;
    private Button btnAttack, btnAbility, btnItem;
    private long mLastClickTime = 0;
    private Enemy enemy;
    private Location location;
    private EnemyType enemyType;
    private List<String> itemNames = new ArrayList<>();
    private int checkedItem;
    private Item.Category checkedItemCategory;
    private enum Action {
        ATTACK,
        USE_ITEM,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        tvName = findViewById(R.id.tvName);
        tvHp = findViewById(R.id.tvHp);
        tvPlayerInfo = findViewById(R.id.tvPlayerInfo);
        tvEnemyInfo = findViewById(R.id.tvEnemyInfo);
        tvEnemyName = findViewById(R.id.tvEnemyName);
        tvEnemyHp = findViewById(R.id.tvEnemyHp);
        btnAttack = findViewById(R.id.btnAttack);
        btnAbility = findViewById(R.id.btnAbility);
        btnItem = findViewById(R.id.btnItem);

        if (getIntent().hasExtra(SecondActivity.ENCOUNTER_LOCATION_KEY)) {
            location = (Location) getIntent().getSerializableExtra(SecondActivity.ENCOUNTER_LOCATION_KEY);
            enemyType = (EnemyType) getIntent().getSerializableExtra(EncounterActivity.ENCOUNTER_COMBAT_ENEMY_TYPE_KEY);
            enemy = new Enemy(enemyType);
        } else {
            startActivity(new Intent(CombatActivity.this, SecondActivity.class));
            finish();
        }

        //na razie nie ma żadnych abilitek
        btnAbility.setVisibility(View.GONE);

        setCombat();

        //TODO: wykrywać gdzie toczy się walka (w jakiej lokacji) oraz uporządkować system walki (osobne funkcje)

        btnAttack.setOnClickListener(v -> characterAction(Action.ATTACK));

        btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                //TODO: zmienić warunek na nieposiadanie potion ani scroll
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

                new MaterialAlertDialogBuilder(CombatActivity.this)
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
                                    if (Character.getCurHP() == Character.getMaxHP()) {
                                        Snackbar.make(view, "You can't use potion at full HP", Snackbar.LENGTH_SHORT).show();
                                    } else {
                                        Snackbar.make(view, "You drank potion and healed " + Math.min(Character.getMaxHP() - Character.getCurHP(), Character.getInventory().get(Character.getIndexOf(itemNames.get(checkedItem))).getAttributes().get(Item.Attribute.HEAL).intValue()) + " HP", Snackbar.LENGTH_SHORT).show();
                                        Character.setCurHP(Math.min(Character.getMaxHP(), Character.getCurHP() + Character.getInventory().get(Character.getIndexOf(itemNames.get(checkedItem))).getAttributes().get(Item.Attribute.HEAL).intValue()));
                                        tvHp.setText("HP: " + Character.getCurHP() + "/" + Character.getMaxHP());
                                        Character.removeItem(Character.getIndexOf(itemNames.get(checkedItem)));
                                    }
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
    }

    private void setCombat() {
        tvPlayerInfo.setText(getResources().getString(R.string.combat_begin_infobox_1, enemy.getName()));
        tvEnemyInfo.setText(getResources().getString(R.string.combat_begin_infobox_2));

        tvName.setText(Character.getName());
        tvHp.setText(getResources().getString(R.string.combat_hp, Character.getCurHP(), Character.getMaxHP()));

        tvEnemyName.setText(enemy.getName());
        tvEnemyHp.setText(getResources().getString(R.string.combat_hp, enemy.getCurHP(), enemy.getMaxHP()));
    }

    private void characterAction(Action action) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (action) {
            case ATTACK:
                characterAttack();
                break;
            case USE_ITEM:
                characterUseItem();
                break;
        }
        if (checkIfWon()) {
            win();
        }
        enemyAttack();
        if (checkIfLost()) {
            lose();
        }
    }

    private void characterAttack() {
        if (Character.toHit(enemy.getAc())) {
            int dmg = Character.getDamage();
            if (Character.getWeapon() != null) {
                dmg += Rnd.rnd(
                        Character.getWeapon().getAttributes().get(Item.Attribute.MIN_DMG).intValue(),
                        Character.getWeapon().getAttributes().get(Item.Attribute.MAX_DMG).intValue()
                );
            }
            enemy.setCurHP(Math.max(enemy.getCurHP() - dmg, 0));
            tvPlayerInfo.setText(getResources().getString(R.string.combat_character_hit, dmg));
            tvEnemyHp.setText(getResources().getString(R.string.combat_hp, enemy.getCurHP(), enemy.getMaxHP()));
        } else {
            tvPlayerInfo.setText(getResources().getString(R.string.combat_character_miss));
        }
    }

    private void characterUseItem() {

    }

    private void enemyAttack() {
        int ac = Character.getAc();
        if (Character.getArmor() != null) {
            ac += Rnd.rnd(
                    Character.getArmor().getAttributes().get(Item.Attribute.MIN_AC).intValue(),
                    Character.getArmor().getAttributes().get(Item.Attribute.MAX_AC).intValue()
            );
        }
        if (enemy.toHit(Character.getLevel(), ac)) {
            int dmg = enemy.damage();
            Character.setCurHP(Math.max(Character.getCurHP() - dmg, 0));
            tvEnemyInfo.setText(getResources().getString(R.string.combat_enemy_hit, enemy.getName(), dmg));
            tvHp.setText(getResources().getString(R.string.combat_hp, enemy.getCurHP(), enemy.getMaxHP()));
        } else {
            tvEnemyInfo.setText(getResources().getString(R.string.combat_enemy_miss, enemy.getName()));
        }
    }

    private void win() {
        Character.addXp(enemy.getXp());
        new MaterialAlertDialogBuilder(CombatActivity.this)
                .setTitle(getResources().getString(R.string.combat_win_title))
                .setMessage(getResources().getString(R.string.combat_win_message, enemy.getXp()))
                .setCancelable(false)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    startActivity(new Intent(CombatActivity.this, SecondActivity.class));
                    finish();
                })
                .show();
    }

    private void lose() {
        new MaterialAlertDialogBuilder(CombatActivity.this)
                .setTitle(getResources().getString(R.string.combat_lose_title))
                .setMessage(getResources().getString(R.string.combat_lose_message))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.combat_lose_button), (dialogInterface, i) -> finish())
                .show();
    }

    private boolean checkIfWon() {
        return enemy.getCurHP() == 0;
    }

    private boolean checkIfLost() {
        return Character.getCurHP() == 0;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(CombatActivity.this, SecondActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}