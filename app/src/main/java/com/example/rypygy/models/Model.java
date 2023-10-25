package com.example.rypygy.models;

import java.util.Random;

abstract public class Model {
    protected static int rnd(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
