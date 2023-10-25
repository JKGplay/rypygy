package com.example.rypygy.models;

public class Character extends Model {
    private static String name;
    private static int level = 1;
    private static int strength = 30;
    private static int dexterity = 20;
    private static int vitality = 25;
    private static int damage = (int)  Math.floor(strength * level / 100);
    private static int ac = (int) Math.floor(dexterity / 5);
    private static int maxhp = (vitality * 2) + (level * 2) + 18;
    private static int curhp = maxhp;
    private static int xp = 0;
    private static int money = 0;

    //https://www.lurkerlounge.com/diablo/jarulf/jarulf162.pdf

    public boolean toHit(int ACmonster) {
        //min(max(toHit, 5), 95)
        return rnd(1, 100) <= Math.min(Math.max((int) Math.floor(70 + (double) dexterity / 2 + level - ACmonster), 5), 95);
    }

    public Character(String name) {
        Character.name = name;
    }
    public Character() {}

    public String getName() {
        return Character.name;
    }
    public int getMaxhp() {
        return Character.maxhp;
    }
    public int getCurhp() {
        return Character.curhp;
    }
    public int getXp() {
        return Character.xp;
    }
    public int getLevel() {
        return Character.level;
    }
    public int getStrength() {
        return Character.strength;
    }
    public int getDexterity() {
        return Character.dexterity;
    }
    public int getMoney() {
        return Character.money;
    }

    public void setCurhp(int curhp) {
        Character.curhp = curhp;
    }

    public void setXp(int xp) {
        Character.xp = xp;
    }

    public void setMoney(int money) {
        Character.money = money;
    }

    public static void setDexterity(int dexterity) {
        Character.dexterity = dexterity;
    }
}