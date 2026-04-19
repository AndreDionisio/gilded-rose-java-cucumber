package com.gildedrose;

import com.gildedrose.rules.*;
import com.gildedrose.updater.*;
import static com.gildedrose.rules.ItemRules.*;

import java.util.Map;

class GildedRose {
    private GildedRose() {
    }
    private static Item[] items;

    private static final Map<String, Object> UPDATERS = Map.of(
            AGED_BRIE, new AgedBrie(),
            BACKSTAGE, new Backstage()
    );

    public static void updateQuality() {
        for (Item item : items) {
            processItem(item);
        }
    }

    private static void processItem(Item item) {
        BetterItem better = toBetterItem(item);

        better = evolveState(better);

        item.quality = better.quality().value();
        item.sellIn = better.expiration().days();
    }

    private static BetterItem evolveState(BetterItem better) {
        if (better.name().equals(SULFURAS)) return better;
        if (better.name().startsWith(CONJURED_PREFIX)) return new Conjured().update(better);

        return ((Update)UPDATERS.getOrDefault(better.name(),new Default())).update(better);
    }

    private static BetterItem toBetterItem(Item item) {
        return new BetterItem(item.name, new Expiration(item.sellIn), new Quality(item.quality));
    }

    public static void setItems(Item[] items) { GildedRose.items = items; }
    public static Item[] getItems(){
        return items;
    }
}