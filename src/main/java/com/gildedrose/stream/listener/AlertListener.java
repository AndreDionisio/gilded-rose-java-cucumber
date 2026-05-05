package com.gildedrose.listener;

import com.gildedrose.events.ItemUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AlertListener {
    private static final Logger log = LoggerFactory.getLogger(InventoryLogListener.class);

    @EventListener
    public void onLowQuality(ItemUpdatedEvent event) {
        if (event.getBetterItem().quality().value() < 5) {
            log.debug("[ALERTA] Qualidade crítica para: " + event.getBetterItem().name());
        }
    }
}
