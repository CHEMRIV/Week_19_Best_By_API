Feature: Testing different request on the services list in best by api application


  Scenario: Check if the services list can be accessed by users
    When User sendsa GET request to services list endpoint
    Then User should get status code 200

  Scenario: Check if user can create id in services list
    When User sends a POST request to create services list endpoint
    Then User must get back a validate status code 201

  Scenario: User verify if created id added to services list
    When User sends a GET request to get services list endpoint
    Then User successfully added id in services list and status code is 200

  Scenario: User verify if created id Update to the services list
    When User sends a PATCH request to update services list endpoint
    Then User should update single data in services list successfully with status code 200

  Scenario: User verify if the created id is Delete from the services list
    When User sends a Delete request to delete services endpoint
    Then User should delete services id from services list successfully with status code 200
    And User check delete services id should not more exist in category list
    And User finally check status code should be 404






