package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;

public sealed interface Update
        permits Default, AgedBrie, Backstage, Conjured, Legendary{
    BetterItem update(BetterItem item);
}
