package com.example.rypygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.example.rypygy.data.CombatData;
import com.example.rypygy.data.GoldData;
import com.example.rypygy.data.ItemData;
import com.example.rypygy.data.NpcData;
import com.example.rypygy.enums.Location;
import com.example.rypygy.enums.EncounterType;
import com.example.rypygy.models.Rnd;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.apache.commons.text.WordUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class EncounterActivity extends AppCompatActivity {

    private TextView tvLocation;
    private Button btnExplore, btnLeave;
    private Location location;
    //TODO: zagnieżdżony hashmap: HashMap<Location, HashMap<EncounterType, int[]>>
    private HashMap<Location, HashMap<EncounterType, Integer>> chances = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);

        tvLocation = findViewById(R.id.tvLocation);
        btnExplore = findViewById(R.id.btnExplore);
        btnLeave = findViewById(R.id.btnLeave);

        fillChances();

        if (getIntent().hasExtra(SecondActivity.ENCOUNTER_LOCATION_KEY)) {
            location = (Location) getIntent().getSerializableExtra(SecondActivity.ENCOUNTER_LOCATION_KEY);
            tvLocation.setText(WordUtils.capitalizeFully(location.toString().replace('_', ' ')));
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

    private EncounterType draw(@NonNull HashMap<EncounterType, Integer> h) {
        int rnd = Rnd.rnd(1, 100);
        int i = 0;
        List<Integer> values = new ArrayList<>(h.values());
        int value = values.get(i);
        EncounterType toReturn = null;
        for (EncounterType t : EncounterType.values()) {
            if (value >= rnd) {
                toReturn = t;
                break;
            }
            i++;
            value += values.get(i);
        }
        return toReturn;
    }

    private void encounter_combat(@NonNull MaterialAlertDialogBuilder builder) {
        CombatData combat = new CombatData(location, EncounterActivity.this);
        builder
                .setTitle(combat.getTitle())
                .setMessage(Html.fromHtml(combat.getMessage(), Html.FROM_HTML_MODE_LEGACY))
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    startActivity(new Intent(EncounterActivity.this, FightActivity.class).putExtra(SecondActivity.ENCOUNTER_LOCATION_KEY, location));
                    finish();
                })
                .setCancelable(false);
    }

    private void encounter_npc(@NonNull MaterialAlertDialogBuilder builder) {
        NpcData npc = new NpcData(location, EncounterActivity.this);
        builder
                .setTitle(npc.getTitle())
                .setMessage(Html.fromHtml(npc.getMessage(), Html.FROM_HTML_MODE_LEGACY))
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    npc.action();
                })
                .setCancelable(false);
    }

    private void encounter_item(@NonNull MaterialAlertDialogBuilder builder) {
        ItemData item = new ItemData(location, EncounterActivity.this);
        builder
                .setTitle(item.getTitle())
                .setMessage(Html.fromHtml(item.getMessage(), Html.FROM_HTML_MODE_LEGACY))
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    item.action();
                })
                .setCancelable(false);
    }

    private void encounter_gold(@NonNull MaterialAlertDialogBuilder builder) {
        GoldData gold = new GoldData(location, EncounterActivity.this);
        builder
                .setTitle(gold.getTitle())
                .setMessage(Html.fromHtml(gold.getMessage(), Html.FROM_HTML_MODE_LEGACY))
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    gold.action();
                })
                .setCancelable(false);
    }

    private void fillChances() {
        HashMap<EncounterType, Integer> chancesForest = new HashMap<>();
        HashMap<EncounterType, Integer> chancesGarages = new HashMap<>();
        HashMap<EncounterType, Integer> chancesToilets = new HashMap<>();
        HashMap<EncounterType, Integer> chancesComputerLab = new HashMap<>();
        HashMap<EncounterType, Integer> chancesDormitory = new HashMap<>();
        HashMap<EncounterType, Integer> chancesCourtyard = new HashMap<>();
        HashMap<EncounterType, Integer> chancesKaczyce = new HashMap<>();

        chancesForest.put(EncounterType.COMBAT, 50);
        chancesForest.put(EncounterType.NPC, 15);
        chancesForest.put(EncounterType.ITEM, 20);
        chancesForest.put(EncounterType.GOLD, 15);

        chancesGarages.put(EncounterType.COMBAT, 50);
        chancesGarages.put(EncounterType.NPC, 15);
        chancesGarages.put(EncounterType.ITEM, 20);
        chancesGarages.put(EncounterType.GOLD, 15);

        chancesToilets.put(EncounterType.COMBAT, 50);
        chancesToilets.put(EncounterType.NPC, 15);
        chancesToilets.put(EncounterType.ITEM, 20);
        chancesToilets.put(EncounterType.GOLD, 15);

        chancesComputerLab.put(EncounterType.COMBAT, 50);
        chancesComputerLab.put(EncounterType.NPC, 15);
        chancesComputerLab.put(EncounterType.ITEM, 20);
        chancesComputerLab.put(EncounterType.GOLD, 15);

        chancesDormitory.put(EncounterType.COMBAT, 50);
        chancesDormitory.put(EncounterType.NPC, 15);
        chancesDormitory.put(EncounterType.ITEM, 20);
        chancesDormitory.put(EncounterType.GOLD, 15);

        chancesCourtyard.put(EncounterType.COMBAT, 50);
        chancesCourtyard.put(EncounterType.NPC, 15);
        chancesCourtyard.put(EncounterType.ITEM, 20);
        chancesCourtyard.put(EncounterType.GOLD, 15);

        chancesKaczyce.put(EncounterType.COMBAT, 50);
        chancesKaczyce.put(EncounterType.NPC, 15);
        chancesKaczyce.put(EncounterType.ITEM, 20);
        chancesKaczyce.put(EncounterType.GOLD, 15);

        chances.put(Location.FOREST, chancesForest);
        chances.put(Location.GARAGES, chancesGarages);
        chances.put(Location.TOILETS, chancesToilets);
        chances.put(Location.COMPUTER_LAB, chancesComputerLab);
        chances.put(Location.DORMITORY, chancesDormitory);
        chances.put(Location.COURTYARD, chancesCourtyard);
        chances.put(Location.KACZYCE, chancesKaczyce);
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