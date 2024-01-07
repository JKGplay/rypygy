package com.example.rypygy.models;

import com.example.rypygy.enums.EnemyType;

import java.util.Objects;

public class Enemy {

    private String name;
    private int maxHP;
    private int curHP;
    private int level;
    private int minDamage;
    private int maxDamage;
    private int ac;
    private int toHitBase;
    private int xp;

    public boolean toHit(int LVplayer, int ACplayer) {
        return Rnd.rnd(1, 100) <= Math.max(15, (int) Math.floor(30 + toHitBase + (double) (2 * (level - LVplayer)) - ACplayer));
    }

    public int damage() {
        return Rnd.rnd(minDamage, maxDamage);
    }

    public Enemy(EnemyType type) {
        switch (Objects.requireNonNull(type)) {
            case FIRST_YEAR:
                this.name = "First-Year";
                maxHP = Rnd.rnd(17, 35);
                curHP = maxHP;
                level = 1;
                minDamage = 5;
                maxDamage = 14;
                ac = 30;
                toHitBase = 40;
                xp = 35;
                break;
            case SMOKER:
            case MILITARY:
            case GRAPHIC_DESIGNER:
            case FIFTH_YEAR:
                name = "";
                maxHP = 0;
                curHP = maxHP;
                level = 0;
                minDamage = 0;
                maxDamage = 0;
                ac = 0;
                toHitBase = 0;
                xp = 0;
                break;
        }
    }

    public String getName() {
        return name;
    }
    public int getMaxHP() {
        return maxHP;
    }
    public int getCurHP() {
        return curHP;
    }
    public void setCurHP(int curHP) {
        this.curHP = curHP;
    }
    public int getLevel() {
        return level;
    }
    public int getAc() {
        return ac;
    }
    public int getToHitBase() {
        return toHitBase;
    }
    public int getXp() {
        return xp;
    }
}