package com.example.rypygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.example.rypygy.data.GoldData;
import com.example.rypygy.data.ItemData;
import com.example.rypygy.data.NpcData;
import com.example.rypygy.enums.Location;
import com.example.rypygy.enums.EncounterType;
import com.example.rypygy.models.Rnd;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Objects;

public class EncounterActivity extends AppCompatActivity {

    private TextView tvLocation;
    private Button btnExplore, btnLeave;
    private Location location;
    //TODO: zagnieżdżony hashmap: HashMap<Location, HashMap<EncounterType, int[]>>
    private HashMap<Location, Integer[]> chances = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);

        tvLocation = findViewById(R.id.tvLocation);
        btnExplore = findViewById(R.id.btnExplore);
        btnLeave = findViewById(R.id.btnLeave);

        chances.put(Location.FOREST, new Integer[]{50, 15, 20, 15});
        chances.put(Location.GARAGES, new Integer[]{50, 15, 20, 15});
        chances.put(Location.TOILETS, new Integer[]{50, 15, 20, 15});
        chances.put(Location.COMPUTER_LAB, new Integer[]{50, 15, 20, 15});
        chances.put(Location.DORMITORY, new Integer[]{50, 15, 20, 15});
        chances.put(Location.COURTYARD, new Integer[]{50, 15, 20, 15});
        chances.put(Location.KACZYCE, new Integer[]{50, 15, 20, 15});

        if (getIntent().hasExtra(SecondActivity.ENCOUNTER_LOCATION_KEY)) {
            location = (Location) getIntent().getSerializableExtra(SecondActivity.ENCOUNTER_LOCATION_KEY);
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
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(EncounterActivity.this);
        switch (draw(Objects.requireNonNull(chances.get(location)))) {
            case COMBAT:
                encounter_combat(builder);
                break;
            case NPC:
                encounter_npc(builder);
                break;
            case ITEM:
                encounter_item(builder);
                break;
            case GOLD:
                encounter_gold(builder);
                break;
        }
        builder.show();
    }

    private EncounterType draw(@NonNull Integer[] n) {
        int rnd = Rnd.rnd(1, 100);
        int i = 0;
        int value = n[i];
        EncounterType toReturn = null;
        for (EncounterType t : EncounterType.values()) {
            if (value >= rnd) {
                toReturn = t;
                break;
            }
            i++;
            value += n[i];
        }
        return toReturn;
    }

    private void encounter_combat(@NonNull MaterialAlertDialogBuilder builder) {
        startActivity(new Intent(EncounterActivity.this, FightActivity.class).putExtra(SecondActivity.ENCOUNTER_LOCATION_KEY, location));
        finish();
    }

    private void encounter_npc(@NonNull MaterialAlertDialogBuilder builder) {
        NpcData npc = new NpcData(location);
        builder
                .setTitle(npc.getTitle())
                .setMessage(Html.fromHtml(npc.getMessage()))
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    npc.action();
                })
                .setCancelable(false);
    }

    private void encounter_item(@NonNull MaterialAlertDialogBuilder builder) {
        ItemData item = new ItemData(location);
        builder
                .setTitle(item.getTitle())
                .setMessage(Html.fromHtml(item.getMessage()))
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    item.action();
                })
                .setCancelable(false);
    }

    private void encounter_gold(@NonNull MaterialAlertDialogBuilder builder) {
        GoldData gold = new GoldData(location);
        builder
                .setTitle(gold.getTitle())
                .setMessage(Html.fromHtml(gold.getMessage()))
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    gold.action();
                })
                .setCancelable(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(EncounterActivity.this, SecondActivity.class));
        finish();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(EncounterActivity.this, SecondActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}