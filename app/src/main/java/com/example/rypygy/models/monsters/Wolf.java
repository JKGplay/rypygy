package com.example.rypygy.models.monsters;

import com.example.rypygy.models.Monster;

public class Wolf extends Monster {

    public Wolf(int level) {
        super("Wolf", level*10, level, level*2, level*2);
    }

    public int action() {
        if(getCurhp() < getMaxhp()/3) {
            return attack()+getLevel();
        } else {
            if(Math.random()>0.5) {
                return attack();
            } else {
                setDefense((int) ((getLevel()*2) + (Math.random() * (getLevel()+1))+1));
                return 0;
            }
        }
    }

    @Override
    public int attack() {
        return (int) (getAttack() + (Math.random() * (getLevel()+1)));
    }

    @Override
    public void defend() {

    }
}
