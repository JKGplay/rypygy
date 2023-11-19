package com.example.rypygy.models;

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
    private int amount;
    private final int price;
    private final HashMap<Attribute, Double> attributes;

    public Item(String name, Category category, int amount, int price) {
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.price = price;
        this.attributes = null;
    }

    public Item(String name, Category category, int amount, int price, HashMap<Attribute, Double> attributes) {
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.price = price;
        this.attributes = attributes;
    }

    public int heal() {
        return (this.getAttributes().containsKey(Attribute.Size)) ? (int) Math.floor(Character.getMaxHP() * this.getAttributes().get(Attribute.Size)) : 0;
    }

    public String getName() {
        return name;
    }
    public Category getCategory() {
        return category;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getPrice() {
        return price;
    }
    public HashMap<Attribute, Double> getAttributes() {
        return attributes;
    }
}
