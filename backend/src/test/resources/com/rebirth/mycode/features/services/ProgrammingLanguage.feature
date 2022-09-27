Feature: The product specification requires this API to provide a list of programming languages to
  assign one to the programming code snippet added by a registered user.

  Rule: The user need to be a registered user to use the service.

    Scenario: Add new Language to the service
      Given The follow param "Java"
      When I try to create this language in the database
      Then I fetch an object of this language created

    Scenario: Fetch Language
      Given The name of the Programming Language created in the last scenario was "Java"
      When I try to fetch this language in the database by name
      Then I compere the name from the fetched with the assigned in the last scenario

    Scenario: Fetch a list of programming languages
      Given I need to query the list of languages
      When I fetch this list
      Then I should get a not empty list

    Scenario: Update programming language
      Given The follow name param "Python" is to update the current programming language "Java"
      When I try to update this entity in persistence in the database
      Then I should get the same name from the database

    Scenario: Delete programming language
      Given The id fetched by the param "Python"
      When I try to delete this entity in the database
      Then The entity don't exist in the database