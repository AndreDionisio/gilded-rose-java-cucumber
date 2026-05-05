package com.gildedrose.events;

import com.gildedrose.rules.BetterItem;
import org.springframework.context.ApplicationEvent;

public class ItemUpdatedEvent extends ApplicationEvent {
    private final BetterItem betterItem;

    public ItemUpdatedEvent(Object source, BetterItem betterItem) {
        super(source);
        this.betterItem = betterItem;
    }

    public BetterItem getBetterItem() {
        return betterItem;
    }
}
