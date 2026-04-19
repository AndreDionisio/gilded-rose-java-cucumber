package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;
import com.gildedrose.rules.Expiration;
import com.gildedrose.rules.Quality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgedBrieTest {

    private final AgedBrie updater = new AgedBrie();

    @Test
    @DisplayName("Should increase quality by 1 before expiration")
    void shouldIncreaseQualityBeforeExpiration() {
        BetterItem item = new BetterItem("Aged Brie", new Expiration(5), new Quality(10));

        BetterItem result = updater.update(item);

        assertEquals(11, result.quality().value());
        assertEquals(4, result.expiration().days());
    }

    @Test
    @DisplayName("Should increase quality by 2 after expiration")
    void shouldIncreaseDoubleAfterExpiration() {
        BetterItem item = new BetterItem("Aged Brie", new Expiration(0), new Quality(10));

        BetterItem result = updater.update(item);

        assertEquals(12, result.quality().value());
        assertEquals(-1, result.expiration().days());
    }

    @Test
    @DisplayName("Should not increase quality beyond maximum (50)")
    void shouldNotExceedMaxQuality() {
        BetterItem item = new BetterItem("Aged Brie", new Expiration(5), new Quality(50));

        BetterItem result = updater.update(item);

        assertEquals(50, result.quality().value());
    }

    @Test
    @DisplayName("Should not exceed max quality even when double increasing after expiration")
    void shouldNotExceedMaxQualityEvenWhenExpired() {
        BetterItem item = new BetterItem("Aged Brie", new Expiration(0), new Quality(49));

        BetterItem result = updater.update(item);

        assertEquals(50, result.quality().value());
    }
}