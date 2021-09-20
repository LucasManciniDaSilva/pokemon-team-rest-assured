Feature: Create a new Pokemon Team not informing the field fourthPokemon


  Scenario: Create a new Pokemon Team not informing the field fourthPokemon
    Given I perform a POST request for 'team' not informing the field fourthPokemon
    Then Verify if returning the message not informing the field fourthPokemon "Field 'fourthPokemon' cannot be null" and response code '400.001'
