package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;

public final class AgedBrie implements Update{
    public BetterItem update(BetterItem item) {
        BetterItem updated = item.withQuality(item.quality().increase());

        updated = updated.tick();

        if (updated.expiration().isExpired()) {
            updated = updated.withQuality(updated.quality().increase());
        }

        return updated;
    }
}
