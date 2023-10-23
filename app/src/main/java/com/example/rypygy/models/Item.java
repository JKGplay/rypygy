package com.example.rypygy.models;

import androidx.annotation.NonNull;

import java.util.Random;

public class Item {
    private String name;
    private int price;
    public enum Category {SWORD, SHIELD, POTION};
    public enum Type {WOODEN, STONE, IRON, SMALL, MEDIUM, LARGE};
    private int damage;
    private int defense;
    private int heal;

    public Item(@NonNull Category c, @NonNull Type t) {
        if (c == Category.SWORD) {
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
                    this.setPrice(45);
                    setDamage(6);
                    break;
            }
        } else if (c == Category.SHIELD) {
            switch (t) {
                case WOODEN:
                    this.setName("Wooden Shield");
                    this.setPrice(15);
                    setDefense(2);
                    break;
                case STONE:
                    this.setName("Stone Shield");
                    this.setPrice(30);
                    setDefense(4);
                    break;
                case IRON:
                    this.setName("Iron Shield");
                    this.setPrice(15);
                    setDefense(6);
                    break;
            }
        } else if (c == Category.POTION) {
            switch (t) {
                case SMALL:
                    this.setName("Small Potion");
                    this.setPrice(10);
                    setHeal(new Random().nextInt(4)+1);
                    break;
                case MEDIUM:
                    this.setName("Medium Potion");
                    this.setPrice(20);
                    setHeal((new Random().nextInt(4)+1)*2);
                    break;
                case LARGE:
                    this.setName("Large Potion");
                    this.setPrice(30);
                    setHeal((new Random().nextInt(4)+1)*3);
                    break;
            }
        }

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getHeal() {
        return heal;
    }
    public void setHeal(int heal) {
        this.heal = heal;
    }
}
