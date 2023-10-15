package com.example.rypygy.models;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

public abstract class Item {
    protected String name;
    protected int price;
    protected enum Category {WEAPON, SHIELD, POTION}
    protected int damage;
    protected int defense;
    protected int heal;

    public Item(@NonNull Category c) {}

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getDamage() {
        return damage;
    }
    public int getDefense() {
        return defense;
    }
    public int getHeal() {
        return heal;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setHeal(int heal) {
        this.heal = heal;
    }
}
