package com.gildedrose;

import static com.gildedrose.ItemRules.*;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item:items) {
            if (!isAgedBrie(item)
                    && !isBackstage(item)) {
                if (isQualityGreaterThan0(item)) {
                    if (!isSulfuras(item)) {
                        decreaseQuality(item);
                    }
                }
            } else {
                if (isQualityLessThan50(item)) {
                    increaseQuality(item);

                    if (isBackstage(item)) {
                        if (isSellInLessThan11(item)) {
                            if (isQualityLessThan50(item)) {
                                increaseQuality(item);
                            }
                        }

                        if (isSellInLessThan6(item)) {
                            if (isQualityLessThan50(item)) {
                                increaseQuality(item);
                            }
                        }
                    }
                }
            }

            if (!isSulfuras(item)) {
                decreaseSellIn(item);
            }

            if (isSellInLessThan0(item)) {
                if (!isAgedBrie(item)) {
                    if (!isBackstage(item)) {
                        if (isQualityGreaterThan0(item)) {
                            if (!isSulfuras(item)) {
                                decreaseQuality(item);
                            }
                        }
                    } else {
                        zeroQuality(item);
                    }
                } else {
                    if (isQualityLessThan50(item)) {
                        increaseQuality(item);
                    }
                }
            }
        }
    }
}
