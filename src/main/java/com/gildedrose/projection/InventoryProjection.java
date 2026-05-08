package com.gildedrose.projection;
import com.gildedrose.Item;
import com.gildedrose.stream.events.ItemUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InventoryProjection {

    private final Map<String, Item> currentInventory = new ConcurrentHashMap<>();

    @EventHandler
    public void on(ItemUpdatedEvent event) {
        Item item = currentInventory.getOrDefault(
                event.name(),
                new Item(event.name(), 0, 0)
        );

        item.sellIn = event.sellIn();
        item.quality = event.quality();

        currentInventory.put(event.name(), item);
    }

    @QueryHandler
    public List<Item> handle(GetInventoryQuery query) {
        return new ArrayList<>(currentInventory.values());
    }
}