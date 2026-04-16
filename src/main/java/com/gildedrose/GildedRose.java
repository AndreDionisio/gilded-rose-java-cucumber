package com.gildedrose;

class GildedRose {
    private GildedRose() {
        /* This utility class should not be instantiated */
    }

    public static Item[] getItems() {
        return items;
    }

    public static void setItems(Item[] items) {
        GildedRose.items = items;
    }

    private static Item[] items;

    public static void updateQuality() {
        for (Item item : items) {
            handleUpdate(item);
        }
    }

    private static void handleUpdate(Item item) {
        switch (item.name) {
            case "Sulfuras, Hand of Ragnaros" -> {/*Never Change*/}
            case "Aged Brie" ->  new AgedBrieUpdater().update(item);
            case "Backstage passes to a TAFKAL80ETC concert" -> new BackstageUpdater().update(item);
            case String s when s.startsWith("Conjured") -> new ConjuredUpdater().update(item);
            default -> new DefaultUpdater().update(item);
        }
    }
}
