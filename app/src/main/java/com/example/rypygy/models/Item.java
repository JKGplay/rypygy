package com.example.rypygy.models;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class Item {

    public enum PredefinedItems {
        SHORT_SWORD,
        LONG_SWORD,
        CLAYMORE,
        GREAT_SWORD,
        BATTLE_AXE,
        GREAT_AXE,
        WAR_HAMMER,
        RAGS,
        LEATHER_ARMOR,
        STUDDED_LEATHER_ARMOR,
        CHAIN_MAIL,
        SCALE_MAIL,
        PLATE_MAIL,
        SMALL_POTION,
        MEDIUM_POTION,
        LARGE_POTION,
        SCROLL_OF_RETURN,
    }

    public enum Category {
        ARMOR,
        POTION,
        SCROLL,
        WEAPON
    }

    public enum Attribute {
        MIN_AC,
        MAX_AC,
        MIN_DMG,
        MAX_DMG,
        SIZE_OF_POTION
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

    public Item(@NonNull PredefinedItems predefinedItem, int amount) {
        String name = "";
        Category category = null;
        int price = 0;
        HashMap<Attribute, Double> attributes = new HashMap<>();

        switch (predefinedItem) {
            case SHORT_SWORD:
                name = "Short Sword";
                category = Category.WEAPON;
                price = 120;
                attributes.put(Attribute.MIN_DMG, 2.0);
                attributes.put(Attribute.MAX_DMG, 6.0);
                break;
            case LONG_SWORD:
                name = "Long Sword";
                category = Category.WEAPON;
                price = 350;
                attributes.put(Attribute.MIN_DMG, 2.0);
                attributes.put(Attribute.MAX_DMG, 10.0);
                break;
            case WAR_HAMMER:
                name = "War Hammer";
                category = Category.WEAPON;
                price = 600;
                attributes.put(Attribute.MIN_DMG, 5.0);
                attributes.put(Attribute.MAX_DMG, 9.0);
                break;
            case CLAYMORE:
                name = "Claymore";
                category = Category.WEAPON;
                price = 450;
                attributes.put(Attribute.MIN_DMG, 1.0);
                attributes.put(Attribute.MAX_DMG, 12.0);
                break;
            case GREAT_SWORD:
                name = "Great Sword";
                category = Category.WEAPON;
                price = 3000;
                attributes.put(Attribute.MIN_DMG, 15.0);//w diablo 10
                attributes.put(Attribute.MAX_DMG, 25.0);//w diablo 20
                break;
            case BATTLE_AXE:
                name = "Battle Axe";
                category = Category.WEAPON;
                price = 1500;
                attributes.put(Attribute.MIN_DMG, 10.0);
                attributes.put(Attribute.MAX_DMG, 25.0);
                break;
            case GREAT_AXE:
                name = "Great Axe";
                category = Category.WEAPON;
                price = 2500;
                attributes.put(Attribute.MIN_DMG, 12.0);
                attributes.put(Attribute.MAX_DMG, 30.0);
                break;
            case RAGS:
                name = "Rags";
                category = Category.ARMOR;
                price = 5;
                attributes.put(Attribute.MIN_AC, 2.0);
                attributes.put(Attribute.MAX_AC, 6.0);
                break;
            case LEATHER_ARMOR:
                name = "Leather Armor";
                category = Category.ARMOR;
                price = 300;
                attributes.put(Attribute.MIN_AC, 10.0);
                attributes.put(Attribute.MAX_AC, 13.0);
                break;
            case STUDDED_LEATHER_ARMOR:
                name = "Studded Leather Armor";
                category = Category.ARMOR;
                price = 700;
                attributes.put(Attribute.MIN_AC, 15.0);
                attributes.put(Attribute.MAX_AC, 17.0);
                break;
            case CHAIN_MAIL:
                name = "Chain Mail";
                category = Category.ARMOR;
                price = 1250;
                attributes.put(Attribute.MIN_AC, 18.0);
                attributes.put(Attribute.MAX_AC, 22.0);
                break;
            case SCALE_MAIL:
                name = "Scale Mail";
                category = Category.ARMOR;
                price = 2300;
                attributes.put(Attribute.MIN_AC, 23.0);
                attributes.put(Attribute.MAX_AC, 28.0);
                break;
            case PLATE_MAIL:
                name = "Plate Mail";
                category = Category.ARMOR;
                price = 4600;
                attributes.put(Attribute.MIN_AC, 42.0);
                attributes.put(Attribute.MAX_AC, 50.0);
                break;
            case SMALL_POTION:
                name = "Small Potion";
                category = Category.POTION;
                price = 50;
                attributes.put(Attribute.SIZE_OF_POTION, 0.25);
                break;
            case MEDIUM_POTION:
                name = "Medium Potion";
                category = Category.POTION;
                price = 75;
                attributes.put(Attribute.SIZE_OF_POTION, 0.5);
                break;
            case LARGE_POTION:
                name = "Large Potion";
                category = Category.POTION;
                price = 100;
                attributes.put(Attribute.SIZE_OF_POTION, 0.75);
                break;
            case SCROLL_OF_RETURN:
                name = "Scroll of Return";
                category = Category.SCROLL;
                price = 200;
                break;
        }

        this.name = name;
        this.category = category;
        this.amount = amount;
        this.price = price;
        this.attributes = attributes;

    }

    public int heal() {
        return (this.getAttributes().containsKey(Attribute.SIZE_OF_POTION)) ? (int) Math.floor(Character.getMaxHP() * this.getAttributes().get(Attribute.SIZE_OF_POTION)) : 0;
    }

    public void addAmount() {
        amount++;
    }
    public void removeAmount() {
        amount--;
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
