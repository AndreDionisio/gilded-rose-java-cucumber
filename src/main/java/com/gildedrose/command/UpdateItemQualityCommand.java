package com.gildedrose.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record UpdateItemQualityCommand(@TargetAggregateIdentifier String id) {
}
