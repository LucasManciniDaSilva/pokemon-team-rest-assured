Feature: Create a new Pokemon Team informing a teamName that already exists


  Scenario: Create a new Pokemon Team informing a teamName that already exists
    Given I perform a POST request for 'team' informing a teamName that already exists
    Then Verify if returning the message 'Team Already Exists' and response code '422.003'

