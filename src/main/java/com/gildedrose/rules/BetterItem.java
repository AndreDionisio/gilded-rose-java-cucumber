package com.gildedrose.rules;

public record BetterItem(String name, Expiration expiration, Quality quality) {

    public BetterItem tick() {
        return new BetterItem(name, expiration.nextDay(), quality);
    }
    public BetterItem withQuality(Quality newQuality) {
        return new BetterItem(name, expiration, newQuality);
    }
}
