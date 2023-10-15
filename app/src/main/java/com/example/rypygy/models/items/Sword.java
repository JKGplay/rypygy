package com.example.rypygy.models.items;

import androidx.annotation.NonNull;

import com.example.rypygy.models.Item;

public class Sword extends Item {
    public enum Type {WOODEN, STONE, IRON}
    public Sword(@NonNull Type t) {
        super(Category.WEAPON);
        switch (t) {
            case WOODEN:
                this.setName("Wooden Sword");
                this.setPrice(15);
                setDamage(2);
                break;
            case STONE:
                this.setName("Stone Sword");
                this.setPrice(30);
                setDamage(4);
                break;
            case IRON:
                this.setName("Iron Sword");
                this.setPrice(15);
                setDamage(6);
                break;
        }
    }
}
