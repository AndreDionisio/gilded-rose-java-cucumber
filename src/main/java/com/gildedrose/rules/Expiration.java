package com.gildedrose.rules;

import static com.gildedrose.rules.DomainConstants.MINIMAL;
import static com.gildedrose.rules.DomainConstants.STEP;

public record Expiration(int days) {

    public Expiration nextDay() {
        return new Expiration(days - STEP);
    }

    public boolean isExpired() { return days < MINIMAL; }

    public boolean hasFewerDaysThan(int threshold) { return days < threshold; }
}