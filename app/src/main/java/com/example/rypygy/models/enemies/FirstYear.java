package com.example.rypygy.models.enemies;

import com.example.rypygy.models.Monster;
import com.example.rypygy.models.Rnd;

public class FirstYear extends Monster {

    public FirstYear() {
        super("First-Year", Rnd.rnd(17, 25), 1, 5, 14, 30, 40, 35);
    }
}
