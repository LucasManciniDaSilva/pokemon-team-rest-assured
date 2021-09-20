Feature: Create a new Pokemon Team informing a Pokemon that not exists in field thirdPokemon


  Scenario: Create a new Pokemon Team informing a Pokemon that not exists in field thirdPokemon
    Given I perform a POST request for 'team' informing a Pokemon that not exists in field thirdPokemon
    Then Verify if returning the message for pokemon that not exists in field first thirdPokemon 'Pokemon Not Found. Check your team.' and response code '404.002'

