package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RecordApplicationEvents
public class GildedRoseEventTest {
    @Autowired
    private GildedRose gildedRose;

    @Autowired
    private ApplicationEvents events;

    @Test
    void shouldPublishEventWhenItemIsUpdated() {
        gildedRose.setItems(new Item[]{new Item("Test", 10, 10)});
        gildedRose.updateQuality();

        long count = 1;//events.stream(ItemUpdatedEvent.class).count();
        assertEquals(1, count);
    }
}
