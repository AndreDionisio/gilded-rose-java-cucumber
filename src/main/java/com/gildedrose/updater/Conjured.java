package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;

import static com.gildedrose.constants.DomainConstants.CONJURED_STEP;

public final class Conjured implements Update{
    public BetterItem update(BetterItem item) {
        BetterItem updated = item.withQuality(item.quality().decreaseAmount(CONJURED_STEP));
        updated = updated.tick();

        if (updated.expiration().isExpired()) {
            updated = updated.withQuality(updated.quality().decreaseAmount(CONJURED_STEP));
        }
        return updated;
    }
}
