package com.example.rypygy.models;

abstract public class Monster extends Model {
    protected String name;
    protected int maxHP;
    protected int curHP;
    protected int level;
    protected int minDamage;
    protected int maxDamage;
    protected int ac;
    protected int toHitBase;
    protected int xp;

    public boolean toHit(int LVplayer, int ACplayer) {
        return rnd(1, 100) <= Math.max(15, (int) Math.floor(30 + toHitBase + (double) (2 * (level - LVplayer)) - ACplayer));
    }

    public int damage() {
        return rnd(minDamage, maxDamage);
    }

    public Monster(String name, int maxhp, int level, int minDamage, int maxDamage, int ac, int toHitBase, int xp) {
        this.name = name;
        this.maxHP = maxhp;
        this.curHP = maxhp;
        this.level = level;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.ac = ac;
        this.toHitBase = toHitBase;
        this.xp = xp;
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