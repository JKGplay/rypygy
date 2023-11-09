package com.example.rypygy.models;

import java.util.Random;

public final class Rnd {
    public static int rnd(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
