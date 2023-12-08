package com.example.rypygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.example.rypygy.data.NpcData;
import com.example.rypygy.models.Rnd;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Objects;

public class EncounterActivity extends AppCompatActivity {

    private TextView tvLocation;
    private Button btnExplore, btnLeave;
    private SecondActivity.Location location;
    private HashMap<SecondActivity.Location, int[]> chances = new HashMap<>();
    private enum Type {
        COMBAT,
        NPC,
        ITEM,
        GOLD
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);

        tvLocation = findViewById(R.id.tvLocation);
        btnExplore = findViewById(R.id.btnExplore);
        btnLeave = findViewById(R.id.btnLeave);

        chances.put(SecondActivity.Location.FOREST, new int[]{0, 100, 0, 0});
        chances.put(SecondActivity.Location.GARAGES, new int[]{50, 15, 20, 15});
        chances.put(SecondActivity.Location.TOILETS, new int[]{50, 15, 20, 15});
        chances.put(SecondActivity.Location.COMPUTER_LAB, new int[]{50, 15, 20, 15});
        chances.put(SecondActivity.Location.DORMITORY, new int[]{50, 15, 20, 15});
        chances.put(SecondActivity.Location.COURTYARD, new int[]{50, 15, 20, 15});
        chances.put(SecondActivity.Location.KACZYCE, new int[]{50, 15, 20, 15});

        if (getIntent().hasExtra(SecondActivity.ENCOUNTER_LOCATION_KEY)) {
            location = (SecondActivity.Location) getIntent().getSerializableExtra(SecondActivity.ENCOUNTER_LOCATION_KEY);
            tvLocation.setText(StringUtils.capitalize(location.toString().replace('_', ' ').toLowerCase()));
        } else {
            startActivity(new Intent(EncounterActivity.this, SecondActivity.class));
            finish();
        }

        btnLeave.setOnClickListener(view -> {
            startActivity(new Intent(EncounterActivity.this, SecondActivity.class));
            finish();
        });

        btnExplore.setOnClickListener(view -> {
            explore();
        });
    }

    private void explore() {
                switch (draw(Objects.requireNonNull(chances.get(location)))) {
                    case COMBAT:
                        encounter_combat();
//                        Log.d("Wylosowano", "Wylosowano walke");
                        break;
                    case NPC:
                        encounter_npc();
//                        Log.d("Wylosowano", "Wylosowano npc");
                        break;
                    case ITEM:
                        encounter_item();
//                        Log.d("Wylosowano", "Wylosowano item");
                        break;
                    case GOLD:
                        encounter_gold();
//                        Log.d("Wylosowano", "Wylosowano gold");
                        break;
                }
//                Log.d(l.toString(), Arrays.toString(temp.get(l)));
    }

    private Type draw(@NonNull int[] n) {
        int rnd = Rnd.rnd(1, 100);
        int i = 0;
        int value = n[i];
        Type toReturn = null;
        for (Type t : Type.values()) {
            if (value >= rnd) {
                toReturn = t;
                break;
            }
            i++;
            value += n[i];
        }
        return toReturn;
    }

    private void encounter_combat() {
        startActivity(new Intent(EncounterActivity.this, FightActivity.class).putExtra(SecondActivity.ENCOUNTER_LOCATION_KEY, location));
        finish();
    }

    private void encounter_npc() {
        NpcData npc = new NpcData(location);
        new MaterialAlertDialogBuilder(EncounterActivity.this)
                .setTitle(npc.getName())
                .setMessage(Html.fromHtml(npc.getMessage()))
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    npc.action();
                })
                .setCancelable(false)
                .show();
    }

    private void encounter_item() {

    }

    private void encounter_gold() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(EncounterActivity.this, SecondActivity.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(EncounterActivity.this, SecondActivity.class));
        finish();
//        super.onBackPressed();
    }
}