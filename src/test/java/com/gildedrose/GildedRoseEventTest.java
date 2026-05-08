package com.gildedrose;

import com.gildedrose.command.CreateItemCommand;
import com.gildedrose.command.UpdateItemQualityCommand;
import com.gildedrose.stream.events.ItemCreatedEvent;
import com.gildedrose.stream.events.ItemUpdatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static com.gildedrose.constants.DomainConstants.CONJURED_CAKE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GildedRoseEventTest {
    @Autowired
    private GildedRose gildedRose;

    @Autowired
    private EventStore eventStore;
    @Autowired private CommandGateway commandGateway;
    @Test
    void shouldPublishEventWhenItemIsCreatedAndUpdated() {
        String itemName = "Test Item";
        commandGateway.sendAndWait(new CreateItemCommand(itemName, 10, 10));
        gildedRose.setItems(new Item[]{new Item(itemName, 10, 10)});

        gildedRose.updateQuality();

        List<? extends Message<?>> publishedEvents = eventStore.readEvents(itemName)
                .asStream()
                .collect(Collectors.toList());

        long count = publishedEvents.stream()
                .filter(event -> event.getPayload() instanceof ItemUpdatedEvent)
                .count();

        assertEquals(1, count, "Should trigger exactly 1 ItemUpdatedEvent at EventStore");
    }
    @Test
    void shouldPublishEventWhenItemIsUpdated() {
        String itemName = CONJURED_CAKE;
        commandGateway.sendAndWait(new CreateItemCommand(itemName, 10, 50));

        long countBefore = eventStore.readEvents(itemName).asStream().count();

        gildedRose.setItems(new Item[]{new Item(itemName, 10, 10)});

        gildedRose.updateQuality();

        List<? extends Message<?>> publishedEvents = eventStore.readEvents(itemName)
                .asStream()
                .collect(Collectors.toList());

        long countAfter = publishedEvents.stream()
                .filter(event -> event.getPayload() instanceof ItemUpdatedEvent)
                .count();


        assertEquals(countBefore, countAfter, "The update quality should add a single ItemUpdatedEvent.");
    }
    @Test
    void shouldPublishItemCreatedEvent() {
        String itemName = "Aged Brie";

        commandGateway.sendAndWait(new CreateItemCommand(itemName, 10, 20));

        List<? extends Message<?>> events = eventStore.readEvents(itemName).asStream().toList();

        assertTrue(events.get(0).getPayload() instanceof ItemCreatedEvent);
        ItemCreatedEvent event = (ItemCreatedEvent) events.get(0).getPayload();

        assertEquals(itemName, event.name());
        assertEquals(20, event.quality());
    }
    @Test
    void shouldPublishItemUpdatedEventWithDecreasedValues() {
        String itemName = "Standard Item";
        commandGateway.sendAndWait(new CreateItemCommand(itemName, 10, 20));

        commandGateway.sendAndWait(new UpdateItemQualityCommand(itemName));

        List<? extends Message<?>> events = eventStore.readEvents(itemName).asStream().toList();
        ItemUpdatedEvent lastEvent = (ItemUpdatedEvent) events.get(events.size() - 1).getPayload();

        assertEquals(19, lastEvent.quality(), "Qualidade deveria cair para 19");
        assertEquals(9, lastEvent.sellIn(), "SellIn deveria cair para 9");
    }
}
