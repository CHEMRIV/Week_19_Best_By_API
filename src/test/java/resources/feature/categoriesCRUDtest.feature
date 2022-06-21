Feature: Testing different request on the Category list in best by api application


  Scenario: Check if the best by api application can be accessed by users
    When User sends a GET request to list endpoint
    Then User must get back a valid status code 200

  Scenario: Check if the best by api application can be create users ids
    When User sends a POST request to create endpoint
    Then User must get back a validatable status code 201

  Scenario: User verify if the Category was added to the application
    When User sends a GET request to get endpoint
    Then User successfully added id in best by application and status code is 201

  Scenario: User verify if the Category Update to the application
    When User sends a PATCH request to get endpoint
    Then User should update single data successfully with status code 201

  Scenario: User verify if the Category id is Delete from the application
    When User sends a Delete request to get endpoint
    Then User should delete category id successfully with status code 200
    And User check delete should not exist in category list
    And User check status code 404






