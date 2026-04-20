package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TexttestFixtureTest {
    @Test
    @DisplayName("Should execute main fixture without exceptions for various arguments")
    void testTexttestFixtureMain() {
        assertDoesNotThrow(() -> TexttestFixture.main(new String[]{}));

        assertDoesNotThrow(() -> TexttestFixture.main(new String[]{"1"}));
    }
}
