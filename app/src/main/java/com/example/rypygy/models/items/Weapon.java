package com.example.rypygy.models.items;

import com.example.rypygy.models.ItemTemp2;
import com.example.rypygy.models.Rnd;

abstract public class Weapon extends ItemTemp2 {
    protected int minDamage;
    protected int maxDamage;

    public int damage() {
        return Rnd.rnd(minDamage, maxDamage);
    }

    public Weapon(String name, int minDamage, int maxDamage, int price) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.price = price;
        this.category = Category.WEAPON;
    }
}
