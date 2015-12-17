Feature: Add to cart
  As a customer
  I want to view the contents of my bag prior to checkout

  Scenario: Add item to bag and view cart
    Given I have added an item to my cart
    When I view the contents of my cart
    Then I should see the contents of the cart include the item
