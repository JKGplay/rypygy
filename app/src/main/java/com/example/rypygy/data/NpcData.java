package com.example.rypygy.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.rypygy.R;
import com.example.rypygy.enums.Location;
import com.example.rypygy.models.Character;


import org.apache.commons.text.WordUtils;

import java.util.HashMap;

public class NpcData {
    private String title, message;
    private Location location;
    public static HashMap<Location, Boolean> isVisited = new HashMap<Location, Boolean>() {{
        put(Location.FOREST, false);
        put(Location.GARAGES, false);
        put(Location.TOILETS, false);
        put(Location.COMPUTER_LAB, false);
        put(Location.DORMITORY, false);
        put(Location.COURTYARD, false);
        put(Location.KACZYCE, false);
    }};

    public static HashMap<Location, String> unvisitedData = new HashMap<>();

    public static HashMap<Location, String> visitedData = new HashMap<>();

    public NpcData(@NonNull Location location, @NonNull Context context) {
        unvisitedData.putIfAbsent(Location.FOREST, context.getResources().getString(R.string.npc_forest_unvisited_message, Character.getName()));
        unvisitedData.putIfAbsent(Location.GARAGES, "");
        unvisitedData.putIfAbsent(Location.TOILETS, "");
        unvisitedData.putIfAbsent(Location.COMPUTER_LAB, "");
        unvisitedData.putIfAbsent(Location.DORMITORY, "");
        unvisitedData.putIfAbsent(Location.COURTYARD, "");
        unvisitedData.putIfAbsent(Location.KACZYCE, "");
        visitedData.putIfAbsent(Location.FOREST, context.getResources().getString(R.string.npc_forest_visited_message));
        visitedData.putIfAbsent(Location.GARAGES, "");
        visitedData.putIfAbsent(Location.TOILETS, "");
        visitedData.putIfAbsent(Location.COMPUTER_LAB, "");
        visitedData.putIfAbsent(Location.DORMITORY, "");
        visitedData.putIfAbsent(Location.COURTYARD, "");
        visitedData.putIfAbsent(Location.KACZYCE, "");
        this.location = location;
        title = context.getResources().getString(R.string.location_title, WordUtils.capitalizeFully(location.toString().replace('_', ' ')));
        if (!isVisited.get(location)) {
            message = unvisitedData.get(location);
            isVisited.replace(location, true);
        } else {
            message = visitedData.get(location);
        }
    }

    public void action() {
        if (isVisited.get(location)) {
            switch (location) {
                case FOREST:
                    break;
                case GARAGES:
                    break;
                case TOILETS:
                    break;
                case COMPUTER_LAB:
                    break;
                case DORMITORY:
                    break;
                case COURTYARD:
                    break;
                case KACZYCE:
                    break;
            }
        } else {
            //powtarzalne eventy z akcja (np. radiacja w kaczycach)
        }
    }

    public String getTitle() {
        return title;
    }
    public String getMessage() {
        return message;
    }
}
