Feature: Create a new Pokemon Team

  Scenario: Create a new Pokemon Team with a request to API pokemon-dream-team
    Given I perform a POST request for 'team' to create a Pokemon Team
    When Verify if teamName returning in response header is equal to informed in the request
    Then Verify if the status code is '201'
    And Veify the contract betwenn Request and "pokemon-team-request.json"

