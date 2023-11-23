package com.example.rypygy.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Character {
    private static String name;
    private static int level = 1;
    private static int strength = 30;
    private static int dexterity = 20;
    private static int vitality = 25;
    private static int curHP = getMaxHP();
    private static int xp = 0;
    private static int money = 0;
    private static Item weapon = new Item(Item.PredefinedItems.SHORT_SWORD, 1);
    private static Item armor = new Item(Item.PredefinedItems.RAGS, 1);
    private static List<Item> inventory = new ArrayList<Item>() {{
        add(weapon);
        add(armor);
        add(new Item(Item.PredefinedItems.LONG_SWORD, 1));
        add(new Item(Item.PredefinedItems.SMALL_POTION, 3));
        add(new Item(Item.PredefinedItems.MEDIUM_POTION, 2));
        add(new Item(Item.PredefinedItems.LARGE_POTION, 5));
    }};

    //https://www.lurkerlounge.com/diablo/jarulf/jarulf162.pdf

    //TODO: uporządkować metody dotyczące ekwipunku

    public static boolean toHit(int ACmonster) {
        //min(max(toHit, 5), 95)
        return Rnd.rnd(1, 100) <= Math.min(Math.max((int) Math.floor(70 + (double) dexterity / 2 + level - ACmonster), 5), 95);
    }

    public static void addItem(Item item) {
        if (isItemInInventory(item)) {
            inventory.get(getIndexOf(item.getName())).addAmount();
        } else {
            inventory.add(item);
        }

    }

    public static int getIndexOf(String name) {
        for (Item item: inventory) {
            if (item.getName().equals(name)) {
                return inventory.indexOf(item);
            }
        }
        return -1;
    }

    public static boolean isItemInInventory(Item searched) {
        for (Item item : inventory) {
            if (item == searched) {
                return true;
            }
        }
        return false;
    }

    public static void removeItem(int i) {
        if (inventory.get(i).getAmount() > 1) {
            inventory.get(i).removeAmount();
        } else {
            inventory.remove(i);
        }
    }

    public static String getName() {
        return name;
    }
    public static void setName(String name) {
        Character.name = name;
    }
    public static int getLevel() {
        return level;
    }
    public static void setLevel(int level) {
        Character.level = level;
    }
    public static int getStrength() {
        return strength;
    }
    public static void setStrength(int strength) {
        Character.strength = strength;
    }
    public static int getDexterity() {
        return dexterity;
    }
    public static void setDexterity(int dexterity) {
        Character.dexterity = dexterity;
    }
    public static int getVitality() {
        return vitality;
    }
    public static void setVitality(int vitality) {
        Character.vitality = vitality;
    }
    public static int getDamage() {
        return Math.max(1, (int) Math.floor(strength * level / 100));
    }
    public static int getAc() {
        return (int) Math.floor(dexterity / 5);
    }
    public static int getMaxHP() {
        return (vitality * 2) + (level * 2) + 18;
    }
    public static int getCurHP() {
        return curHP;
    }
    public static void setCurHP(int curHP) {
        Character.curHP = curHP;
    }
    public static int getXp() {
        return xp;
    }
    public static void setXp(int xp) {
        Character.xp = xp;
    }
    public static int getMoney() {
        return money;
    }
    public static void setMoney(int money) {
        Character.money = money;
    }
    public static Item getWeapon() {
        return weapon;
    }
    public static void setWeapon(Item weapon) {
        Character.weapon = weapon;
    }
    public static Item getArmor() {
        return armor;
    }
    public static void setArmor(Item armor) {
        Character.armor = armor;
    }
    public static List<Item> getInventory() {
        return inventory;
    }
    public static void setInventory(List<Item> inventory) {
        Character.inventory = inventory;
    }
}