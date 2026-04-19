package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class BackstageUpdater extends AbstractItemUpdater {
    @Override
    protected void applyPreUpdateLogic(Item item) {
        if (isMaxQuality.negate().test(item)) {
            increaseQuality(item);

            if (hasFewerDaysThan.test(item, 11) && isMaxQuality.negate().test(item)) {
                increaseQuality(item);
            }

            if (hasFewerDaysThan.test(item, 6) && isMaxQuality.negate().test(item)) {
                increaseQuality(item);
            }
        }
    }

    @Override
    protected void applyPostUpdateLogic(Item item) {
        if (isExpired.test(item)) {
            zeroQuality(item);
        }
    }
}
