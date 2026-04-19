package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;

public sealed interface Update
        permits Default, AgedBrie, Backstage, Conjured{
    BetterItem update(BetterItem item);
}
