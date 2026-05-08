package com.gildedrose.events;

import com.gildedrose.stream.events.ItemUpdatedEvent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemUpdatedEventTest {
    @Test
    void shouldHoldEventData() {
        ItemUpdatedEvent event = new ItemUpdatedEvent("Aged Brie", 10, 20);

        assertEquals("Aged Brie", event.name());
        assertEquals(10, event.sellIn());
        assertEquals(20, event.quality());
    }
}