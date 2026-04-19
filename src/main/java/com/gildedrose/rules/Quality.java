package com.gildedrose.rules;

public record Quality(int value) {
    public static final int MINIMAL = 0;
    public static final int MAXIMAL = 50;
    public static final int LEGENDARY_QUALITY = 80;
    public static final int DOUBLE_QUALITY_THRESHOLD = 11;
    public static final int TRIPLE_QUALITY_THRESHOLD = 6;
    public static final int STEP = 1;
    public static final int INITIAL_VALUE = 0;
    public Quality {
        if (value != LEGENDARY_QUALITY) {
            value = Math.clamp(value, MINIMAL, MAXIMAL);
        }
    }
    public Quality increase() {
        return new Quality(value + STEP);
    }

    public Quality decrease(int amount) {
        return new Quality(value - amount);
    }

    public Quality reset() {
        return new Quality(INITIAL_VALUE);
    }


}


