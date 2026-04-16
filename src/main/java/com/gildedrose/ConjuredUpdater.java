package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class ConjuredUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        decreaseQuality(item, 2);
        decreaseSellIn(item);
        if (isSellInLessThan0(item) && isQualityGreaterThan0(item)) {
            decreaseQuality(item, 2);
        }
    }
}
