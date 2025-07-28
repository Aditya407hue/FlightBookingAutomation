# src/test/resources/features/Login.feature
Feature: Login Module Functionality

  @Login
  Scenario: Successful Login with valid credentials
    Given User is on the Login page
    When User enters valid username "flightadmin" and password "flightadmin"
    And Clicks on "Login" button
    Then User should be successfully redirected to the home page

  @Login
  Scenario: Login with incorrect password
    Given User is on the Login page
    When User enters username "flightadmin" and invalid password "wrongpass"
    And Clicks on "Login" button
    Then An error message should appear

  @Login
  Scenario: Login with a non-existent user
    Given User is on the Login page
    When User enters random username "nonexistent" and password "randompass"
    And Clicks on "Login" button
    Then An error message should appear

  @Login
  Scenario: Login with incorrect username and password
    Given User is on the Login page
    When User enters invalid username "invaliduser" and password "invalidpass"
    And Clicks on "Login" button
    Then An error message should appear

  @Login
  Scenario: Submit login with all fields blank
    Given User is on the Login page
    When User leaves all login fields empty
    And Clicks on "Login" button
    Then An error message should appear

  @Login
  Scenario: Submit login with only username
    Given User is on the Login page
    When User enters username "flightadmin" and leaves password field empty
    And Clicks on "Login" button
    Then An error message for "password" field should appear

  @Login
  Scenario: Submit login with only password
    Given User is on the Login page
    When User enters password "flightadmin" and leaves username field empty
    And Clicks on "Login" button
    Then An error message for "username" field should appear

  @Login
  Scenario: Enter special characters in username field
    Given User is on the Login page
    When User enters special characters "user!@#" in username field
    And Clicks on "Login" button
    Then An error message should appear

  @Login
  Scenario: Enter script tag or XSS payload in input fields
    Given User is on the Login page
    When User enters XSS payload "<script>alert(1)</script>" in input fields
    And Clicks on "Login" button
    Then An error message should appear

  @Login
  Scenario: Enter special characters in password field
    Given User is on the Login page
    When User enters special characters "pass!@#" in password field
    And Clicks on "Login" button
    Then An error message should appear