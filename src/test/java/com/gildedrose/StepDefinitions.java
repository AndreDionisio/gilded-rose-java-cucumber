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
    public void printScenarioStatus(){
        System.out.printf("Then the final SellIn is %d and the final Quality is %d",items[0].sellIn,items[0].quality);
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
}

