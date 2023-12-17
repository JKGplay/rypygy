package com.example.rypygy.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.rypygy.R;
import com.example.rypygy.enums.EnemyType;
import com.example.rypygy.enums.Location;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

public class CombatData {
    private String title, message;
    private Location location;

    private static final HashMap<Location, EnemyType> DATA = new HashMap<Location, EnemyType>() {{
        put(Location.FOREST, EnemyType.FIRST_YEAR);
        put(Location.GARAGES, EnemyType.SMOKER);
        put(Location.TOILETS, EnemyType.GRAPHIC_DESIGNER);
        put(Location.COMPUTER_LAB, EnemyType.FIFTH_YEAR);
        put(Location.DORMITORY, EnemyType.FIRST_YEAR);
        put(Location.COURTYARD, EnemyType.MILITARY);
        put(Location.KACZYCE, EnemyType.FIRST_YEAR);
    }};

    public CombatData(@NonNull Location location, @NonNull Context context) {
        this.location = location;
        title = context.getResources().getString(R.string.location_title, StringUtils.capitalize(location.toString().replace('_', ' ').toLowerCase()));
        message = context.getResources().getString(R.string.combat_message, StringUtils.capitalize(DATA.get(location).toString().replace('_', ' ').toLowerCase()));
    }

    public void action() {

    }

    public String getTitle() {
        return title;
    }
    public String getMessage() {
        return message;
    }
    public Location getLocation() {
        return location;
    }
}
