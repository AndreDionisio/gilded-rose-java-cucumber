package com.gildedrose.rules;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BetterItemTest {

    @Test
    @DisplayName("Should create a new instance with decremented expiration when tick is called")
    void testTick() {
        Expiration initialExpiration = new Expiration(10);
        Quality initialQuality = new Quality(20);
        BetterItem item = new BetterItem("Standard Item", initialExpiration, initialQuality);

        BetterItem tickedItem = item.tick();

        assertEquals(9, tickedItem.expiration().days());
        assertEquals(20, tickedItem.quality().value());

        assertNotSame(item, tickedItem);
        assertEquals(10, item.expiration().days());
    }

    @Test
    @DisplayName("Should create a new instance with updated quality")
    void testWithQuality() {
        BetterItem item = new BetterItem("Standard Item", new Expiration(10), new Quality(20));
        Quality newQuality = new Quality(30);

        BetterItem updatedItem = item.withQuality(newQuality);

        assertEquals(30, updatedItem.quality().value());
        assertEquals(10, updatedItem.expiration().days());

        assertNotSame(item, updatedItem);
        assertEquals(20, item.quality().value());
    }

    @Test
    @DisplayName("Should maintain identity of the name")
    void testNamePersistence() {
        String name = "Test Item";
        BetterItem item = new BetterItem(name, new Expiration(5), new Quality(10));

        assertEquals(name, item.name());
        assertEquals(name, item.tick().name());
        assertEquals(name, item.withQuality(new Quality(0)).name());
    }
}