package com.gildedrose;

import com.gildedrose.command.UpdateItemQualityCommand;
import com.gildedrose.stream.events.ItemUpdatedEvent;
import com.gildedrose.rules.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
class GildedRose {
    private Item[] items;
    private final CommandGateway commandGateway;
    @Autowired
    public GildedRose(CommandGateway commandGateway) {

        this.commandGateway = commandGateway;
    }

    public void updateQuality() {
        if (items == null) return;
        Arrays.stream(items).forEach(this::processItem);
    }

    private void processItem(Item item) {
        BetterItem better = toBetterItem(item);

        better = ItemType.getUpdaterFor(better.name()).update(better);

        item.quality = better.quality().value();
        item.sellIn = better.expiration().days();
        commandGateway.send(new UpdateItemQualityCommand(item.name));

    }

    private static BetterItem toBetterItem(Item item) {
        return new BetterItem(item.name, new Expiration(item.sellIn), new Quality(item.quality));
    }

    public  void setItems(Item[] items) { this.items = items; }
    public  void setItems(List<Item> items) { this.items = items.toArray(Item[]::new); }
    public Item[] getItems() { return items; }
}