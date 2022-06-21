Feature: Testing different request on the utility in best by api application


  Scenario: Check the version of Application
    When User send a GET request to check version of application
    Then User must get return status code 200

  Scenario: Check the Health check of Application
    When User send a GET request to  Helth check of application
    Then User must check status code 200








