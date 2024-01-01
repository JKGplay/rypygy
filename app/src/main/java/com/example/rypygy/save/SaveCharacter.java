package com.example.rypygy.save;

import com.example.rypygy.models.Character;

public class SaveCharacter {
    private String name;
    private int level;
    private int xp;
    private int strength;
    private int dexterity;
    private int vitality;
    private int curHP;
    private int gold;

    public SaveCharacter() {
        name = Character.getName();
        level = Character.getLevel();
        xp = Character.getXp();
        strength = Character.getStrength();
        dexterity = Character.getDexterity();
        vitality = Character.getVitality();
        curHP = Character.getCurHP();
        gold = Character.getGold();
    }

    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public int getXp() {
        return xp;
    }
    public int getStrength() {
        return strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public int getVitality() {
        return vitality;
    }
    public int getCurHP() {
        return curHP;
    }
    public int getGold() {
        return gold;
    }

    @Override
    public String toString() {
        return "SaveCharacter{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", xp=" + xp +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", vitality=" + vitality +
                ", curHP=" + curHP +
                ", gold=" + gold +
                '}';
    }
}
