package com.gildedrose.rules;

import static com.gildedrose.constants.DomainConstants.MINIMAL;
import static com.gildedrose.constants.DomainConstants.STEP;

public record Expiration(int days) {

    public Expiration nextDay() {
        return new Expiration(days - Math.abs(STEP));
    }

    public boolean isExpired() { return days < MINIMAL; }

    public boolean hasFewerDaysThan(int threshold) { return days < threshold; }
}