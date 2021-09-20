Feature: Create a new Pokemon Team not informing the field fifthPokemon


  Scenario: Create a new Pokemon Team not informing the field fifthPokemon
    Given I perform a POST request for 'team' not informing the field fifthPokemon
    Then Verify if returning the message not informing the field fifthPokemon "Field 'fifthPokemon' cannot be null" and response code '400.001'
