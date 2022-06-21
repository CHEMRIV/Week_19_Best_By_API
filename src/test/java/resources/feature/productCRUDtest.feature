Feature: Testing different request on the product list in best by api application


  Scenario: Check if the product list can be accessed by users
    When User sends a GET request to product list endpoint
    Then User should get back a valid status code 200

  Scenario: Check if user can create id in product list
    When User sends a POST request to create product list endpoint
    Then User must get back a validable status code 200

  Scenario: User verify if created id added to product list
    When User sends a GET request to get product list endpoint
    Then User successfully added id in product list and status code is 201

  Scenario: User verify if created id Update to the product list
    When User sends a PATCH request to update product list endpoint
    Then User should update single data in product list successfully with status code 201

  Scenario: User verify if the created id is Delete from the application
    When User sends a Delete request to delete endpoint
    Then User should delete category id from product list successfully with status code 200
    And User check delete should not more exist in category list
    And User check status code should be 404






