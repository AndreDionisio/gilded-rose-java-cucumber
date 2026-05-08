package com.gildedrose.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateQualityCommand {
    @TargetAggregateIdentifier
    private final String itemId;

    public UpdateQualityCommand(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }
}
