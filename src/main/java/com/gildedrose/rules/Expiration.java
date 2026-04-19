package com.gildedrose.rules;

public record Expiration(int days) {
    private static final int MINIMAL = 0;
    private static final int STEP = 1;

    public Expiration nextDay() {
        return new Expiration(days - STEP);
    }

    public boolean isExpired() { return days < MINIMAL; }

    public boolean hasFewerDaysThan(int threshold) { return days < threshold; }
}