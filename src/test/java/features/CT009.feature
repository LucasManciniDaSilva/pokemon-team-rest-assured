Feature: Create a new Pokemon Team not informing the field firstPokemon


  Scenario: Create a new Pokemon Team not informing the field firstPokemon
    Given I perform a POST request for 'team' not informing the field firstPokemon
    Then Verify if returning the message not informing the field firstPokemon "Field 'firstPokemon' cannot be null" and response code '400.001'
