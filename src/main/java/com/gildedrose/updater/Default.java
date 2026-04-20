package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;

public final class Default  implements Update{
    public BetterItem update(BetterItem item){
        BetterItem updated = item.withQuality(item.quality().decrease());
        updated = updated.tick();
        if (updated.expiration().isExpired()){
            updated = updated.withQuality(updated.quality().decrease());
        }
        return updated;
    }
}
