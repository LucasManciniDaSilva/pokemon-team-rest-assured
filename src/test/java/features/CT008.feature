Feature: Create a new Pokemon Team informing a Pokemon that not exists in field lastPokemon


  Scenario: Create a new Pokemon Team informing a Pokemon that not exists in field lastPokemon
    Given I perform a POST request for 'team' informing a Pokemon that not exists in field lastPokemon
    Then Verify if returning the message for pokemon that not exists in field lastPokemon 'Pokemon Not Found. Check your team.' and response code '404.002'

