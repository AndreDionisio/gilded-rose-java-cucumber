package com.gildedrose.stream.events;

import com.gildedrose.rules.BetterItem;
import org.springframework.context.ApplicationEvent;

public record ItemUpdatedEvent(
        String name,
        int sellIn,
        int quality
) { }
