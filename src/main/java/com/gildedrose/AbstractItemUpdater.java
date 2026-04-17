package com.gildedrose;

import static com.gildedrose.ItemRules.decreaseSellIn;

public abstract class AbstractItemUpdater implements ItemUpdater {

    @Override
    public final void update(Item item) {
        applyPreUpdateLogic(item);

        decreaseSellIn(item);

        applyPostUpdateLogic(item);
    }

    protected abstract void applyPreUpdateLogic(Item item);

    protected abstract void applyPostUpdateLogic(Item item);

}
