Feature: first Feature File
  Scenario: first Scenario file
    Given url 'https://reqres.in/api/users?page=2'
    When method get
    Then status 200
    And print Tested