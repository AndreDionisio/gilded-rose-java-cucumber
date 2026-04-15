package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class DefaultUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {

        if (isQualityGreaterThan0(item)) {
            decreaseQuality(item);
        }

        decreaseSellIn(item);

        if (isSellInLessThan0(item) && isQualityGreaterThan0(item)) {
            decreaseQuality(item);
        }
    }
}
