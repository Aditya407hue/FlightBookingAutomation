package com.flightbooking.stepDefinitions;

import com.flightbooking.pages.FlightBookingPage;
import com.flightbooking.utils.LoggerUtil;
import com.flightbooking.utils.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class FlightBookingStepDefinitions {
    private FlightBookingPage flightBookingPage;

    public FlightBookingStepDefinitions() {
        flightBookingPage = new FlightBookingPage();
    }

    @Given("The booking page is loaded and is accessible")
    public void theBookingPageIsLoadedAndIsAccessible() {
        LoggerUtil.info("Navigating to Flight Booking page.");
        // Assuming successful login leads to booking page or direct navigation if allowed
        flightBookingPage.navigateToLoginPage(ConfigReader.getProperty("base.url").replace("login.html", "booking.html")); // Adjust URL as per your application flow
        // Add assertion to verify if it's the booking page
    }

    @When("User leaves all booking form fields empty")
    public void userLeavesAllBookingFormFieldsEmpty() {
        LoggerUtil.info("Leaving all booking form fields empty.");
        // No explicit action needed as fields are empty by default
    }

    @And("Clicks on {string} button")
    public void clicksOnButton(String buttonName) {
        LoggerUtil.info("Clicking on " + buttonName + " button.");
        flightBookingPage.clickBookNow();
    }

    @Then("Error message for all mandatory fields should appear")
    public void errorMessageForAllMandatoryFieldsShouldAppear() {
        LoggerUtil.info("Verifying error messages for mandatory fields.");
        // This part depends heavily on how your application displays these errors.
        // You'll need to check for specific error messages or general error indicators.
        // For example:
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Travel From"), "Travel From error not found.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Travel To"), "Travel To error not found.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Date"), "Date error not found.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Name"), "Name error not found.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Email"), "Email error not found.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Phone Number"), "Phone Number error not found.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Passenger Count"), "Passenger Count error not found.");
    }

    @When("User leaves the {string} field empty")
    public void userLeavesTheFieldEmpty(String fieldName) {
        LoggerUtil.info("Leaving the " + fieldName + " field empty.");
        // This requires setting other fields to valid values while leaving one empty
        flightBookingPage.enterTravelFrom("Delhi");
        flightBookingPage.enterTravelTo("Mumbai");
        flightBookingPage.enterDate("30/07/2025");
        flightBookingPage.selectClass("Economy");
        flightBookingPage.enterPassengerCount("1");

        switch (fieldName) {
            case "Passenger Name":
                flightBookingPage.enterName("");
                flightBookingPage.enterEmail("test@example.com");
                flightBookingPage.enterPhoneNumber("1234567890");
                break;
            case "Phone Number":
                flightBookingPage.enterName("John Doe");
                flightBookingPage.enterEmail("test@example.com");
                flightBookingPage.enterPhoneNumber("");
                break;
            // Add more cases for other fields as needed
            default:
                throw new IllegalArgumentException("Field not recognized: " + fieldName);
        }
    }

    @Then("Error message for {string} should appear")
    public void errorMessageForFieldShouldAppear(String fieldName) {
        LoggerUtil.info("Verifying error message for " + fieldName + " field.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage(fieldName), "Error message for " + fieldName + " not found.");
    }

    @When("User enters invalid email {string}")
    public void userEntersInvalidEmail(String email) {
        LoggerUtil.info("Entering invalid email: " + email);
        flightBookingPage.enterName("Test User");
        flightBookingPage.enterEmail(email);
        flightBookingPage.enterPhoneNumber("1234567890");
        flightBookingPage.enterTravelFrom("Delhi");
        flightBookingPage.enterTravelTo("Mumbai");
        flightBookingPage.enterDate("30/07/2025");
        flightBookingPage.selectClass("Economy");
        flightBookingPage.enterPassengerCount("1");
    }

    @Then("Error message for invalid email format should appear")
    public void errorMessageForInvalidEmailFormatShouldAppear() {
        LoggerUtil.info("Verifying error message for invalid email format.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Email"), "Email format error not found.");
    }

    @When("User enters non-numeric phone number {string}")
    public void userEntersNonNumericPhoneNumber(String phoneNumber) {
        LoggerUtil.info("Entering non-numeric phone number: " + phoneNumber);
        flightBookingPage.enterName("Test User");
        flightBookingPage.enterEmail("valid@example.com");
        flightBookingPage.enterPhoneNumber(phoneNumber);
        flightBookingPage.enterTravelFrom("Delhi");
        flightBookingPage.enterTravelTo("Mumbai");
        flightBookingPage.enterDate("30/07/2025");
        flightBookingPage.selectClass("Economy");
        flightBookingPage.enterPassengerCount("1");
    }

    @Then("Error message for non-numeric phone number should appear")
    public void errorMessageForNonNumericPhoneNumberShouldAppear() {
        LoggerUtil.info("Verifying error message for non-numeric phone number.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Phone Number"), "Non-numeric phone number error not found.");
    }

    @When("User enters phone number {string} with less than 10 digits")
    public void userEntersPhoneNumberWithLessThanDigits(String phoneNumber) {
        LoggerUtil.info("Entering phone number with less than 10 digits: " + phoneNumber);
        flightBookingPage.enterName("Test User");
        flightBookingPage.enterEmail("valid@example.com");
        flightBookingPage.enterPhoneNumber(phoneNumber);
        flightBookingPage.enterTravelFrom("Delhi");
        flightBookingPage.enterTravelTo("Mumbai");
        flightBookingPage.enterDate("30/07/2025");
        flightBookingPage.selectClass("Economy");
        flightBookingPage.enterPassengerCount("1");
    }

    @Then("Error message for phone number length should appear")
    public void errorMessageForPhoneNumberLengthShouldAppear() {
        LoggerUtil.info("Verifying error message for phone number length.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Phone Number"), "Phone number length error not found.");
    }

    @When("User enters past date {string} in {string} field")
    public void userEntersPastDateInField(String date, String fieldName) {
        LoggerUtil.info("Entering past date " + date + " in " + fieldName + " field.");
        flightBookingPage.enterName("Test User");
        flightBookingPage.enterEmail("valid@example.com");
        flightBookingPage.enterPhoneNumber("1234567890");
        flightBookingPage.enterTravelFrom("Delhi");
        flightBookingPage.enterTravelTo("Mumbai");
        flightBookingPage.enterDate(date);
        flightBookingPage.selectClass("Economy");
        flightBookingPage.enterPassengerCount("1");
    }

    @Then("Error message for past date should appear")
    public void errorMessageForPastDateShouldAppear() {
        LoggerUtil.info("Verifying error message for past date.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Date"), "Past date error not found.");
    }

    @When("User enters invalid date format {string} in {string} field")
    public void userEntersInvalidDateFormatInField(String date, String fieldName) {
        LoggerUtil.info("Entering invalid date format " + date + " in " + fieldName + " field.");
        flightBookingPage.enterName("Test User");
        flightBookingPage.enterEmail("valid@example.com");
        flightBookingPage.enterPhoneNumber("1234567890");
        flightBookingPage.enterTravelFrom("Delhi");
        flightBookingPage.enterTravelTo("Mumbai");
        flightBookingPage.enterDate(date);
        flightBookingPage.selectClass("Economy");
        flightBookingPage.enterPassengerCount("1");
    }

    @Then("Error message for invalid date format should appear")
    public void errorMessageForInvalidDateFormatShouldAppear() {
        LoggerUtil.info("Verifying error message for invalid date format.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Date"), "Invalid date format error not found.");
    }

    @When("User enters non-date text {string} in {string} field")
    public void userEntersNonDateTextInField(String dateText, String fieldName) {
        LoggerUtil.info("Entering non-date text " + dateText + " in " + fieldName + " field.");
        flightBookingPage.enterName("Test User");
        flightBookingPage.enterEmail("valid@example.com");
        flightBookingPage.enterPhoneNumber("1234567890");
        flightBookingPage.enterTravelFrom("Delhi");
        flightBookingPage.enterTravelTo("Mumbai");
        flightBookingPage.enterDate(dateText);
        flightBookingPage.selectClass("Economy");
        flightBookingPage.enterPassengerCount("1");
    }

    @Then("Error message for non-date text should appear")
    public void errorMessageForNonDateTextShouldAppear() {
        LoggerUtil.info("Verifying error message for non-date text.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Date"), "Non-date text error not found.");
    }

    @When("User enters invalid date {string} in {string} field")
    public void userEntersInvalidDateInField(String date, String fieldName) {
        LoggerUtil.info("Entering invalid date " + date + " in " + fieldName + " field.");
        flightBookingPage.enterName("Test User");
        flightBookingPage.enterEmail("valid@example.com");
        flightBookingPage.enterPhoneNumber("1234567890");
        flightBookingPage.enterTravelFrom("Delhi");
        flightBookingPage.enterTravelTo("Mumbai");
        flightBookingPage.enterDate(date);
        flightBookingPage.selectClass("Economy");
        flightBookingPage.enterPassengerCount("1");
    }

    @Then("Error message for invalid date should appear")
    public void errorMessageForInvalidDateShouldAppear() {
        LoggerUtil.info("Verifying error message for invalid date.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Date"), "Invalid date error not found.");
    }

    @When("User enters valid details in all fields and sets passenger count to {string}")
    public void userEntersValidDetailsInAllFieldsAndSetsPassengerCountTo(String count) {
        LoggerUtil.info("Entering valid details with passenger count: " + count);
        flightBookingPage.enterTravelFrom("Delhi");
        flightBookingPage.enterTravelTo("Mumbai");
        flightBookingPage.enterDate("30/07/2025");
        flightBookingPage.selectClass("Economy");
        flightBookingPage.enterName("Valid Passenger");
        flightBookingPage.enterEmail("valid@example.com");
        flightBookingPage.enterPhoneNumber("1234567890");
        flightBookingPage.enterPassengerCount(count);
    }

    @Then("Error message for {int} passengers should appear")
    public void errorMessageForPassengersShouldAppear(int count) {
        LoggerUtil.info("Verifying error message for " + count + " passengers.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Passenger Count"), "Error for " + count + " passengers not found.");
    }

    @When("User increases passenger count using {string} button")
    public void userIncreasesPassengerCountUsingButton(String button) {
        LoggerUtil.info("Increasing passenger count.");
        flightBookingPage.enterPassengerCount("1"); // Start with 1 to test increase
        String initialCount = flightBookingPage.getDisplayedPassengerCount();
        flightBookingPage.increasePassengerCount();
        int expectedCount = Integer.parseInt(initialCount) + 1;
        Assert.assertEquals(flightBookingPage.getDisplayedPassengerCount(), String.valueOf(expectedCount), "Passenger count did not increase by 1.");
    }

    @Then("Passenger count should increase by {int}")
    public void passengerCountShouldIncreaseBy(int expectedIncrease) {
        LoggerUtil.info("Verifying passenger count increase.");
        // This assertion is already done in the @When step for immediate feedback
    }

    @When("User sets passenger count to {string} and tries to decrease it using {string} button")
    public void userSetsPassengerCountToAndTriesToDecreaseItUsingButton(String count, String button) {
        LoggerUtil.info("Attempting to decrease passenger count below 0.");
        flightBookingPage.enterPassengerCount(count); // Set to 0
        flightBookingPage.decreasePassengerCount();
    }

    @Then("Passenger count should not go below {int}")
    public void passengerCountShouldNotGoBelow(int expectedMin) {
        LoggerUtil.info("Verifying passenger count does not go below " + expectedMin + ".");
        Assert.assertEquals(flightBookingPage.getDisplayedPassengerCount(), String.valueOf(expectedMin), "Passenger count went below " + expectedMin + ".");
    }

    @When("User enters valid details in all fields and books a ticket")
    public void userEntersValidDetailsInAllFieldsAndBooksATicket() {
        LoggerUtil.info("Entering valid details and booking a ticket.");
        flightBookingPage.enterTravelFrom("Delhi");
        flightBookingPage.enterTravelTo("Mumbai");
        flightBookingPage.enterDate("30/07/2025");
        flightBookingPage.selectClass("Economy");
        flightBookingPage.enterName("Valid Passenger");
        flightBookingPage.enterEmail("valid@example.com");
        flightBookingPage.enterPhoneNumber("1234567890");
        flightBookingPage.enterPassengerCount("2"); // Example count
        flightBookingPage.clickBookNow();
    }

    @Then("Booking table appears successfully and VAT is calculated accurately")
    public void bookingTableAppearsSuccessfullyAndVATIsCalculatedAccurately() {
        LoggerUtil.info("Verifying booking table and VAT calculation.");
        Assert.assertTrue(flightBookingPage.isBookingTableDisplayed(), "Booking table is not displayed.");
        // Add assertions for VAT calculation (requires fetching values from UI and performing calculation)
        // This would typically involve new methods in FlightBookingPage to get subtotal, VAT, total.
    }

    @Then("Booking table appears successfully and Total is calculated accurately")
    public void bookingTableAppearsSuccessfullyAndTotalIsCalculatedAccurately() {
        LoggerUtil.info("Verifying booking table and Total calculation.");
        Assert.assertTrue(flightBookingPage.isBookingTableDisplayed(), "Booking table is not displayed.");
        // Add assertions for Total calculation (requires fetching values from UI and performing calculation)
    }

    @When("User enters same location in {string} and {string} fields")
    public void userEntersSameLocationInAndFields(String field1, String field2) {
        LoggerUtil.info("Entering same locations for Travel From and To.");
        String location = "Delhi";
        flightBookingPage.enterTravelFrom(location);
        flightBookingPage.enterTravelTo(location);
        flightBookingPage.enterDate("30/07/2025");
        flightBookingPage.selectClass("Economy");
        flightBookingPage.enterName("Valid Passenger");
        flightBookingPage.enterEmail("valid@example.com");
        flightBookingPage.enterPhoneNumber("1234567890");
        flightBookingPage.enterPassengerCount("1");
    }

    @Then("Error message that {string} and {string} cannot be same should appear")
    public void errorMessageThatAndCannotBeSameShouldAppear(String field1, String field2) {
        LoggerUtil.info("Verifying error message for same Travel From/To locations.");
        Assert.assertNotNull(flightBookingPage.getFieldErrorMessage("Travel From/To"), "Error for same locations not found.");
    }
}