@tag
Feature: My wish list
  I want to add product into my wish list

  @tag1
  Scenario: Add 4 Products to wishlist
    Given I open test script demo site
    And I add 4 different products to my wish list
    When I view my wishlist table
    Then I found total 4 selected items in my wish list
    When I search for lowest price product
    And I am able to add the lowest price item to my cart
    Then I am able to verify the item in my cart

