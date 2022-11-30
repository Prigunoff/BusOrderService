package com.fintech.order.enums;

import java.util.Random;

public enum Status {
    NEW, DONE, FAILED;
    private static final Random RNG = new Random();

    public static Status randomStatus() {
        Status[] directions = values();
        return directions[RNG.nextInt(directions.length)];
    }
}
