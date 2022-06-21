Feature: Testing different request on the store list in best by api application


  Scenario: Check if the store list can be accessed by users
    When User send GET request to store list endpoint
    Then User must get status code 200

  Scenario: Check if user can create id in store list
    When User send a POST request to create store list endpoint
    Then User must get a validate status code 201

  Scenario: User verify if created id added to store list
    When User sends a GET request to get store list endpoint
    Then User successfully added id in store list and status code is 200

  Scenario: User verify if created id Update to the store list
    When User sends a PATCH request to update store list endpoint
    Then User should update single data in store list successfully with status code 200

  Scenario: User verify if the created id is Delete from the store list
    When User sends a Delete request to delete store endpoint
    Then User should delete services id from store list successfully with status code 200
    And User check delete services id should not more exist in store list
    And User check deleted id have status code should be 404






