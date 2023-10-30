package com.example.rypygy.models;

public class Character extends Model {
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

    private static Object[] inventory = {};

    //https://www.lurkerlounge.com/diablo/jarulf/jarulf162.pdf

    public static boolean toHit(int ACmonster) {
        //min(max(toHit, 5), 95)
        return rnd(1, 100) <= Math.min(Math.max((int) Math.floor(70 + (double) dexterity / 2 + level - ACmonster), 5), 95);
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
}