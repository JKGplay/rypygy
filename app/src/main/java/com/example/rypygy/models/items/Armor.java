package com.example.rypygy.models.items;

import com.example.rypygy.models.ItemTemp2;
import com.example.rypygy.models.Rnd;

abstract public class Armor extends ItemTemp2 {
    protected int minAC;
    protected int maxAC;

    public int getAC() {
        return Rnd.rnd(minAC, maxAC);
    }

    public Armor(String name, int minAC, int maxAC, int price) {
        this.name = name;
        this.minAC = minAC;
        this.maxAC = maxAC;
        this.price = price;
        this.category = Category.ARMOR;
    }
}
