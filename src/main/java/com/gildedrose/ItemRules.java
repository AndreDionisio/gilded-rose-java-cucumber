package com.gildedrose;

public final class ItemRules {

    static void zeroQuality(Item item) {
        item.quality = 0;
    }
    static void increaseQuality(Item item) {
        item.quality += 1;
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
    static boolean isQualityLessThan50(Item item){
        return item.quality < 50;
    }
    static boolean isQualityGreaterThan0(Item item){
        return item.quality > 0;
    }
    static boolean isSellInLessThan11(Item item){
        return item.sellIn < 11;
    }
    static boolean isSellInLessThan6(Item item){
        return item.sellIn < 6;
    }
    static boolean isSellInLessThan0(Item item){
        return item.sellIn < 0;
    }
}