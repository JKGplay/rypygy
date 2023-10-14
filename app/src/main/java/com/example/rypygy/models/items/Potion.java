package com.example.rypygy.models.items;

import androidx.annotation.NonNull;

import com.example.rypygy.models.Item;

import java.util.Random;

public class Potion extends Item {
    private enum Types {SMALL, MEDIUM, LARGE}
    public Potion(@NonNull Types t) {
        super(Category.POTION);
        switch (t) {
            case SMALL:
                this.setName("Small Health Potion");
                this.setPrice(10);
                setHeal(new Random().nextInt(4)+1);
                break;
            case MEDIUM:
                this.setName("Medium Health Potion");
                this.setPrice(20);
                setHeal((new Random().nextInt(4)+1)*2);
                break;
            case LARGE:
                this.setName("Large Health Potion");
                this.setPrice(30);
                setHeal((new Random().nextInt(4)+1)*3);
                break;
        }
    }
}
