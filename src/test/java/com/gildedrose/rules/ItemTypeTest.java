package com.gildedrose.rules;

import com.gildedrose.updater.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ItemTypeTest {

    @ParameterizedTest(name = "Item \"{0}\" should return updater {1}")
    @MethodSource("provideItemsAndUpdaters")
    @DisplayName("Should return correct updater for known item names")
    void shouldReturnCorrectUpdater(String itemName, Class<? extends Update> expectedClass) {
        Update updater = ItemType.getUpdaterFor(itemName);
        assertInstanceOf(expectedClass, updater);
    }

    private static Stream<Arguments> provideItemsAndUpdaters() {
        return Stream.of(
                Arguments.of("Aged Brie", AgedBrie.class),
                Arguments.of("Backstage passes to a TAFKAL80ETC concert", Backstage.class),
                Arguments.of("Sulfuras, Hand of Ragnaros", Legendary.class),
                Arguments.of("Conjured Mana Cake", Conjured.class),
                Arguments.of("Conjured Shield", Conjured.class),
                Arguments.of("Normal Item", Default.class),
                Arguments.of("Unknown Mystery Item", Default.class)
        );
    }

    @Test
    @DisplayName("Should return Default updater for empty string or null-like matches")
    void fallbackToDefault() {
        Update updater = ItemType.getUpdaterFor("");
        assertInstanceOf(Default.class, updater);
    }
}
