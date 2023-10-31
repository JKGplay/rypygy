package com.example.rypygy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rypygy.models.Attribute;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Item;
import com.example.rypygy.models.Rnd;
import com.example.rypygy.models.enemies.FirstYear;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Arrays;

public class ForestActivity extends AppCompatActivity {

    private TextView tvName, tvHp, tvPlayerInfo, tvEnemyInfo, tvEnemyName, tvEnemyHp;
    private Button btnAttack, btnDefend, btnItem;

    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest);

        ////////////////

        Item woodenSword = new Item("Wooden Sword", Item.Type.WEAPON, 10,
                new Attribute[]{
                        new Attribute(Attribute.Type.MinDMG, 2),
                        new Attribute(Attribute.Type.MaxDMG, 6)
                }
        );

        ///////////////

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
                    int dmg = Character.getDamage() + Rnd.rnd(Character.getWeapon().getAttributes()[0].value, Character.getWeapon().getAttributes()[1].value);//dodac jeszcze dmg z broni
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
                    if (monster.toHit(Character.getLevel(), Character.getAc() + Rnd.rnd(Character.getArmor().getAttributes()[0].value, Character.getArmor().getAttributes()[1].value))) {
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
                Item[] search = new Item[]{
                        new Item("Small Potion")
                };
                for (Item item: Character.getInventory()) {
                    if (item.equals(search)) {
                        Character.setCurhp(Math.min(70, Character.getCurhp() + item.getAttributes()[0].value));
                        tvHp.setText("HP: " + Character.getCurhp() + "/" + Character.getMaxhp());
                        break;
                    }
                }
                Log.d("test", "---");
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}