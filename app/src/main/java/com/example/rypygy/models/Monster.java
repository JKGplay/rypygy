package com.example.rypygy.models;

public class Monster {
    private String name;
    private int maxhp;
    private int curhp;
    private int level;
    private int attack;
    private int defense;

    public Monster(String name, int maxhp, int level, int attack, int defense) {
        this.name = name;
        this.maxhp = maxhp;
        curhp = maxhp;
        this.level = level;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }
    public int getMaxhp() {
        return maxhp;
    }
    public int getCurhp() {
        return curhp;
    }
    public void setCurhp(int curhp) {
        this.curhp = curhp;
    }
    public int getLevel() {
        return level;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
}