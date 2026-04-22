package com.gildedrose.rules;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.gildedrose.constants.TestConstants.NINE_DAYS;
import static com.gildedrose.constants.TestConstants.TEN_DAYS;
import static org.junit.jupiter.api.Assertions.*;

class ExpirationTest {

    @Test
    @DisplayName("Should decrease days when nextDay is called")
    void shouldDecreaseDays() {
        Expiration expiration = new Expiration(TEN_DAYS);
        Expiration next = expiration.nextDay();

        assertEquals(NINE_DAYS, next.days());
        assertEquals(TEN_DAYS, expiration.days(), "Original record should remain immutable");
    }

    @ParameterizedTest(name = "Days {0} should result in isExpired = {1}")
    @CsvSource({
            "1, false",
            "0, false",
            "-1, true",
            "-10, true"
    })
    @DisplayName("Should correctly identify if an item is expired")
    void testIsExpired(int days, boolean expected) {
        assertEquals(expected, new Expiration(days).isExpired());
    }

    @ParameterizedTest(name = "Days {0} has fewer than {1} should be {2}")
    @CsvSource({
            "10, 11, true",
            "11, 11, false",
            "12, 11, false",
            "5, 6, true",
            "6, 6, false"
    })
    @DisplayName("Should correctly identify days relative to a threshold")
    void testHasFewerDaysThan(int days, int threshold, boolean expected) {
        assertEquals(expected, new Expiration(days).hasFewerDaysThan(threshold));
    }
}
