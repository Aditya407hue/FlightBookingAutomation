Feature: Login Module Functionality

  Scenario: Successful Login with valid credentials
    Given User is on the Login page
    When User enters valid username "flightadmin" and password "flightadmin"
    And Clicks on "Login" button
    Then User should be successfully redirected to the home page

  Scenario: Login with incorrect password
    Given User is on the Login page
    When User enters username "flightadmin" and invalid password "wrongpass"
    And Clicks on "Login" button
    Then An error message "Invalid Credentials" should appear

  Scenario: Login with a non-existent user
    Given User is on the Login page
    When User enters random username "nonexistent" and password "randompass"
    And Clicks on "Login" button
    Then An error message "Invalid Credentials" should appear

  Scenario: Login with incorrect username and password
    Given User is on the Login page
    When User enters invalid username "invaliduser" and password "invalidpass"
    And Clicks on "Login" button
    Then An error message "Invalid Credentials" should appear

  Scenario: Submit login with all fields blank
    Given User is on the Login page
    When User leaves all login fields empty
    And Clicks on "Login" button
    Then An error message "Please enter username and password" should appear

  Scenario: Submit login with only username
    Given User is on the Login page
    When User enters username "flightadmin" and leaves password field empty
    And Clicks on "Login" button
    Then An error message "Password is required" should appear

  Scenario: Submit login with only password
    Given User is on the Login page
    When User enters password "flightadmin" and leaves username field empty
    And Clicks on "Login" button
    Then An error message "Username is required" should appear

  Scenario: Enter special characters in username field
    Given User is on the Login page
    When User enters special characters "user!@#" in username field
    And Clicks on "Login" button
    Then An error message "Invalid characters in username" should appear

  Scenario: Enter script tag or XSS payload in input fields
    Given User is on the Login page
    When User enters XSS payload "<script>alert(1)</script>" in input fields
    And Clicks on "Login" button
    Then An error message "Invalid input" should appear

  Scenario: Enter special characters in password field
    Given User is on the Login page
    When User enters special characters "pass!@#" in password field
    And Clicks on "Login" button
    Then An error message "Invalid characters in password" should appear