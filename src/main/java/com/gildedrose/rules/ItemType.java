package com.gildedrose.rules;

import com.gildedrose.updater.*;
import java.util.Arrays;
import java.util.function.Supplier;

public enum ItemType {
    AGED_BRIE("Aged Brie", AgedBrie::new),
    BACKSTAGE("Backstage passes to a TAFKAL80ETC concert", Backstage::new),
    SULFURAS("Sulfuras, Hand of Ragnaros", Legendary::new),
    CONJURED("Conjured", Conjured::new),
    DEFAULT("", Default::new);

    private final String name;
    private final Supplier<Update> updaterSupplier;

    ItemType(String name, Supplier<Update> updaterSupplier) {
        this.name = name;
        this.updaterSupplier = updaterSupplier;
    }

    public static Update getUpdaterFor(String itemName) {
        if (itemName.startsWith("Conjured")) return new Conjured();

        return Arrays.stream(values())
                .filter(type -> type.name.equals(itemName))
                .findFirst()
                .map(type -> type.updaterSupplier.get())
                .orElseGet(Default::new);
    }
}