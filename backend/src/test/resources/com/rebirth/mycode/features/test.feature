
Feature: What happend if a sum two numbers
  Everybody wants to know what happend if I sum two numbers

  Scenario Outline: I sum two numbers
    Given I have number <x> and number <y>
    When I sum this two numbers
    Then I should get <z>

    Examples:
      | x | y | z  |
      | 1 | 2 | 3  |
      | 3 | 7 | 10 |
      | 4 | 8 | 12 |
      | 6 | 9 | 15 |
      | 2 | 2 | 4  |