package com.gildedrose.aggregate;

import com.gildedrose.command.CreateItemCommand;
import com.gildedrose.command.UpdateItemQualityCommand;
import com.gildedrose.rules.BetterItem;
import com.gildedrose.rules.Expiration;
import com.gildedrose.rules.ItemType;
import com.gildedrose.rules.Quality;
import com.gildedrose.stream.events.ItemCreatedEvent;
import com.gildedrose.stream.events.ItemUpdatedEvent;
import com.gildedrose.updater.Update;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ItemAggregate {

    @AggregateIdentifier
    private String name;
    private BetterItem betterItem;

    protected ItemAggregate() { }

    @CommandHandler
    public ItemAggregate(CreateItemCommand cmd) {
        apply(new ItemCreatedEvent(cmd.name(), cmd.sellIn(), cmd.quality()));
    }

    @CommandHandler
    public void handle(UpdateItemQualityCommand cmd) {
        Update updater = ItemType.getUpdaterFor(this.betterItem.name());
        BetterItem nextState = updater.update(this.betterItem);

        apply(new ItemUpdatedEvent(
                nextState.name(),
                nextState.expiration().days(),
                nextState.quality().value()
        ));
    }
    @EventSourcingHandler
    public void on(ItemCreatedEvent event) {
        this.name = event.name();
        this.betterItem = new BetterItem(
                event.name(),
                new Expiration(event.sellIn()),
                new Quality(event.quality())
        );
    }
    @EventSourcingHandler
    public void on(ItemUpdatedEvent event) {
        this.name = event.name();
        this.betterItem = new BetterItem(
                event.name(),
                new Expiration(event.sellIn()),
                new Quality(event.quality())
        );
    }
}