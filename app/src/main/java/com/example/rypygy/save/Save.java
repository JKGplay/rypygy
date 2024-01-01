package com.example.rypygy.save;

public class Save {
    private SaveCharacter character;
    private SaveInventory inventory;
    private SaveEncounters encounters;

    public Save() {
        character = new SaveCharacter();
        inventory = new SaveInventory();
        encounters = new SaveEncounters();
    }

    public SaveCharacter getCharacter() {
        return character;
    }
    public SaveInventory getInventory() {
        return inventory;
    }
    public SaveEncounters getEncounters() {
        return encounters;
    }

    @Override
    public String toString() {
        return "Save{" +
                "character=" + character +
                ", inventory=" + inventory +
                ", encounters=" + encounters +
                '}';
    }
}
