package com.example.rypygy.models;

abstract public class ItemTemp2{
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
