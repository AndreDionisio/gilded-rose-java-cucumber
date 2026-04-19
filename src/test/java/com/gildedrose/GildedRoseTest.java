package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GildedRoseTest {

    @Test
    @DisplayName("Should update a list of items correctly using the orchestrator")
    void testUpdateQuality() {
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80)
        };

        GildedRose.setItems(items);
        GildedRose.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);

        assertEquals(1, items[1].sellIn);
        assertEquals(1, items[1].quality);

        assertEquals(0, items[2].sellIn);
        assertEquals(80, items[2].quality);
    }

    @Test
    @DisplayName("Should allow getting the items array")
    void testGetItems() {
        Item[] items = new Item[] { new Item("Foo", 0, 0) };
        GildedRose.setItems(items);
        assertEquals(items, GildedRose.getItems());
    }

    @Test
    @DisplayName("Covering initialization")
    void testGildedRoseConstructorIsPrivate() throws Exception {
        Constructor<GildedRose> constructor = GildedRose.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}