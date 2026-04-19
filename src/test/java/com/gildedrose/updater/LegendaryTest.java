package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;
import com.gildedrose.rules.Expiration;
import com.gildedrose.rules.Quality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class LegendaryTest {

    private final Legendary updater = new Legendary();

    @Test
    @DisplayName("Should return exactly the same item without any changes")
    void shouldMaintainIdentity() {
        BetterItem item = new BetterItem(
                "Sulfuras, Hand of Ragnaros",
                new Expiration(0),
                new Quality(80)
        );

        BetterItem result = updater.update(item);

        assertSame(item, result, "Legendary items should return the exact same instance");

        assertEquals(80, result.quality().value());
        assertEquals(0, result.expiration().days());
    }
}