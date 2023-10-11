package com.example.rypygy.models;

abstract public class Monster implements Model{
    protected String name;
    protected int maxhp;
    protected int curhp;
    protected int level;
    protected int attack;
    protected int defense;

    abstract public int attack();
    abstract public void defend();

    public Monster(String name, int maxhp, int level, int attack, int defense) {
        this.name = name;
        this.maxhp = maxhp;
        this.curhp = maxhp;
        this.level = level;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMaxhp() {
        return maxhp;
    }
    public void setMaxhp(int maxhp) {
        this.maxhp = maxhp;
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
    public void setLevel(int level) {
        this.level = level;
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