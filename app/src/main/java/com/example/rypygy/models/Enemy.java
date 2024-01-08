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
    //TODO: dodać statystyki przeciwników (może jeszcze jakiś loot)
    public Enemy(EnemyType type) {
        switch (Objects.requireNonNull(type)) {
            case FIRST_YEAR:
                this.name = "First-Year";
                maxHP = Rnd.rnd(7, 12);
                curHP = maxHP;
                level = 1;
                minDamage = 3;
                maxDamage = 10;
                ac = 15;
                toHitBase = 25;
                xp = 35;
                break;
            case SMOKER:
                name = "Smoker";
                maxHP = Rnd.rnd(12, 20);
                curHP = maxHP;
                level = 2;
                minDamage = 4;
                maxDamage = 12;
                ac = 20;
                toHitBase = 30;
                xp = 35;
                break;
            case GRAPHIC_DESIGNER:
                name = "Graphic Designer";
                maxHP = Rnd.rnd(17, 25);
                curHP = maxHP;
                level = 3;
                minDamage = 5;
                maxDamage = 14;
                ac = 30;
                toHitBase = 40;
                xp = 35;
                break;
            case FIFTH_YEAR:
                name = "Fifth-Year";
                maxHP = Rnd.rnd(20, 30);
                curHP = maxHP;
                level = 4;
                minDamage = 8;
                maxDamage = 16;
                ac = 30;
                toHitBase = 40;
                xp = 35;
                break;
            case JANITOR:
                name = "Janitor";
                maxHP = Rnd.rnd(25, 32);
                curHP = maxHP;
                level = 5;
                minDamage = 10;
                maxDamage = 20;
                ac = 40;
                toHitBase = 50;
                xp = 35;
                break;
            case MILITARY:
                name = "Military Man";
                maxHP = Rnd.rnd(30, 40);
                curHP = maxHP;
                level = 6;
                minDamage = 12;
                maxDamage = 24;
                ac = 50;
                toHitBase = 55;
                xp = 35;
                break;
            case KUNCZKA:
                name = "Kunczka, the Dark Lord";
                maxHP = 110;
                curHP = maxHP;
                level = 7;
                minDamage = 8;
                maxDamage = 16;
                ac = 70;
                toHitBase = 70;
                xp = 1;
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