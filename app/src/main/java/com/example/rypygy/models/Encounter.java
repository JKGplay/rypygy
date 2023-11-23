package com.example.rypygy.models;

import android.util.Log;

import java.util.stream.IntStream;

public class Encounter {

    /*
        Cała klasa skupia się na metodzie outcome.
        Klasa zostaje wywołana w momencie kliknięcia przycisku lokacji w SecondActivity.
        Outcome przyjmuje parametr location (FOREST, GARAGES, itp.)
        Zwraca wynik spotkania (encounteru) np. walka, spotkanie npc, znalezienie itemu lub złota.
        Możliwe jest określenie wartości procentowych na dany encounter; możliwa jest również wartość 0%
     */

    public enum Location {
        FOREST,
        GARAGES,
        TOILETS,
        COMPUTER_ROOM,
        INTERNAT,
        KACZYCE
    }
    public enum Type {
        COMBAT,
        NPC,
        ITEM,
        GOLD
    }

    public static Type outcome(Location location) {
        Type toReturn = null;
        switch (location) {
            case FOREST:
                toReturn = Encounter.percentage(new int[] {50, 15, 20, 15});
                break;
            case GARAGES:
            case TOILETS:
            case COMPUTER_ROOM:
            case INTERNAT:
            case KACZYCE:
                break;
        }
        return toReturn;
    }

    public static Type percentage(int[] percentages) {
        int rnd = Rnd.rnd(1, 100);
//        Log.d("rnd = ", "rnd = " + rnd);
        int i = 0;
        int value = percentages[i];
        Type ret = null;
        if (IntStream.of(percentages).sum() != 100) {
            return null;
        }
        for (Type t : Type.values()) {
            if (value >= rnd) {
                ret = t;
                break;
            }
            i++;
            value += percentages[i];
        }
        return ret;
    }
}
