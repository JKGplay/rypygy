package com.example.rypygy.models;

import com.example.rypygy.models.Model;

abstract public class Item extends Model {
    protected enum Category {
        WEAPON,
        ARMOR,
        POTION,
        SCROLL
    }

    protected String name;
    protected Category category;
    protected int price;
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
}
