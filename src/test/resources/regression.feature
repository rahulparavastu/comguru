Feature: Check Login functionalty
  As a user
  I am on Login page
  So i can login successfully

  @Sp
  Scenario: Login
    Given as a User I am on HomePage
    When I enter Username as "eastham"
    And I enter password as "eastham"
    And I click on "Sign-On"
    Then I can view "Flight Finder" page

  @Sp1
  Scenario Outline: Flight Search
    Given as a User I am on Flight Search Page
    When I select journey type "<type>"
    And I select passengers "<passengers>"
    And i select  Departing From "<Departing From>"
    And I select Arriving In> "<Arriving In>"
    And I select Service Class "<Service Class>"
    And I click on CONTINUE
    Then I can view "Blue Skies Airlines 360" selected

    Examples:
      | type       | passengers | Departing From | Arriving In | Service Class  |
      | Round Trip | 2          | London         | New York    | Economy class  |
      | One Way    | 1          | London         | Paris       | Business class |








