Feature: Create a new Pokemon Team not informing the field thirdPokemon


  Scenario: Create a new Pokemon Team not informing the field thirdPokemon
    Given I perform a POST request for 'team' not informing the field thirdPokemon
    Then Verify if returning the message not informing the field thirdPokemon "Field 'thirdPokemon' cannot be null" and response code '400.001'
