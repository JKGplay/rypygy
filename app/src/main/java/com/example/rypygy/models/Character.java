package com.example.rypygy.models;

import java.io.Serializable;

import javax.security.auth.callback.CallbackHandler;

public class Character implements Serializable, Model {
    private static String name;
    private static int level = 1;
    private static int maxhp = level * 10;
    private static int curhp = maxhp;
    private static int xp = 0;
    private static int attack = level * 2;
    private static int defense = level * 2;
    private static int money = 10;

    public Character(String name) {
        Character.name = name;
    }

    public int attack() {
        return (int) (getAttack() + (Math.random() * (getLevel()+1)));
    }

    public void defend() {
        setDefense((int) ((getLevel()*2) + (Math.random() * (getLevel()+1))+1));
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
    public int getAttack() {
        return Character.attack;
    }
    public int getDefense() {
        return Character.defense;
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

    public static void setDefense(int defense) {
        Character.defense = defense;
    }
}