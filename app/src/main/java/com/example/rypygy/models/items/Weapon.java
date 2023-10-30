package com.example.rypygy.models.items;

import com.example.rypygy.models.Item;

abstract public class Weapon extends Item {
    protected int minDamage;
    protected int maxDamage;

    public int damage() {
        return rnd(minDamage, maxDamage);
    }

    public Weapon(String name, int minDamage, int maxDamage, int price) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.price = price;
        this.category = Category.WEAPON;
    }
}
