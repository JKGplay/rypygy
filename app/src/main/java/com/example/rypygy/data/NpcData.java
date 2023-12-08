package com.example.rypygy.data;

import androidx.annotation.NonNull;

import com.example.rypygy.App;
import com.example.rypygy.SecondActivity;
import com.example.rypygy.R;
import com.example.rypygy.models.Character;

import java.util.HashMap;

public class NpcData {
    private String title, message;
    private SecondActivity.Location location;
    public static HashMap<SecondActivity.Location, Boolean> isVisited = new HashMap<SecondActivity.Location, Boolean>() {{
        put(SecondActivity.Location.FOREST, false);
        put(SecondActivity.Location.GARAGES, false);
        put(SecondActivity.Location.TOILETS, false);
        put(SecondActivity.Location.COMPUTER_LAB, false);
        put(SecondActivity.Location.DORMITORY, false);
        put(SecondActivity.Location.COURTYARD, false);
        put(SecondActivity.Location.KACZYCE, false);
    }};

    public static HashMap<SecondActivity.Location, String[]> unvisitedData = new HashMap<SecondActivity.Location, String[]>() {{
       put(SecondActivity.Location.FOREST, new String[]{
               App.getContext().getResources().getString(R.string.npc_forest_unvisited_title),
               App.getContext().getResources().getString(R.string.npc_forest_unvisited_message, Character.getName()),
               // TODO: dodac dostep do nazwy gracza (aktualnie jej nie ma bo dane zbiera przy odpaleniu aplikacji)
       });
       put(SecondActivity.Location.GARAGES, new String[]{
               "",
               "",
       });
        put(SecondActivity.Location.TOILETS, new String[]{
                "",
                "",
        });
        put(SecondActivity.Location.COMPUTER_LAB, new String[]{
                "",
                "",
        });
        put(SecondActivity.Location.DORMITORY, new String[]{
                "",
                "",
        });
        put(SecondActivity.Location.COURTYARD, new String[]{
                "",
                "",
        });
        put(SecondActivity.Location.KACZYCE, new String[]{
                "",
                "",
        });
    }};

    public static HashMap<SecondActivity.Location, String[]> visitedData = new HashMap<SecondActivity.Location, String[]>() {{
        put(SecondActivity.Location.FOREST, new String[]{
                "You wander into Forest...",
                "You meet Fred. He doesn't have anything more to say to help you with First-Years, but you talk all day anyway."
        });
        put(SecondActivity.Location.GARAGES, new String[]{
                "",
                "",
        });
        put(SecondActivity.Location.TOILETS, new String[]{
                "",
                "",
        });
        put(SecondActivity.Location.COMPUTER_LAB, new String[]{
                "",
                "",
        });
        put(SecondActivity.Location.DORMITORY, new String[]{
                "",
                "",
        });
        put(SecondActivity.Location.COURTYARD, new String[]{
                "",
                "",
        });
        put(SecondActivity.Location.KACZYCE, new String[]{
                "",
                "",
        });
    }};

    public NpcData(@NonNull SecondActivity.Location location) {
        this.location = location;
        if (!isVisited.get(location)) {
            title = unvisitedData.get(location)[0];
            message = unvisitedData.get(location)[1];
            isVisited.replace(location, true);
        } else {
            title = visitedData.get(location)[0];
            message = visitedData.get(location)[1];
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

    public String getName() {
        return title;
    }
    public String getMessage() {
        return message;
    }
}
