Feature: Create a new Pokemon Team not informing the field lastPokemon


  Scenario: Create a new Pokemon Team not informing the field lastPokemon
    Given I perform a POST request for 'team' not informing the field lastPokemon
    Then Verify if returning the message not informing the field lastPokemon "Field 'lastPokemon' cannot be null" and response code '400.001'
