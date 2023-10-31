package com.example.rypygy.models;

import android.util.Log;

public class Item {

    public enum Type {
        ARMOR,
        POTION,
        SCROLL,
        WEAPON
    }

    private final String name;
    private final Type type;
    private final int price;
    private final Attribute[] attributes;

    public Item(String name) {
        this.name = name;
        this.type = null;
        this.price = 0;
        this.attributes = new Attribute[]{};
    }

    public Item(String name, Type type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.attributes = new Attribute[]{};
    }

    public Item(String name, Type type, int price, Attribute[] attributes) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.attributes = attributes;
    }

    public boolean equals(Item[] items) {
        for (Item item: items) {
            if (this.name.equals(item.name)) {
                return true;
            }
        }
        return false;
    }
    public String getName() {
        return name;
    }
    public Type getType() {
        return type;
    }
    public int getPrice() {
        return price;
    }
    public Attribute[] getAttributes() {
        return attributes;
    }
}
