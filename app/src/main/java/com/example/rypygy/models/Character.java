package com.example.rypygy.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Character {
    private static String name;
    private static int level = 1;
    private static int strength = 30;
    private static int dexterity = 20;
    private static int vitality = 25;
    private static int damage = Math.max(1, (int) Math.floor(strength * level / 100));
    private static int ac = (int) Math.floor(dexterity / 5);
    private static int maxhp = (vitality * 2) + (level * 2) + 18;
    private static int curhp = maxhp;
    private static int xp = 0;
    private static int money = 0;
    private static Item weapon = new Item("Short sword", Item.Category.WEAPON, 50,
            new HashMap<Item.Attribute, Double>() {{
                put(Item.Attribute.MinDMG, 2.0);
                put(Item.Attribute.MaxDMG, 6.0);
            }}
    );
    private static Item armor = new Item("Rags", Item.Category.ARMOR, 5,
            new HashMap<Item.Attribute, Double>() {{
                put(Item.Attribute.MinAC, 2.0);
                put(Item.Attribute.MaxAC, 6.0);
            }}
    );
    private static Item[] inventory = {
            weapon,
            armor,
            new Item("Small Potion", Item.Category.POTION, 25,
                    new HashMap<Item.Attribute, Double>() {{
                        put(Item.Attribute.Size, 0.25);
                    }}
            ),
            new Item("Medium Potion", Item.Category.POTION, 50,
                    new HashMap<Item.Attribute, Double>() {{
                        put(Item.Attribute.Size, 0.5);
                    }}
            ),
            new Item("Big Potion", Item.Category.POTION, 75,
                    new HashMap<Item.Attribute, Double>() {{
                        put(Item.Attribute.Size, 0.75);
                    }}
            )
    };

    //https://www.lurkerlounge.com/diablo/jarulf/jarulf162.pdf

    public static boolean toHit(int ACmonster) {
        //min(max(toHit, 5), 95)
        return Rnd.rnd(1, 100) <= Math.min(Math.max((int) Math.floor(70 + (double) dexterity / 2 + level - ACmonster), 5), 95);
    }

    public static int getIndexOf(String name) {
        for (Item item: Character.getInventory()) {
            if (item.getName().equals(name)) {
                return Arrays.asList(Character.getInventory()).indexOf(item);
            }
        }
        return -1;
    }

    public static void removeItem(int index) {
        Item[] temp = new Item[Character.getInventory().length - 1];
        for (int i = 0, k = 0; i < Character.getInventory().length; i++) {
            if (i == index) {
                continue;
            }
            temp[k++] = Character.getInventory()[i];
        }
        Character.setInventory(temp);
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
        return damage;
    }
    public static void setDamage(int damage) {
        Character.damage = damage;
    }
    public static int getAc() {
        return ac;
    }
    public static void setAc(int ac) {
        Character.ac = ac;
    }
    public static int getMaxhp() {
        return maxhp;
    }
    public static void setMaxhp(int maxhp) {
        Character.maxhp = maxhp;
    }
    public static int getCurhp() {
        return curhp;
    }
    public static void setCurhp(int curhp) {
        Character.curhp = curhp;
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
    public static Item[] getInventory() {
        return inventory;
    }
    public static void setInventory(Item[] inventory) {
        Character.inventory = inventory;
    }
}