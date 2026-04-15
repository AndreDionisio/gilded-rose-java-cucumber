Feature: Daily quality update simulation
  The system should update item quality correctly over multiple days

  Scenario Outline: Simulate item "<name>" with sellIn <sellIn> and quality <quality> evolution over <days> day
    Given an item "<name>" with sellIn <sellIn> and quality <quality>
    When I update the quality for <days> days
    Then The final sellIn should be <finalSellIn>
    And The final quality should be <finalQuality>

    Examples:
      | name                                      | sellIn | quality | days | finalSellIn | finalQuality |
      | +5 Dexterity Vest                         | 10     | 20      | 1    | 9           | 19           |
      | Aged Brie                                 | 2      | 0       | 1    | 1           | 1            |
      | Elixir of the Mongoose                    | 5      | 7       | 1    | 4           | 6            |
      | Sulfuras, Hand of Ragnaros                | 0      | 80      | 1    | 0           | 80           |
      | Backstage passes to a TAFKAL80ETC concert | 15     | 20      | 1    | 14          | 21           |
      | Conjured Mana Cake                        | 3      | 6       | 1    | 2           | 4            |
