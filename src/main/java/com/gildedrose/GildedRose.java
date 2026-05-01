package com.gildedrose;

import com.gildedrose.rules.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
class GildedRose {
    private static Item[] items;

    private GildedRose() {
    }

    public static void updateQuality() {
        Arrays.stream(items).forEach(GildedRose::processItem);
    }

    private static void processItem(Item item) {
        BetterItem better = toBetterItem(item);

        better = ItemType.getUpdaterFor(better.name()).update(better);

        item.quality = better.quality().value();
        item.sellIn = better.expiration().days();
    }

    private static BetterItem toBetterItem(Item item) {
        return new BetterItem(item.name, new Expiration(item.sellIn), new Quality(item.quality));
    }

    public static void setItems(Item[] items) { GildedRose.items = items; }
    public static void setItems(List<Item> items) { GildedRose.items = items.toArray(Item[]::new); }
    public static Item[] getItems() { return items; }
}