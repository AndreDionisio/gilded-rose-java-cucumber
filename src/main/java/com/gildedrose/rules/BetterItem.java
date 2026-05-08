package com.gildedrose.rules;

import java.io.Serializable;

public record BetterItem(String name, Expiration expiration, Quality quality) implements Serializable {

    public BetterItem tick() {
        return new BetterItem(name, expiration.nextDay(), quality);
    }
    public BetterItem withQuality(Quality newQuality) {
        return new BetterItem(name, expiration, newQuality);
    }
}
