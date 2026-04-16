package com.gildedrose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TexttestFixture {
    private static final Logger log = LoggerFactory.getLogger(TexttestFixture.class);
    static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static void main(String[] args) {
        log.info("OMGHAI!");

        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item(BACKSTAGE, 15, 20),
                new Item(BACKSTAGE, 10, 49),
                new Item(BACKSTAGE, 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6) };

        GildedRose.setItems(items);
        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            log.debug("-------- day {} --------", i);
            log.debug("name, sellIn, quality");
            for (Item item : items) {
                log.debug("{}", item);
            }
            GildedRose.updateQuality();
        }
    }

}
