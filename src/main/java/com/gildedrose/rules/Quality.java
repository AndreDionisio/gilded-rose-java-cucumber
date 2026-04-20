package com.gildedrose.rules;

import static com.gildedrose.rules.DomainConstants.*;

public record Quality(int value) {

    public Quality {
        if (value != LEGENDARY_QUALITY) {
            value = Math.clamp(value, MINIMAL, MAXIMAL);
        }
    }
    public Quality increase() {
        return new Quality(value + Math.abs(STEP));
    }

    public Quality decrease() {
        return decreaseAmount(STEP);
    }

    public Quality decreaseAmount(int amount) {
        return new Quality(value - Math.abs(amount));
    }

    public Quality reset() {
        return new Quality(INITIAL_VALUE);
    }


}


