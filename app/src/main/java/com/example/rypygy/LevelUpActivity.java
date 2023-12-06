package com.example.rypygy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rypygy.models.Character;
import com.google.android.material.snackbar.Snackbar;

public class LevelUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvInfo, tvPoints, tvStrChange, tvDexChange, tvVitChange;
    private ImageButton btnStr, btnDex, btnVit;
    private Button btnSubmit, btnReset;
    private int points = 5;
    private int curStr, curDex, curVit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_up);

        tvInfo = findViewById(R.id.tvInfo);
        tvPoints = findViewById(R.id.tvPoints);
        tvStrChange = findViewById(R.id.tvStrChange);
        tvDexChange = findViewById(R.id.tvDexChange);
        tvVitChange = findViewById(R.id.tvVitChange);
        btnStr = findViewById(R.id.btnStr);
        btnDex = findViewById(R.id.btnDex);
        btnVit = findViewById(R.id.btnVit);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnReset = findViewById(R.id.btnReset);

        btnStr.setOnClickListener(this);
        btnDex.setOnClickListener(this);
        btnVit.setOnClickListener(this);

        setStats();

        btnSubmit.setOnClickListener(view -> {
            if (points != 0) {
                Snackbar.make(view, "You still have some points left", Snackbar.LENGTH_SHORT).show();
            } else {
                Character.setStrength(curStr);
                Character.setDexterity(curDex);
                Character.setVitality(curVit);
                Character.setLevel(Character.getLevel()+1);
                Character.setCurHP(Character.getMaxHP());
                Character.setXp(Math.max(Character.getXp() - 100, 0));
                startActivity(new Intent(LevelUpActivity.this, SecondActivity.class));
                finish();
            }
        });

        btnReset.setOnClickListener(view -> setStats());
    }

    @Override
    public void onClick(View view) {
        if (points == 0) {
            Snackbar.make(view, "You don't have any points left", Snackbar.LENGTH_SHORT).show();
        } else {
            points--;
            tvPoints.setText("Points left: " + points);
            if (((ImageButton) view).equals(btnStr)) {
                curStr++;
                tvStrChange.setText(Character.getStrength() + " -> " + curStr);
            } else if (((ImageButton) view).equals(btnDex)) {
                curDex++;
                tvDexChange.setText(Character.getDexterity() + " -> " + curDex);
            } else if (((ImageButton) view).equals(btnVit)) {
                curVit++;
                tvVitChange.setText(Character.getVitality() + " -> " + curVit);
            }
        }
    }

    public void setStats() {
        points = 5;
        curStr = Character.getStrength();
        curDex = Character.getDexterity();
        curVit = Character.getVitality();

        tvInfo.setText("Level Up " + Character.getLevel() + " -> " + (Character.getLevel()+1));
        tvPoints.setText("Points left: " + points);
        tvStrChange.setText(Character.getStrength() + " -> " + curStr);
        tvDexChange.setText(Character.getDexterity() + " -> " + curDex);
        tvVitChange.setText(Character.getVitality() + " -> " + curVit);
    }
}