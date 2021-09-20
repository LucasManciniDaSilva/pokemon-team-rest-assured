Feature: Create a new Pokemon Team informing a Pokemon that not exists in field fifthPokemon


  Scenario: Create a new Pokemon Team informing a Pokemon that not exists in field fifthPokemon
    Given I perform a POST request for 'team' informing a Pokemon that not exists in field fifthPokemon
    Then Verify if returning the message for pokemon that not exists in field fifthPokemon 'Pokemon Not Found. Check your team.' and response code '404.002'

