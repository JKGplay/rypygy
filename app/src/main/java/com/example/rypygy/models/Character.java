package com.example.rypygy.models;

public class Character {
    private static String name;
    private static int level = 9;
    private static int strength = 30;
    private static int dexterity = 20;
    private static int vitality = 25;
    private static int curHP = getMaxHP();
    private static int xp = 0;
    private static int gold = 10000;
    //TODO: dodanie listy z modyfikacjami (10% damage boost przeciwko Pierwszakom, + do obrażeń bez broni itd.)

    //https://www.lurkerlounge.com/diablo/jarulf/jarulf162.pdf

    public static boolean toHit(int ACmonster) {
        //min(max(toHit, 5), 95)
        return Rnd.rnd(1, 100) <= Math.min(Math.max(70 + dexterity / 2 + level - ACmonster, 5), 95);
    }

    public static void addXp(int i) {
        xp += i;
    }
    public static void addGold(int i) {
        gold += i;
    }
    public static void removeGold(int i) {
        gold -= i;
    }
    public static int getDamage() {
        return Math.max(1, strength * level / 100);
    }
    public static int getAc() {
        return dexterity / 5;
    }
    public static int getMaxHP() {
        return (vitality * 2) + (level * 2) + 18;
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
    public static int getGold() {
        return gold;
    }
    public static void setGold(int gold) {
        Character.gold = gold;
    }
}