package com.gildedrose;

import static org.junit.jupiter.api.Assertions.*;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;
public class StepDefinitions {
    private final Item[] items = new Item[1];

    @Before
    public void printScenarioName(Scenario scenario) {
        System.out.println(scenario.getName());
    }

    @After
    public void printScenarioStatus() {
        Item[] currentItems = GildedRose.getItems();
        if (currentItems == null || currentItems.length == 0) return;

        if (currentItems.length == 1) {
            System.out.printf("Then the final SellIn is %d and the final Quality is %d%n",
                    currentItems[0].sellIn, currentItems[0].quality);
        } else {
            System.out.println("Final state of all items:");
            for (Item item : currentItems) {
                System.out.println(item.toString());
            }
        }
    }

    @Given("The item as {string}")
    public void initial_sellin_is_and_quality_is(String name) {
        items[0] = new Item(name, 0, 0);
        GildedRose.setItems(items);
    }

    @When("I update the quality")
    public void i_update_the_quality() {
        GildedRose.updateQuality();
    }

    @Then("I should get item as {string}")
    public void i_should_get_sellin_as_and_quality_as(String expected) {
        assertEquals(expected, GildedRose.getItems()[0].name);
    }

    @Given("an item {string} with sellIn {int} and quality {int}")
    public void an_item_with_sellIn_and_quality(String name, int sellIn, int quality) {
        items[0] = new Item(name, sellIn, quality);
        GildedRose.setItems(items);
    }

    @When("I update the quality for {int} days")
    public void i_update_the_quality_for_days(int days) {
        for (int i = 0; i < days; i++) {
            GildedRose.updateQuality();
        }
    }

    @Then("The final sellIn should be {int}")
    public void the_final_sellIn_should_be(int expected) {
        assertEquals(expected, items[0].sellIn);
    }

    @Then("The final quality should be {int}")
    public void the_final_quality_should_be(int expected) {
        assertEquals(expected, items[0].quality);
    }

    @Then("the item description should be {string}")
    public void the_item_description_should_be(String expectedDescription) {
        assertEquals(expectedDescription, items[0].toString());
    }

    @Given("the standard set of inventory items")
    public void the_standard_set_of_inventory_items() {
        GildedRose.setItems(new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6)
        });
    }

    @Then("the item {string} should have sellIn {int} and quality {int}")
    public void verify_specific_item_state(String name, int sellIn, int quality) {
        Item match = java.util.Arrays.stream(GildedRose.getItems())
                .filter(i -> i.name.equals(name))
                .findFirst()
                .orElseThrow();

        assertEquals(sellIn, match.sellIn);
        assertEquals(quality, match.quality);
    }
}

