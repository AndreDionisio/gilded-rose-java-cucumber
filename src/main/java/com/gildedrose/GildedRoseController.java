package com.gildedrose;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.gildedrose.constants.DomainConstants.*;
import static com.gildedrose.constants.DomainConstants.AGED_BRIE;
import static com.gildedrose.constants.DomainConstants.BACKSTAGE;
import static com.gildedrose.constants.DomainConstants.BIG_WEEKEND;
import static com.gildedrose.constants.DomainConstants.CONJURED_CAKE;
import static com.gildedrose.constants.DomainConstants.ELIXIR;
import static com.gildedrose.constants.DomainConstants.LEGENDARY_QUALITY;
import static com.gildedrose.constants.DomainConstants.LOW;
import static com.gildedrose.constants.DomainConstants.MINIMAL;
import static com.gildedrose.constants.DomainConstants.NEW;
import static com.gildedrose.constants.DomainConstants.NORMAL;
import static com.gildedrose.constants.DomainConstants.SPRINT;
import static com.gildedrose.constants.DomainConstants.SULFURAS;
import static com.gildedrose.constants.DomainConstants.TRIPLE_QUALITY_THRESHOLD;
import static com.gildedrose.constants.DomainConstants.VACATION;
import static com.gildedrose.constants.DomainConstants.WEEKEND;
import static com.gildedrose.constants.DomainConstants.WORKDAYS;
import static com.gildedrose.constants.DomainConstants.YESTERDAY;

@RestController
public class GildedRoseController {
    private final GildedRose gildedRose;
    public GildedRoseController(GildedRose gildedRose) {
        this.gildedRose = gildedRose;
        final List<Item> items = List.of(
                new Item(VEST, SPRINT, NORMAL),
                new Item(AGED_BRIE, WEEKEND, MINIMAL),
                new Item(ELIXIR, WORKDAYS, LOW),
                new Item(SULFURAS, MINIMAL, LEGENDARY_QUALITY),
                new Item(SULFURAS, YESTERDAY, LEGENDARY_QUALITY),
                new Item(BACKSTAGE, VACATION, NORMAL),
                new Item(BACKSTAGE, SPRINT, NEW),
                new Item(BACKSTAGE, WORKDAYS, NEW),
                new Item(CONJURED_CAKE, BIG_WEEKEND, TRIPLE_QUALITY_THRESHOLD)
        );
        this.gildedRose.setItems(items.toArray(Item[]::new));
    }
    @GetMapping("/items")
    public ResponseEntity<Item[]> getItems() {
        return ResponseEntity.ok(gildedRose.getItems());
    }
    @PostMapping("/item")
    public ResponseEntity<String> addItem(@RequestBody Item item) {
        Item[] currentItems = this.gildedRose.getItems();
        Item[] newItems = Arrays.copyOf(currentItems, currentItems.length + 1);
        newItems[newItems.length - 1] = item;
        this.gildedRose.setItems(newItems);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
    }
    @PutMapping("/items")
    public ResponseEntity<String> setItems(@RequestBody Item[] items) {
        this.gildedRose.setItems(items);
        return ResponseEntity.ok("Ok");
    }
    @PostMapping("/items/update")
    public ResponseEntity<Item[]> runUpdate() {
        this.gildedRose.updateQuality();
        return ResponseEntity.ok(this.gildedRose.getItems());
    }

}
