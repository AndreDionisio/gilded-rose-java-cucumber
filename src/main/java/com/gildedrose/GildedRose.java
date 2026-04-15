package com.gildedrose;

import static com.gildedrose.ItemRules.*;

class GildedRose {
    static Item[] items;

    public GildedRose(Item[] items) {
        GildedRose.items = items;
    }
    public static void updateQuality() {
        for (Item item : items) {
            handleUpdate(item);
        }
    }

    private static void handleUpdate(Item item) {
        // 1. Update Quality
        switch (item.name) {
            case "Sulfuras, Hand of Ragnaros" -> { return; }
            case "Aged Brie" -> updateAgedBrie(item);
            case "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePasses(item);
            case String s when s.startsWith("Conjured") -> updateConjured(item);
            default -> updateNormalItem(item);
        }

        handleExpiration(item);
    }
    private static void updateNormalItem(Item item){
        if (isQualityGreaterThan0(item)) {
            decreaseQuality(item);
        }
    }
    private static void updateBackstagePasses(Item item){
        if (isQualityLessThan50(item)) {
            increaseQuality(item);

            if (isSellInLessThan11(item) && isQualityLessThan50(item)) {
                increaseQuality(item);
            }

            if (isSellInLessThan6(item) && isQualityLessThan50(item)) {
                increaseQuality(item);
            }
        }
    }
    private static void handleExpiration(Item item){
        decreaseSellIn(item);
        if (isSellInLessThan0(item)) {
            if (!isAgedBrie(item)) {
                if (!isBackstage(item)) {
                    if (isQualityGreaterThan0(item)) {
                        if (!isSulfuras(item)) {
                            decreaseQuality(item);
                        }
                    }
                } else {
                    zeroQuality(item);
                }
            } else {
                if (isQualityLessThan50(item)) {
                    increaseQuality(item);
                }
            }
        }
    }
    private static void updateConjured(Item item) {
        decreaseQuality(item, 2);
    }
    private static void updateAgedBrie(Item item){
        if (isQualityLessThan50(item)) {
            increaseQuality(item);
        }
    }


}
