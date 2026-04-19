package com.gildedrose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.IntStream;
import static com.gildedrose.rules.Expiration.*;
import static com.gildedrose.rules.Expiration.MINIMAL;
import static com.gildedrose.rules.ItemType.*;
import static com.gildedrose.rules.Quality.*;
import static com.gildedrose.rules.Quality.STEP;

public class TexttestFixture {
    private static final Logger log = LoggerFactory.getLogger(TexttestFixture.class);
    static final String CONJURED_CAKE = "Conjured Mana Cake";
    static final String OMGHAI = "OMGHAI!";
    static final String VEST = "+5 Dexterity Vest";
    static final String ELIXIR = "Elixir of the Mongoose";
    public static void main(String[] args) {
        log.info(OMGHAI);

        final List<Item> items = List.of(
                new Item(VEST, SPRINT, NORMAL),
                new Item(AGED_BRIE.name(), WEEKEND, MINIMAL),
                new Item(ELIXIR, WORKDAYS, LOW),
                new Item(SULFURAS.name(), MINIMAL, LEGENDARY_QUALITY),
                new Item(SULFURAS.name(), YESTERDAY, LEGENDARY_QUALITY),
                new Item(BACKSTAGE.name(), VACATION, NORMAL),
                new Item(BACKSTAGE.name(), SPRINT, NEW),
                new Item(BACKSTAGE.name(), WORKDAYS, NEW),
                new Item(CONJURED_CAKE, BIG_WEEKEND, TRIPLE_QUALITY_THRESHOLD)
        );

        GildedRose.setItems(items);
        int days = (args.length > INITIAL_VALUE) ? Integer.parseInt(args[INITIAL_VALUE]) + STEP : STEP;

        IntStream.range(INITIAL_VALUE, days).forEach(day -> runDailyUpdate(day, items));
    }
    private static void runDailyUpdate(int day, List<Item> items) {
        log.debug("-------- day {} --------", day);
        log.debug("name, sellIn, quality");

        items.forEach(item -> log.debug("{}", item));
        GildedRose.updateQuality();
    }
}
