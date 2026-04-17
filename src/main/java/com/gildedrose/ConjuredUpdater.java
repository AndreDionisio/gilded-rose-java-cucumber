package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class ConjuredUpdater extends AbstractItemUpdater {
    @Override
    protected void applyPreUpdateLogic(Item item) {
        decreaseQuality(item, 2);
    }
    @Override
    protected void applyPostUpdateLogic(Item item){
        if (isMinQuality.negate().and(isExpired).test(item)) {
            decreaseQuality(item, 2);
        }
    }
}
