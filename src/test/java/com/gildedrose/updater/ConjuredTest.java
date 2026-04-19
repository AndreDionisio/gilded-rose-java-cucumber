package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;
import com.gildedrose.rules.Expiration;
import com.gildedrose.rules.Quality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConjuredTest {

    private final Conjured updater = new Conjured();

    @Test
    @DisplayName("Should decrease quality by 2 before expiration")
    void shouldDecreaseDoubleBeforeExpiration() {
        BetterItem item = new BetterItem("Conjured Mana Cake", new Expiration(5), new Quality(10));

        BetterItem result = updater.update(item);

        assertEquals(8, result.quality().value());
        assertEquals(4, result.expiration().days());
    }

    @Test
    @DisplayName("Should decrease quality by 4 after expiration (expired post-tick)")
    void shouldDecreaseQuadrupleAfterExpiration() {
        BetterItem item = new BetterItem("Conjured Mana Cake", new Expiration(0), new Quality(10));

        BetterItem result = updater.update(item);

        assertEquals(6, result.quality().value());
        assertEquals(-1, result.expiration().days());
    }

    @ParameterizedTest(name = "Initial quality {0} should not drop below 0")
    @CsvSource({
            "1, 0",
            "3, 0"
    })
    @DisplayName("Should respect minimum quality bound (0)")
    void shouldNotDropBelowZero(int initial, int expected) {
        BetterItem item = new BetterItem("Conjured Mana Cake", new Expiration(0), new Quality(initial));

        BetterItem result = updater.update(item);

        assertEquals(expected, result.quality().value());
    }
}