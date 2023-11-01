package com.example.rypygy.models;

import android.util.Log;

import java.util.HashMap;

public class Item {

    public enum Category {
        ARMOR,
        POTION,
        SCROLL,
        WEAPON
    }

    public enum Attribute {
        MinAC,
        MaxAC,
        MinDMG,
        MaxDMG,
        Size
    }

    private final String name;
    private final Category category;
    private final int price;
    private final HashMap<Attribute, Double> attributes;

    public Item(String name) {
        this.name = name;
        this.category = null;
        this.price = 0;
        this.attributes = null;
    }

    public Item(String name, Category category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.attributes = null;
    }

    public Item(String name, Category category, int price, HashMap<Attribute, Double> attributes) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.attributes = attributes;
    }

    public int heal() {
        return (this.getAttributes().containsKey(Attribute.Size)) ? (int) Math.floor(Character.getMaxhp() * this.getAttributes().get(Attribute.Size)) : 0;
    }

//    public boolean equals(Item[] items) {
//        for (Item item: items) {
//            if (this.name.equals(item.name)) {
//                return true;
//            }
//        }
//        return false;
//    }
    public String getName() {
        return name;
    }
    public Category getCategory() {
        return category;
    }
    public int getPrice() {
        return price;
    }
    public HashMap<Attribute, Double> getAttributes() {
        return attributes;
    }
}
