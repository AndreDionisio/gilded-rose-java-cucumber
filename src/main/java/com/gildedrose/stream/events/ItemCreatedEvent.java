package com.gildedrose.stream.events;

public record ItemCreatedEvent( String name,
                                int sellIn,
                                int quality) {
}
