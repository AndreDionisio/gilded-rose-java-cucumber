package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class AgedBrieUpdater extends AbstractItemUpdater {

    @Override
    protected void applyPreUpdateLogic(Item item) {
        if (isMaxQuality.negate().test(item)) {
            increaseQuality(item);
        }
    }

    @Override
    protected void applyPostUpdateLogic(Item item) {
        // The "expired" logic
        if (isMaxQuality.negate().and(isExpired).test(item)) {
            increaseQuality(item);
        }
    }
}
