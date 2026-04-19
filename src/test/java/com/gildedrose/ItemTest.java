package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @Test
    @DisplayName("Should correctly initialize item fields via constructor")
    void testItemConstructor() {
        Item item = new Item("Test Item", 10, 20);

        assertEquals("Test Item", item.name);
        assertEquals(10, item.sellIn);
        assertEquals(20, item.quality);
    }

    @Test
    @DisplayName("Should return correct string representation")
    void testItemToString() {
        Item item = new Item("Aged Brie", 5, 10);
        String expected = "Aged Brie, 5, 10";

        assertEquals(expected, item.toString());
    }
}