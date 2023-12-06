package com.example.rypygy.models;

import androidx.annotation.NonNull;

import com.example.rypygy.SecondActivity;

public class NpcData {
    private String name, message;
    private SecondActivity.Location location;
    private boolean isVisited;
    //cos porobic z tym statikiem zeby byl osobny dla kazdego npcta (zmienic na hashmape)

    public NpcData(@NonNull SecondActivity.Location location) {
        this.location = location;
        switch (location) {
            case FOREST:
                name = "placeholder_name";
                message = "placeholder_message";
                break;
            case GARAGES:
                name = "placeholder_name";
                message = "placeholder_message";
                break;
            case TOILETS:
                name = "Pytel";
                message = "placeholder_message";
                break;
            case COMPUTER_LAB:
                name = "Olszar";
                message = "placeholder_message";
                break;
            case DORMITORY:
                name = "Guzy";
                message = "placeholder_message";
                break;
            case COURTYARD:
                name = "Pawlak";
                message = "placeholder_message";
                break;
            case KACZYCE:
                name = "placeholder_name";
                message = "placeholder_message";
                break;
        }
    }

    public void action() {
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
    }

    public String getName() {
        return name;
    }
    public String getMessage() {
        return message;
    }
    public boolean isVisited() {
        return isVisited;
    }
    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}
