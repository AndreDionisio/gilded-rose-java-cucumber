package com.gildedrose.rules;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.gildedrose.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class BetterItemTest {

    @Test
    @DisplayName("Should create a new instance with decremented expiration when tick is called")
    void testTick() {
        Expiration initialExpiration = new Expiration(TEN_DAYS);
        Quality initialQuality = new Quality(QUALITY_OF_TWENTY);
        BetterItem item = new BetterItem("Standard Item", initialExpiration, initialQuality);

        BetterItem tickedItem = item.tick();

        assertEquals(NINE_DAYS, tickedItem.expiration().days());
        assertEquals(QUALITY_OF_TWENTY, tickedItem.quality().value());

        assertNotSame(item, tickedItem);
        assertEquals(TEN_DAYS, item.expiration().days());
        assertEquals(QUALITY_OF_TWENTY, item.quality().value());
    }

    @Test
    @DisplayName("Should create a new instance with updated quality")
    void testWithQuality() {
        BetterItem item = new BetterItem(STANDARD_ITEM, new Expiration(TEN_DAYS), new Quality(QUALITY_OF_TWENTY));
        Quality newQuality = new Quality(QUALITY_OF_THIRTY);

        BetterItem updatedItem = item.withQuality(newQuality);

        assertEquals(QUALITY_OF_THIRTY, updatedItem.quality().value());
        assertEquals(TEN_DAYS, updatedItem.expiration().days());

        assertNotSame(item, updatedItem);
        assertEquals(QUALITY_OF_TWENTY, item.quality().value());
        assertEquals(TEN_DAYS, item.expiration().days());
    }

    @Test
    @DisplayName("Should maintain identity of the name")
    void testNamePersistence() {

        BetterItem item = new BetterItem(NAME, new Expiration(FIVE_DAYS), new Quality(QUALITY_OF_TEN));

        assertEquals(NAME, item.name());
        assertEquals(NAME, item.tick().name());
        assertEquals(NAME, item.withQuality(new Quality(QUALITY_OF_ZERO)).name());
    }
}