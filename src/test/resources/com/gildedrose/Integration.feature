Feature: Texttest Fixture Integration
  As a developer, I want to ensure the legacy fixture items
  evolve correctly over the first 2 days.

  Scenario: Standard fixture run for 2 days
    Given the standard set of inventory items
    When I update the quality for 2 days
    Then the item "+5 Dexterity Vest" should have sellIn 8 and quality 18
    And the item "Aged Brie" should have sellIn 0 and quality 2
    And the item "Conjured Mana Cake" should have sellIn 1 and quality 2