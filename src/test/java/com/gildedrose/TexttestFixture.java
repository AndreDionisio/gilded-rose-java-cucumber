package com.gildedrose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.stream.IntStream;
import static com.gildedrose.constants.DomainConstants.*;
import static com.gildedrose.constants.DomainConstants.AGED_BRIE;
import static com.gildedrose.constants.DomainConstants.BACKSTAGE;
import static com.gildedrose.constants.DomainConstants.SULFURAS;

public class TexttestFixture {
    private static final Logger log = LoggerFactory.getLogger(TexttestFixture.class);
    @Autowired
    private GildedRose gildedRose;
    public static void main(String[] args) {
        log.info(OMGHAI);

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
        SpringApplication app = new SpringApplication(GildedRoseApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = app.run(args);
        GildedRose gildedRose = context.getBean(GildedRose.class);
        gildedRose.setItems(items);
        int days = (args.length > INITIAL_VALUE) ? Integer.parseInt(args[INITIAL_VALUE]) + STEP : STEP;

        IntStream.range(INITIAL_VALUE, days).forEach(day -> runDailyUpdate(day, items,gildedRose));
    }
    private static void runDailyUpdate(int day, List<Item> items, GildedRose gildedRose) {
        log.debug("-------- day {} --------", day);
        log.debug("name, sellIn, quality");

        items.forEach(item -> log.debug("{}", item));
        gildedRose.updateQuality();
    }
}
