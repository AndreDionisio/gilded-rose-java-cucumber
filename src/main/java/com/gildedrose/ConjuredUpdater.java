package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class ConjuredUpdater extends AbstractItemUpdater {
    @Override
    protected void applyPreUpdateLogic(Item item) {
        decreaseQuality(item, 2);
    }
    @Override
    protected void applyPostUpdateLogic(Item item){
        if (isSellInLessThan0(item) && isQualityGreaterThan0(item)) {
            decreaseQuality(item, 2);
        }
    }
}
