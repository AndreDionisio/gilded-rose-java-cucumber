package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class DefaultUpdater extends AbstractItemUpdater {
    @Override
    protected void applyPreUpdateLogic(Item item) {
        if (isQualityGreaterThan0(item)) {
            decreaseQuality(item);
        }
    }
    @Override
    protected void applyPostUpdateLogic(Item item){
        if (isSellInLessThan0(item) && isQualityGreaterThan0(item)) {
            decreaseQuality(item);
        }
    }
}
