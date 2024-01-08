package com.example.rypygy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rypygy.enums.EnemyType;
import com.example.rypygy.enums.Location;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Enemy;
import com.example.rypygy.models.Inventory;
import com.example.rypygy.models.Item;
import com.example.rypygy.models.Rnd;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CombatActivity extends AppCompatActivity {

    private TextView tvName, tvHp, tvPlayerInfo, tvEnemyInfo, tvEnemyName, tvEnemyHp;
    private Button btnAttack, btnAbility, btnItem;
    private ImageView ivAbility;
    private long mLastClickTime = 0;
    private Enemy enemy;
    private Location location;
    private EnemyType enemyType;
    private List<Item> listOfUsableItems = new ArrayList<>();
    private Item checkedItem;
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
        ivAbility = findViewById(R.id.ivAbility);

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
        ivAbility.setVisibility(View.GONE);

        setCombat();

        btnAttack.setOnClickListener(v -> characterAction(Action.ATTACK, v));

        btnItem.setOnClickListener(v -> characterAction(Action.USE_ITEM, v));
    }

    private void setCombat() {
        tvPlayerInfo.setText(getResources().getString(R.string.combat_begin_infobox_1, enemy.getName()));
        tvEnemyInfo.setText(getResources().getString(R.string.combat_begin_infobox_2));

        tvName.setText(Character.getName());
        tvHp.setText(getResources().getString(R.string.combat_hp, Character.getCurHP(), Character.getMaxHP()));

        tvEnemyName.setText(enemy.getName());
        tvEnemyHp.setText(getResources().getString(R.string.combat_hp, enemy.getCurHP(), enemy.getMaxHP()));
    }

    private void characterAction(Action action, View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (action) {
            case ATTACK:
                characterAttack();
                break;
            case USE_ITEM:
                characterUseItem(view);
                break;
        }
    }

    private void characterAttack() {
        if (Character.toHit(enemy.getAc())) {
            int dmg = Character.getDamage();
            Log.d("atak", Inventory.getInventory().toString());
            if (Inventory.hasEquipped(Item.Category.WEAPON)) {
                Log.d("ma cos equipnete", Inventory.getEquipped(Item.Category.WEAPON).toString());
                dmg += Rnd.rnd(
                        Inventory.getEquipped(Item.Category.WEAPON).getAttributes().get(Item.Attribute.MIN_DMG).intValue(),
                        Inventory.getEquipped(Item.Category.WEAPON).getAttributes().get(Item.Attribute.MAX_DMG).intValue()
                );
            } else {
                Log.d("ni ma cos equipnete", Inventory.getEquipped(Item.Category.WEAPON).toString());
            }
            enemy.setCurHP(Math.max(enemy.getCurHP() - dmg, 0));
            tvPlayerInfo.setText(getResources().getString(R.string.combat_character_hit, dmg));
            tvEnemyHp.setText(getResources().getString(R.string.combat_hp, enemy.getCurHP(), enemy.getMaxHP()));
        } else {
            tvPlayerInfo.setText(getResources().getString(R.string.combat_character_miss));
        }
        actionMade();
    }

    private void characterUseItem(View view) {
        listOfUsableItems.clear();
        listOfUsableItems = Inventory.listOfUsableItems();
        if (listOfUsableItems.size() == 0) {
            Snackbar.make(view, getResources().getString(R.string.combat_use_nothing), Snackbar.LENGTH_SHORT).show();
            return;
        }
        List<String> listOfNamesOfUsableItems = Inventory.listOfNamesOfUsableItems();
        checkedItem = listOfUsableItems.get(0);
        new MaterialAlertDialogBuilder(CombatActivity.this)
                .setTitle(getResources().getString(R.string.btn_inventory))
                .setSingleChoiceItems(listOfNamesOfUsableItems.toArray(new String[0]), 0, (dialogInterface, which) -> {
                    checkedItem = listOfUsableItems.get(which);
                })
                .setPositiveButton(getResources().getString(R.string.use), (dialogInterface, i) -> {
                    if (checkedItem.getCategory().equals(Item.Category.POTION)) {
                        if (Character.getCurHP() == Character.getMaxHP()) {
                            Snackbar.make(view, getResources().getString(R.string.combat_use_potion_full), Snackbar.LENGTH_SHORT).show();
                        } else {
                            int heal = Math.min(
                                    Character.getMaxHP() - Character.getCurHP(),
                                    checkedItem.getAttributes().get(Item.Attribute.HEAL).intValue()
                            );
                            Snackbar.make(view, getResources().getString(R.string.combat_use_potion, heal), Snackbar.LENGTH_SHORT).show();
                            Character.setCurHP(Character.getCurHP() + heal);
                            tvHp.setText(getResources().getString(R.string.combat_hp, Character.getCurHP(), Character.getMaxHP()));
                            Inventory.removeItem(checkedItem);
                            actionMade();
                        }
                    } else if (checkedItem.getCategory().equals(Item.Category.SCROLL)) {
                        new MaterialAlertDialogBuilder(CombatActivity.this)
                                .setTitle(getResources().getString(R.string.combat_use_scroll_title))
                                .setMessage(getResources().getString(R.string.combat_use_scroll_message))
                                .setCancelable(false)
                                .setPositiveButton("OK", (dialog, which) -> {
                                    startActivity(new Intent(CombatActivity.this, SecondActivity.class));
                                    finish();
                                })
                                .show();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.cancel), null)
                .show();
    }

    private void enemyAttack() {
        int ac = Character.getAc();
        if (Inventory.hasEquipped(Item.Category.ARMOR)) {
            ac += Rnd.rnd(
                    Inventory.getEquipped(Item.Category.ARMOR).getAttributes().get(Item.Attribute.MIN_AC).intValue(),
                    Inventory.getEquipped(Item.Category.ARMOR).getAttributes().get(Item.Attribute.MAX_AC).intValue()
            );
        }
        if (enemy.toHit(Character.getLevel(), ac)) {
            int dmg = enemy.damage();
            Character.setCurHP(Math.max(Character.getCurHP() - dmg, 0));
            tvEnemyInfo.setText(getResources().getString(R.string.combat_enemy_hit, enemy.getName(), dmg));
            tvHp.setText(getResources().getString(R.string.combat_hp, Character.getCurHP(), Character.getMaxHP()));
        } else {
            tvEnemyInfo.setText(getResources().getString(R.string.combat_enemy_miss, enemy.getName()));
        }
    }

    private void actionMade() {
        if (checkIfWon()) {
            win();
        }
        enemyAttack();
        if (checkIfLost()) {
            lose();
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
                .setPositiveButton(getResources().getString(R.string.combat_lose_button), (dialogInterface, i) -> {
                    startActivity(new Intent(CombatActivity.this, MainActivity.class));
                    finish();
                })
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