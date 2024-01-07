package com.example.rypygy.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.rypygy.R;
import com.example.rypygy.enums.Location;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Rnd;

import org.apache.commons.text.WordUtils;

import java.util.HashMap;

public class GoldData {
    private String title, message;
    private int amount;
    private static final HashMap<Location, Integer[]> DATA = new HashMap<Location, Integer[]>() {{
        put(Location.FOREST, new Integer[]{25, 100});
        put(Location.GARAGES, new Integer[]{100, 175});
        put(Location.TOILETS, new Integer[]{175, 250});
        put(Location.COMPUTER_LAB, new Integer[]{250, 325});
        put(Location.DORMITORY, new Integer[]{325, 400});
        put(Location.COURTYARD, new Integer[]{400, 475});
        put(Location.KACZYCE, new Integer[]{475, 550});
    }};

    public GoldData(@NonNull Location location, @NonNull Context context) {
        title = context.getResources().getString(R.string.encounter_location_title, WordUtils.capitalizeFully(location.toString().replace('_', ' ')));
        amount = Rnd.rnd(DATA.get(location)[0], DATA.get(location)[1]);
        message = context.getResources().getString(R.string.encounter_gold_message, amount);
    }

    public void action() {
        Character.addGold(amount);
    }

    public String getTitle() {
        return title;
    }
    public String getMessage() {
        return message;
    }
    public int getAmount() {
        return amount;
    }
}
