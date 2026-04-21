package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;

import static com.gildedrose.constants.DomainConstants.DOUBLE_QUALITY_THRESHOLD;
import static com.gildedrose.constants.DomainConstants.TRIPLE_QUALITY_THRESHOLD;

public final class Backstage implements Update{

    public BetterItem update(BetterItem item) {
        BetterItem updated = item.withQuality(item.quality().increase());

        if (item.expiration().hasFewerDaysThan(DOUBLE_QUALITY_THRESHOLD)) {
            updated = updated.withQuality(updated.quality().increase());
        }

        if (item.expiration().hasFewerDaysThan(TRIPLE_QUALITY_THRESHOLD)) {
            updated = updated.withQuality(updated.quality().increase());
        }

        updated = updated.tick();

        if (updated.expiration().isExpired()) {
            updated = updated.withQuality(updated.quality().reset());
        }

        return updated;
    }
}
