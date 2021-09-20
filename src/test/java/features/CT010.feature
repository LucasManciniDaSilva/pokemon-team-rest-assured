Feature: Create a new Pokemon Team not informing the field secondPokemon


  Scenario: Create a new Pokemon Team not informing the field secondPokemon
    Given I perform a POST request for 'team' not informing the field secondPokemon
    Then Verify if returning the message not informing the field secondPokemon "Field 'secondPokemon' cannot be null" and response code '400.001'
