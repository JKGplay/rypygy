package com.example.rypygy.models.items;

import androidx.annotation.NonNull;

import com.example.rypygy.models.Character;
import com.example.rypygy.models.Item;

public class Potion extends Item {
    protected enum Size {
        SMALL,
        MEDIUM,
        BIG
    }
    protected int heal;

    public Potion(@NonNull Size size) {
        this.category = Category.POTION;
        switch (size) {
            case SMALL:
                this.name = "Small Potion";
                this.price = 25;
                heal = (int) Math.floor(Character.getMaxhp() * 0.25);
                break;
            case MEDIUM:
                this.name = "Medium Potion";
                this.price = 50;
                heal = (int) Math.floor(Character.getMaxhp() * 0.5);
                break;
            case BIG:
                this.name = "Big Potion";
                this.price = 75;
                heal = (int) Math.floor(Character.getMaxhp() * 0.75);
                break;
        }
    }
}
