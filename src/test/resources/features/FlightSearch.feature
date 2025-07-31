
Feature: Flight Search Functionality

  As a user using the Flight Search page,
  I want to search by flight number, name and type
  So that I can view the correct airline details.

  Background:
    Given the application URL is accessible and search
    And I navigate to the Flight Search page

  Scenario: Valid Flight Number
    When I enter the flight number "AC789"
    Then the system should display "SkyRider Express" in the results

  Scenario: Invalid Flight Number
    When I enter the flight number "INVALID123"
    Then the system should not display any matching airline

  Scenario: Valid Flight Name
    When I search by flight name "Unity Express"
    Then the system should display flight name result "Unity Express"

  Scenario: Invalid Flight Name
    When I search by flight name "AirIndia"
    Then the system should display "Data not found" message

  Scenario: Valid Flight Type
    When I search by flight type "Direct Flight"
    Then the system should display flight type result "Direct Flight"

  Scenario: Invalid Flight Type
    When I search by flight type "Indirect Flight"
    Then the system should show no flight type results