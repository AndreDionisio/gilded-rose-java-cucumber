package com.gildedrose.rules;

import com.gildedrose.constants.TestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.gildedrose.constants.TestConstants.*;
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
    @MethodSource("provideValues")
    @DisplayName("Should correctly identify if an item is expired")
    void testIsExpired(int days, boolean expected) {
        assertEquals(expected, new Expiration(days).isExpired());
    }
    private static Stream<Arguments> provideValues() {
        return Stream.of(
                Arguments.of(TestConstants.TOMORROW, TestConstants.VALID),
                Arguments.of(TestConstants.TODAY, TestConstants.VALID),
                Arguments.of(TestConstants.YESTERDAY, TestConstants.EXPIRED),
                Arguments.of(TestConstants.EXPIRED_TEN_DAYS, TestConstants.EXPIRED)
        );
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
