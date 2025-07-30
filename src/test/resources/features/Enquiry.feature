Feature: Enquiry Form Testing

  Scenario: Submit form with all fields blank
    Given user is on the enquiry form page
    When user submits the form without filling any fields
    Then error messages should be displayed for all fields

  Scenario: Enter invalid email format
    Given user is on the enquiry form page
    When user enters "John" in Name, "john#mail.com" in Email, and a message
    And clicks on Submit
    Then error message for invalid email should appear

  Scenario: Submit form with special characters in Name
    Given user is on the enquiry form page
    When user enters "@John123" in Name, valid Email, and a message
    And clicks on Submit
    Then error message for invalid characters in Name should appear

  Scenario: Submit message longer than 500 characters
    Given user is on the enquiry form page
    When user enters valid Name, Email and more than 500 characters in message
    And clicks on Submit
    Then error for message length should appear

  Scenario: Submit valid enquiry form
    Given user is on the enquiry form page
    When user enters valid Name, Email, and message
    And clicks on Submit
    Then enquiry should be submitted successfully

  Scenario: Submit form with empty Name field
    Given user is on the enquiry form page
    When user leaves Name field blank, enters valid Email and message
    And clicks on Submit
    Then error message for Name field should be displayed
