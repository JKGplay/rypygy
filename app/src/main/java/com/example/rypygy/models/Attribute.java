package com.example.rypygy.models;

import androidx.annotation.NonNull;

public class Attribute {

    public enum Type {
        MinAC,
        MaxAC,
        MinDMG,
        MaxDMG,
        Small,
        Medium,
        Big
    }

    public final Type type;
    public final int value;

    public Attribute(Type type, int value) {
        this.type = type;
        this.value = value;
    }

    public Attribute(@NonNull Type type) {
        this.type = type;
        switch (type) {
            case Small:
                this.value = (int) Math.floor(Character.getMaxhp() * 0.25);
                break;
            case Medium:
                this.value = (int) Math.floor(Character.getMaxhp() * 0.5);
                break;
            case Big:
                this.value = (int) Math.floor(Character.getMaxhp() * 0.75);
                break;
            default:
                this.value = 0;
                break;
        }
    }
}
