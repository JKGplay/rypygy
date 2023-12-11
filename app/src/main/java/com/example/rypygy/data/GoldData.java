package com.example.rypygy.data;

import androidx.annotation.NonNull;

import com.example.rypygy.App;
import com.example.rypygy.R;
import com.example.rypygy.enums.Location;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Rnd;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

public class GoldData {

    private String title, message;
    private int amount;
    private Location location;

    private static HashMap<Location, Integer[]> data = new HashMap<Location, Integer[]>() {{
        put(Location.FOREST, new Integer[]{25, 100});
        put(Location.GARAGES, new Integer[]{25, 100});
        put(Location.TOILETS, new Integer[]{25, 100});
        put(Location.COMPUTER_LAB, new Integer[]{25, 100});
        put(Location.DORMITORY, new Integer[]{25, 100});
        put(Location.COURTYARD, new Integer[]{25, 100});
        put(Location.KACZYCE, new Integer[]{25, 100});
    }};

    public GoldData(@NonNull Location location) {
        title = App.getContext().getResources().getString(R.string.location_title, StringUtils.capitalize(location.toString().replace('_', ' ').toLowerCase()));
        amount = Rnd.rnd(data.get(location)[0], data.get(location)[1]);
        message = App.getContext().getResources().getString(R.string.gold_message, amount);
        this.location = location;
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
