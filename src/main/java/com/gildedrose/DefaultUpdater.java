package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class DefaultUpdater extends AbstractItemUpdater {
    @Override
    protected void applyPreUpdateLogic(Item item) {
        if (isMinQuality.negate().test(item)) {
            decreaseQuality(item);
        }
    }
    @Override
    protected void applyPostUpdateLogic(Item item){
        if (isMinQuality.negate().and(isExpired).test(item)) {
            decreaseQuality(item);
        }
    }
}
