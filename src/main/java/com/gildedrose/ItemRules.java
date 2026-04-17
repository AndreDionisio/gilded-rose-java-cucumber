package com.gildedrose;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public final class ItemRules {

    private ItemRules() {
    }

    static void zeroQuality(Item item) {
        item.quality = 0;
    }
    static void increaseQuality(Item item) {
        item.quality = Math.min(50, item.quality+1);
    }
    static void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(0, item.quality - amount);
    }
    static void decreaseQuality(Item item) {
        decreaseQuality(item,1);
    }
    static void decreaseSellIn(Item item) {
        item.sellIn--;
    }

    static final BiPredicate<Item, Integer> hasFewerDaysThan = (item, days) -> item.sellIn < days;
    static final Predicate<Item> isMinQuality = item -> item.quality <= 0;
    static final Predicate<Item> isMaxQuality = item -> item.quality >= 50;
    static final Predicate<Item> isExpired = item -> item.sellIn < 0;
}