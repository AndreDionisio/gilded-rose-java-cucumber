package com.gildedrose.rules;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.gildedrose.constants.TestConstants.*;
import static com.gildedrose.rules.DomainConstants.SPRINT;
import static org.junit.jupiter.api.Assertions.*;

class BetterItemTest {

    @Test
    @DisplayName("Should create a new instance with decremented expiration when tick is called")
    void testTick() {
        Expiration initialExpiration = new Expiration(TEN_DAYS);
        Quality initialQuality = new Quality(QUALITY_OF_20);
        BetterItem item = new BetterItem("Standard Item", initialExpiration, initialQuality);

        BetterItem tickedItem = item.tick();

        assertEquals(NINE_DAYS, tickedItem.expiration().days());
        assertEquals(QUALITY_OF_20, tickedItem.quality().value());

        assertNotSame(item, tickedItem);
        assertEquals(TEN_DAYS, item.expiration().days());
        assertEquals(QUALITY_OF_20, item.quality().value());
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