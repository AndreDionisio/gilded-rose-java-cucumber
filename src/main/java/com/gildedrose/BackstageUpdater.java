package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class BackstageUpdater extends AbstractItemUpdater {
    @Override
    protected void applyPreUpdateLogic(Item item) {
        if (isQualityLessThan50(item)) {
            increaseQuality(item);

            if (isSellInLessThan11(item) && isQualityLessThan50(item)) {
                increaseQuality(item);
            }

            if (isSellInLessThan6(item) && isQualityLessThan50(item)) {
                increaseQuality(item);
            }
        }
    }

    @Override
    protected void applyPostUpdateLogic(Item item) {
        if (isSellInLessThan0(item)) {
            zeroQuality(item);
        }
    }
}
