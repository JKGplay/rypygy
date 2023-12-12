package com.example.rypygy.data;

import androidx.annotation.NonNull;

import com.example.rypygy.App;
import com.example.rypygy.R;
import com.example.rypygy.enums.Location;
import com.example.rypygy.models.Character;

import org.apache.commons.lang3.StringUtils;

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

    public static HashMap<Location, String> unvisitedData = new HashMap<Location, String>() {{
       put(Location.FOREST, App.getContext().getResources().getString(R.string.npc_forest_unvisited_message, Character.getName()));
       put(Location.GARAGES, "");
       put(Location.TOILETS, "");
       put(Location.COMPUTER_LAB, "");
       put(Location.DORMITORY, "");
       put(Location.COURTYARD, "");
       put(Location.KACZYCE, "");
    }};

    public static HashMap<Location, String> visitedData = new HashMap<Location, String>() {{
        put(Location.FOREST, App.getContext().getResources().getString(R.string.npc_forest_visited_message));
        put(Location.GARAGES, "");
        put(Location.TOILETS, "");
        put(Location.COMPUTER_LAB, "");
        put(Location.DORMITORY, "");
        put(Location.COURTYARD, "");
        put(Location.KACZYCE, "");
    }};

    public NpcData(@NonNull Location location) {
        this.location = location;
        title = App.getContext().getResources().getString(R.string.location_title, StringUtils.capitalize(location.toString().replace('_', ' ').toLowerCase()));
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
