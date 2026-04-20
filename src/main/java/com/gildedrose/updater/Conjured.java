package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;

public final class Conjured implements Update{
    public BetterItem update(BetterItem item) {
        BetterItem updated = item.withQuality(item.quality().decreaseAmount(2));
        updated = updated.tick();

        if (updated.expiration().isExpired()) {
            updated = updated.withQuality(updated.quality().decreaseAmount(2));
        }
        return updated;
    }
}
