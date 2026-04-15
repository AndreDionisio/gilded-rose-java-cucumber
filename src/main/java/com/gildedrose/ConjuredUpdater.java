package com.gildedrose;

import static com.gildedrose.ItemRules.*;
import static com.gildedrose.ItemRules.isQualityGreaterThan0;


public class ConjuredUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        decreaseQuality(item, 2);
        decreaseSellIn(item);
        if (isSellInLessThan0(item) && isQualityGreaterThan0(item)) {
            decreaseQuality(item);
        }
    }
}
