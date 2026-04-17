package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class AgedBrieUpdater extends AbstractItemUpdater {

    @Override
    protected void applyPreUpdateLogic(Item item) {
        if (isQualityLessThan50(item)) {
            increaseQuality(item);
        }
    }

    @Override
    protected void applyPostUpdateLogic(Item item) {
        // The "expired" logic
        if (isSellInLessThan0(item) && isQualityLessThan50(item)) {
            increaseQuality(item);
        }
    }
}
