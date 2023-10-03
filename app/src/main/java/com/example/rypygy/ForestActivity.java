package com.example.rypygy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rypygy.models.Character;
import com.example.rypygy.models.monsters.Wolf;

public class ForestActivity extends AppCompatActivity {

    private TextView tvName, tvHp, tvInfo, tvEnemyName, tvEnemyHp;
    private Button btnAttack, btnDefend, btnItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest);

        tvName = findViewById(R.id.tvName);
        tvHp = findViewById(R.id.tvHp);
        tvInfo = findViewById(R.id.tvInfo);
        tvEnemyName = findViewById(R.id.tvEnemyName);
        tvEnemyHp = findViewById(R.id.tvEnemyHp);
        btnAttack = findViewById(R.id.btnAttack);
        btnDefend = findViewById(R.id.btnDefend);
        btnItem = findViewById(R.id.btnItem);

        Character character = new Character();
        Wolf wolf = new Wolf(1);
        tvInfo.setText("You encounter a wolf!");
        boolean initiative = true;
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                tvInfo.setText("test2");
//            }
//        }, 2000);

        while(true) {
            tvHp.setText("HP: " + character.getCurhp() + "/" + character.getMaxhp());
            tvEnemyHp.setText("HP: " + wolf.getCurhp() + "/" + wolf.getMaxhp());

            btnAttack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvInfo.setText("klik");
                }
            });



            break;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}