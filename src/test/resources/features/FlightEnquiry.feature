@Enquiry
Feature: Email Field Validation on Enquiry Page

  As a user filling out the Enquiry form,
  I want the email field to accept only valid email formats,
  So that I can ensure proper communication and prevent form submission with invalid emails.

  Background:
    Given the application URL is accessible
    And I navigate to the Enquiry page

  Scenario: Valid Email Address
    And I fill email field with valid data like "neeti@gmail.com"
    And I fill name field with valid data like "Neeti"
    And I fill phone field with valid data like "1234567890"
    And I fill subject field with valid data like "This is test message"
    And I fill message field with valid data like "I am not satisfy with service"
    And I click the "SEND" button
    Then no error message should be displayed
    And the form should be submitted successfully

  Scenario: Invalid Email Address
    And I fill email field with invalid data like "neeti"
    And I fill name field with valid data like "Neeti"
    And I fill phone field with valid data like "1234567890"
    And I fill subject field with valid data like "This is test message"
    And I fill message field with valid data like "Poor service"
    And I click the "SEND" button
    Then an appropriate error message should be displayed with proper styling
    And the form submission should be prevented

  Scenario: Valid Messages Field
    And I fill name field with valid data like "Neeti"
    And I fill email field with valid data like "neeti@gmail.com"
    And I fill phone field with valid data like "1234567890"
    And I fill subject field with valid data like "This is test message"
    And I fill message field with valid data like "Very bad service"
    And I click the "SEND" button
    Then no error message should be displayed
    And the form should be submitted successfully

  Scenario: Valid Name Field
    And I fill name field with valid data like "Neeti"
    And I fill email field with valid data like "neeti@gmail.com"
    And I fill phone field with valid data like "1234567890"
    And I fill subject field with valid data like "This is test message"
    And I fill message field with valid data like "I am not satisfied with service"
    And I click the "SEND" button
    Then no error message should be displayed
    And the form should be submitted successfully

  Scenario: Invalid Name Field
    And I fill name field with blank data like ""
    And I fill email field with valid data like "neeti@gmail.com"
    And I fill phone field with valid data like "1234567890"
    And I fill subject field with valid data like "This is test message"
    And I fill message field with valid data like "I am not satisfy with service"
    And I click the "SEND" button
    Then an error message should be displayed
    And the form submission should be prevented due to invalid name

  Scenario: Valid Phone Field
    And I fill name field with valid data like "Neeti"
    And I fill email field with valid data like "neeti@gmail.com"
    And I fill phone field with valid data like "1234567890"
    And I fill subject field with valid data like "This is test message"
    And I fill message field with valid data like "I am not satisfy with service"
    And I click the "SEND" button
    Then no error message should be displayed
    And the form should be submitted successfully

  Scenario: Valid Phone Field
    And I fill name field with valid data like "Neeti"
    And I fill email field with valid data like "neeti@gmail.com"
    And I fill phone field with valid data like "1234567890"
    And I fill subject field with valid data like "This is test message"
    And I fill message field with valid data like "I am not satisfy with service"
    And I click the "SEND" button
    Then no error message should be displayed
    And the form should be submitted successfully

  Scenario Outline: Invalid phone number should prevent form submission
    When I enter "<phone>" in the Phone Number field
    And I fill name field with valid data like "Neeti"
    And I fill email field with valid data like "neeti@gmail.com"
    And I fill subject field with valid data like "This is test message"
    And I fill message field with valid data like "I am not satisfy with service"
    And I click the "SEND" button
    Then the form should not be submitted and error message should display


    Examples:
      | phone     |
      | abc-123   |
      | 12345     |
      | 123abc456 |
      | !@*&)({}  |
      | 987654321 |  