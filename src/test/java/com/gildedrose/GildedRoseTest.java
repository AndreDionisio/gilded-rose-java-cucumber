package com.gildedrose;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GildedRoseTest {
    private GildedRose gildedRose;
    private CommandGateway publisher;
    @BeforeEach
    void setUp() {
        publisher = mock(CommandGateway.class);
        gildedRose = new GildedRose(publisher);
    }
    @Test
    @DisplayName("Should update a list of items correctly using the orchestrator")
    void testUpdateQuality() {
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80)
        };

        this.gildedRose.setItems(items);
        this.gildedRose.updateQuality();

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
        this.gildedRose.setItems(items);
        assertEquals(items, this.gildedRose.getItems());
    }

    @Test
    @DisplayName("Covering initialization")
    void testGildedRoseConstructor() throws Exception {
        Constructor<GildedRose> constructor = GildedRose.class.getDeclaredConstructor(CommandGateway.class);

        assertTrue(Modifier.isPublic(constructor.getModifiers()));

        GildedRose instance = constructor.newInstance((CommandGateway) null);
        assertNotNull(instance);
    }
}