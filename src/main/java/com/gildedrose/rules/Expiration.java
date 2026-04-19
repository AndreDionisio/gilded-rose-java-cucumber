package com.gildedrose.rules;

public record Expiration(int days) {
    public static final int MINIMAL = 0;
    public static final int STEP = 1;
    public static final int WEEKEND = 2;
    public static final int BIG_WEEKEND = 3;
    public static final int YESTERDAY = -1;
    public static final int WORKDAYS = 5;
    public static final int SPRINT = 10;
    public static final int VACATION = 15;
    public Expiration nextDay() {
        return new Expiration(days - STEP);
    }

    public boolean isExpired() { return days < MINIMAL; }

    public boolean hasFewerDaysThan(int threshold) { return days < threshold; }
}