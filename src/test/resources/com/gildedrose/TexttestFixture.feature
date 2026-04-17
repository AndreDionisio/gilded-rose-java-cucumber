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
      | +5 Dexterity Vest                         | -1     | 50      | 1    | -2          | 48           |
      | Aged Brie                                 | 2      | 0       | 1    | 1           | 1            |
      | Aged Brie                                 | 2      | 50      | 1    | 1           | 50           |
      | Aged Brie                                 | -1     | 48      | 1    | -2          | 50           |
      | Aged Brie                                 | -1     | 10      | 1    | -2          | 12           |
      | Aged Brie                                 | -1     | 50      | 1    | -2          | 50           |
      | Aged Brie                                 | 2      | 10      | 1    | 1           | 11           |
      | Elixir of the Mongoose                    | 5      | 7       | 1    | 4           | 6            |
      | Sulfuras, Hand of Ragnaros                | 0      | 80      | 1    | 0           | 80           |
      | Backstage passes to a TAFKAL80ETC concert | 15     | 20      | 1    | 14          | 21           |
      | Backstage passes to a TAFKAL80ETC concert | 15     | 50      | 1    | 14          | 50           |
      | Backstage passes to a TAFKAL80ETC concert | 12     | 10      | 1    | 11          | 11           |
      | Backstage passes to a TAFKAL80ETC concert | 10     | 10      | 1    | 9           | 12           |
      | Backstage passes to a TAFKAL80ETC concert | 5      | 10      | 1    | 4           | 13           |
      | Backstage passes to a TAFKAL80ETC concert | 5      | 49      | 1    | 4           | 50           |
      | Backstage passes to a TAFKAL80ETC concert | 0      | 10      | 1    | -1          | 0            |
      | Conjured Mana Cake                        | 3      | 6       | 1    | 2           | 4            |
      | Conjured Mana Cake	                      |-1	   | 6	     | 1    | -2          | 2            |
      | Conjured Mana Cake	                      |-1	   | -6      | 1    | -2          | 0            |
      | Conjured Mana Cake	                      |-1	   | 50      | 1    | -2          | 46           |
      | Conjured Mana Cake                        | 50     | 50      | 1    | 49          | 48           |

