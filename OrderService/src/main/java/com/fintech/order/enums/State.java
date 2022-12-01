package com.fintech.order.enums;

import java.util.Random;

public enum State {
    NEW,FAILED,DONE;
    private static final Random RNG = new Random();

    public static State randomStatus()  {
        State[] states = values();
        return states[RNG.nextInt(states.length)];
    }
}
