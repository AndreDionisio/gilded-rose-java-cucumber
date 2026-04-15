package com.gildedrose;

public final class ItemRules {


    static boolean isAgedBrie(Item item) {
        return "Aged Brie".equals(item.name);
    }
    static boolean isBackstage(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.name);
    }
    static boolean isSulfuras(Item item) {
        return "Sulfuras, Hand of Ragnaros".equals(item.name);
    }
    static void zeroQuality(Item item) {
        item.quality = 0;
    }
    static void increaseQuality(Item item) {
        item.quality += 1;
    }
    static void decreaseQuality(Item item) {
        item.quality -= 1;
    }
    static void decreaseSellIn(Item item) {
        item.sellIn -= 1;
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