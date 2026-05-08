package com.gildedrose.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateItemCommand(
        @TargetAggregateIdentifier String name,
        int sellIn,
        int quality
) {
}