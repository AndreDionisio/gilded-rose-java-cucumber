package com.gildedrose;

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
        switch (item.name) {
            case "Sulfuras, Hand of Ragnaros" -> {}
            case "Aged Brie" ->  new AgedBrieUpdater().update(item);
            case "Backstage passes to a TAFKAL80ETC concert" -> new BackstageUpdater().update(item);
            case String s when s.startsWith("Conjured") -> new ConjuredUpdater().update(item);
            default -> new DefaultUpdater().update(item);
        }
    }
}
