package com.gildedrose;

import static com.gildedrose.ItemRules.*;

public class AgedBrieUpdater  implements ItemUpdater {

    @Override
    public void update(Item item) {

        if (isQualityLessThan50(item)) {
            increaseQuality(item);
        }

        decreaseSellIn(item);

        if (isSellInLessThan0(item) && isQualityLessThan50(item)) {
            increaseQuality(item);
        }
    }
}
