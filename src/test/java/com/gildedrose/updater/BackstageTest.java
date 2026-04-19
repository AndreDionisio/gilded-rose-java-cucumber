package com.gildedrose.updater;

import com.gildedrose.rules.BetterItem;
import com.gildedrose.rules.Expiration;
import com.gildedrose.rules.Quality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackstageTest {

    private final Backstage updater = new Backstage();

    @ParameterizedTest(name = "With {0} days, quality {1} should become {2}")
    @CsvSource({
            "15, 20, 21",
            "11, 20, 21",
            "10, 20, 22",
            "6, 20, 22",
            "5, 20, 23",
            "1, 20, 23",
            "0, 20, 0"
    })
    @DisplayName("Should follow tiered quality increase rules based on expiration")
    void tieredIncreases(int sellIn, int initialQuality, int expectedQuality) {
        BetterItem item = new BetterItem("Backstage passes", new Expiration(sellIn), new Quality(initialQuality));
        BetterItem result = updater.update(item);

        assertEquals(expectedQuality, result.quality().value());
    }

    @Test
    @DisplayName("Should not exceed maximum quality (50) during tiered increases")
    void respectMaxQuality() {
        BetterItem item = new BetterItem("Backstage passes", new Expiration(5), new Quality(49));
        BetterItem result = updater.update(item);

        assertEquals(50, result.quality().value());
    }
}