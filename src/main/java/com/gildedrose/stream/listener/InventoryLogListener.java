package com.gildedrose.listener;

import com.gildedrose.GildedRoseApplication;
import com.gildedrose.Item;
import com.gildedrose.events.ItemUpdatedEvent;
import com.gildedrose.rules.BetterItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryLogListener {
    private static final Logger log = LoggerFactory.getLogger(InventoryLogListener.class);

    @EventListener
    public void handleItemUpdate(ItemUpdatedEvent event) {
        BetterItem item = event.getBetterItem();
        if (log.isDebugEnabled()) {
            log.debug("[LOG EVENTO] Item processado: " + item.name() + " | Nova Qualidade: " + item.quality());
        }
    }
}
