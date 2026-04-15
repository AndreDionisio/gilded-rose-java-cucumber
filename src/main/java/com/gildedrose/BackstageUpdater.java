package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class BackstageUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {

        if (isQualityLessThan50(item)) {
            increaseQuality(item);

            if (isSellInLessThan11(item) && isQualityLessThan50(item)) {
                increaseQuality(item);
            }

            if (isSellInLessThan6(item) && isQualityLessThan50(item)) {
                increaseQuality(item);
            }
        }

        decreaseSellIn(item);

        if (isSellInLessThan0(item)) {
            zeroQuality(item);
        }
    }
}
