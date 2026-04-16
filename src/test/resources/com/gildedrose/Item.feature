Feature: Item metadata
  Verification of Item class basic functionality

  Scenario: Item string representation
    Given an item "+5 Dexterity Vest" with sellIn 10 and quality 20
    Then the item description should be "+5 Dexterity Vest, 10, 20"


