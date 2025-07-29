
Feature: Ticket Booking Module Functionality

  Scenario: Submit form with all fields blank
    Given The booking page is loaded and is accessible
    When User leaves all booking form fields empty
    And Clicks on "Book Now" button
    Then Error message for all mandatory fields should appear

  Scenario: Leave "Name" field empty
    Given The booking page is loaded and is accessible
    When User leaves the "Passenger Name" field empty
    And Clicks on "Book Now" button
    Then Error message for "Passenger Name" should appear

  Scenario: Enter invalid email format
    Given The booking page is loaded and is accessible
    When User enters invalid email "john#gfail.com"
    And Clicks on "Book Now" button
    Then Error message for invalid email format should appear

  Scenario: Enter non-numeric phone number
    Given The booking page is loaded and is accessible
    When User enters non-numeric phone number "abcd123"
    And Clicks on "Book Now" button
    Then Error message for non-numeric phone number should appear

  Scenario: Enter less than 10 digit phone number
    Given The booking page is loaded and is accessible
    When User enters phone number "98765" with less than 10 digits
    And Clicks on "Book Now" button
    Then Error message for phone number length should appear

  Scenario: Enter past date in 'Departure Date' field
    Given The booking page is loaded and is accessible
    When User enters past date "01/07/2024" in "Departure Date" field
    And Clicks on "Book Now" button
    Then Error message for past date should appear

  Scenario: Enter invalid date format in 'Departure Date' field
    Given The booking page is loaded and is accessible
    When User enters invalid date format "August 15,20205" in "Departure Date" field
    And Clicks on "Book Now" button
    Then Error message for invalid date format should appear

  Scenario: Enter a non-date text in 'Departure Date' field
    Given The booking page is loaded and is accessible
    When User enters non-date text "abcd123" in "Departure Date" field
    And Clicks on "Book Now" button
    Then Error message for non-date text should appear

  Scenario: Enter an invalid date in 'Departure Date' field
    Given The booking page is loaded and is accessible
    When User enters invalid date "34/13/1999" in "Departure Date" field
    And Clicks on "Book Now" button
    Then Error message for invalid date should appear

  Scenario: Book with 0 passengers
    Given The booking page is loaded and is accessible
    When User enters valid details in all fields and sets passenger count to "0"
    And Clicks on "Book Now" button
    Then Error message for 0 passengers should appear

  Scenario: Increase passenger count using "+" button
    Given The booking page is loaded and is accessible
    When User increases passenger count using "+" button
    Then Passenger count should increase by 1

  Scenario: Decrease passenger count using "-" button when count is 0
    Given The booking page is loaded and is accessible
    When User sets passenger count to "0" and tries to decrease it using "-" button
    Then Passenger count should not go below 0

  Scenario: Validate VAT as 3% of subtotal
    Given The booking page is loaded and is accessible
    When User enters valid details in all fields and books a ticket
    Then Booking table appears successfully and VAT is calculated accurately

  Scenario: Validate Total = Subtotal + VAT
    Given The booking page is loaded and is accessible
    When User enters valid details in all fields and books a ticket
    Then Booking table appears successfully and Total is calculated accurately

  Scenario: Travel From and To cannot be same
    Given The booking page is loaded and is accessible
    When User enters same location in "Travel From" and "Travel To" fields
    And Clicks on "Book Now" button
    Then Error message that "Travel From" and "Travel To" cannot be same should appear