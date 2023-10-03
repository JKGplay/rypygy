package com.example.rypygy.models;

import java.io.Serializable;

import javax.security.auth.callback.CallbackHandler;

public class Character implements Serializable {
    private static String name;
    private static int maxhp;
    private static int curhp;
    private static int xp;
    private static int level;
    private static int attack;
    private static int defense;
    private static int money;

    public Character(String name) {
        Character.name = name;
        maxhp = 10;
        curhp = maxhp;
        xp = 0;
        level = 1;
        attack = level;
        defense = level;
        money = 10;
    }

    public int attack() {
        return (int) (getAttack() + (Math.random() * (getLevel()+1)));
    }

    public void defend() {
        setDefense((int) ((getLevel()*2) + (Math.random() * (getLevel()+1))+1));
    }

    public Character() {}

    public String getName() {
        return name;
    }
    public int getMaxhp() {
        return maxhp;
    }
    public int getCurhp() {
        return curhp;
    }
    public int getXp() {
        return xp;
    }
    public int getLevel() {
        return level;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getMoney() {
        return money;
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