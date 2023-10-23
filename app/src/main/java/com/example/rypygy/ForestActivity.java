package com.example.rypygy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rypygy.models.Character;
import com.example.rypygy.models.monsters.Wolf;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class ForestActivity extends AppCompatActivity {

    private TextView tvName, tvHp, tvPlayerInfo, tvMonsterInfo, tvEnemyName, tvEnemyHp;
    private Button btnAttack, btnDefend, btnItem;

    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest);

        tvName = findViewById(R.id.tvName);
        tvHp = findViewById(R.id.tvHp);
        tvPlayerInfo = findViewById(R.id.tvPlayerInfo);
        tvMonsterInfo = findViewById(R.id.tvMonsterInfo);
        tvEnemyName = findViewById(R.id.tvEnemyName);
        tvEnemyHp = findViewById(R.id.tvEnemyHp);
        btnAttack = findViewById(R.id.btnAttack);
        btnDefend = findViewById(R.id.btnDefend);
        btnItem = findViewById(R.id.btnItem);

        Character character = new Character();
        Wolf wolf = new Wolf(1);
        tvPlayerInfo.setText("You encounter a wolf!");

        tvName.setText(character.getName());
        tvHp.setText("HP: " + character.getCurhp() + "/" + character.getMaxhp());

        btnAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                int charDmg = character.attack();
                tvPlayerInfo.setText("You attacked and dealt " + charDmg + " damage!");
                wolf.setCurhp(wolf.getCurhp() - charDmg);
                if (wolf.getCurhp() > 0) {
                    int enemDmg = wolf.attack();
                    tvMonsterInfo.setText("Wold attacked you and dealt " + enemDmg + " damage!");
                    character.setCurhp(character.getCurhp() - enemDmg);

                    tvHp.setText("HP: " + character.getCurhp() + "/" + character.getMaxhp());
                    tvEnemyHp.setText("HP: " + wolf.getCurhp() + "/" + wolf.getMaxhp());
                } else {

                    new MaterialAlertDialogBuilder(ForestActivity.this)
                            .setTitle("You won!")
                            .setMessage("You get 10 xp")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    character.setXp(character.getXp() + 10);
                                    if (character.getXp() >= 100) {
                                        startActivity(new Intent(ForestActivity.this, LevelUpActivity.class));
                                        finish();
                                    } else {
                                        startActivity(new Intent(ForestActivity.this, MainActivity2.class));
                                        finish();
                                    }
                                }
                            })
                            .show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}