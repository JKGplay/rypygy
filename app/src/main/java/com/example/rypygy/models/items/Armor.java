package com.example.rypygy.models.items;

import com.example.rypygy.models.Item;

abstract public class Armor extends Item {
    protected int minAC;
    protected int maxAC;

    public int getAC() {
        return rnd(minAC, maxAC);
    }

    public Armor(String name, int minAC, int maxAC, int price) {
        this.name = name;
        this.minAC = minAC;
        this.maxAC = maxAC;
        this.price = price;
        this.category = Category.ARMOR;
    }
}
