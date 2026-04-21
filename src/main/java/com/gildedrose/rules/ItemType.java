package com.gildedrose.rules;

import com.gildedrose.constants.DomainConstants;
import com.gildedrose.updater.*;
import java.util.Arrays;
import java.util.function.Supplier;

public enum ItemType {
    AGED_BRIE(DomainConstants.AGED_BRIE, AgedBrie::new),
    BACKSTAGE(DomainConstants.BACKSTAGE, Backstage::new),
    SULFURAS(DomainConstants.SULFURAS, Legendary::new),
    CONJURED(DomainConstants.CONJURED, Conjured::new),
    DEFAULT(DomainConstants.DEFAULT, Default::new);

    private final String name;
    private final Supplier<Update> updaterSupplier;

    ItemType(String name, Supplier<Update> updaterSupplier) {
        this.name = name;
        this.updaterSupplier = updaterSupplier;
    }

    public static Update getUpdaterFor(String itemName) {
        if (itemName.startsWith(DomainConstants.CONJURED)) return new Conjured();

        return Arrays.stream(values())
                .filter(type -> type.name.equals(itemName))
                .findFirst()
                .map(type -> type.updaterSupplier.get())
                .orElseGet(Default::new);
    }
}