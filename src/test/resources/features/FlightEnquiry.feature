@Enquiry

Feature: Enquiry Form Validations



  Scenario: Submit form with all fields blank
    Given User is on the enquiry form page
    When User submits the form with all fields blank
    Then Error message should be displayed for empty Name field
    And Error message should be displayed for invalid email

  Scenario: Enter invalid email format
    Given User is on the enquiry form page
    When User enters invalid email format "john#mail.com"
    Then Error message should be displayed for invalid email

  Scenario: Enter special characters in Name
    Given User is on the enquiry form page
    When User enters special characters in Name "@John123"
    Then Enquiry should be submitted successfully

  Scenario: Enter message longer than 500 characters
    Given User is on the enquiry form page
    When User enters message longer than 500 characters
    Then Enquiry should be submitted successfully

  Scenario: Submit valid enquiry
    Given User is on the enquiry form page
    When User enters valid name "John", email "john@mail.com", phone "9876543210", subject "Booking", and message "Need help with booking"
    Then Enquiry should be submitted successfully

  Scenario: Submit form with invalid phone number
    Given User is on the enquiry form page
    When User enters valid name "Neeti", email "neeti@mail.com", phone "123", subject "Help", and message "Short phone test"
    Then Error message should be displayed for invalid phone number
