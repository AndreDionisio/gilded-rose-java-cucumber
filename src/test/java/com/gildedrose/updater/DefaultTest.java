package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;
import com.gildedrose.rules.Expiration;
import com.gildedrose.rules.Quality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultTest {

    private final Default updater = new Default();

    @Test
    @DisplayName("Should decrease quality by 1 before expiration")
    void shouldDecreaseNormally() {
        BetterItem item = new BetterItem("+5 Dexterity Vest", new Expiration(10), new Quality(20));

        BetterItem result = updater.update(item);

        assertEquals(19, result.quality().value());
        assertEquals(9, result.expiration().days());
    }

    @Test
    @DisplayName("Should decrease quality by 2 after expiration")
    void shouldDecreaseDoubleAfterExpiration() {
        BetterItem item = new BetterItem("Elixir of the Mongoose", new Expiration(0), new Quality(10));

        BetterItem result = updater.update(item);

        assertEquals(8, result.quality().value());
        assertEquals(-1, result.expiration().days());
    }

    @ParameterizedTest(name = "Initial quality {0} should not drop below 0")
    @CsvSource({
            "0, 0",
            "1, 0"
    })
    @DisplayName("Should never decrease quality below the minimum (0)")
    void shouldRespectMinQuality(int initial, int expected) {
        BetterItem item = new BetterItem("Normal Item", new Expiration(0), new Quality(initial));

        BetterItem result = updater.update(item);

        assertEquals(expected, result.quality().value());
    }
}