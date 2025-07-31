package com.flightbooking.stepDefinitions;

import com.flightbooking.utils.WebDriverFactory;
import com.flightbooking.pages.FlightBookingPage;
import io.cucumber.java.en.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.Assert.*;

public class FlightBookingStepDefinitions {

    FlightBookingPage page;

    @Given("the application is accessible")
    public void application_url_is_accessible() {
        // Already navigated in Hooks.java
        System.out.println("Application launched at: " + WebDriverFactory.getDriver().getCurrentUrl());
    }

    @And("I navigate to the Booking page")
    public void i_navigate_to_booking_page() {
        WebDriverFactory.getDriver().get("https://webapps.tekstac.com/FlightBooking/index.html");
        page = new FlightBookingPage(WebDriverFactory.getDriver());

        JavascriptExecutor js = (JavascriptExecutor) WebDriverFactory.getDriver();
        js.executeScript("window.scrollTo(0, 0);");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }


    @When("User enters {string} in Travel From field")
    public void enterTravelFrom(String from) {
        page.travelFrom.clear();
        page.travelFrom.sendKeys(from);
    }

    @When("User enters {string} in Travel To field")
    public void enterTravelTo(String to) {
        page.travelTo.clear();
        page.travelTo.sendKeys(to);
    }

    @When("User enters {string} as Departure Date")
    public void enterDate(String date) {
        page.departureDate.clear();
        page.departureDate.sendKeys(date);
    }

    @When("User selects {string}")
    public void selectClass(String cls) {
        page.travelClass.sendKeys(cls);
    }

    @When("User enters {string} as Passenger Name")
    public void enterName(String name) {
        page.passengerName.clear();
        page.passengerName.sendKeys(name);
    }

    @When("User enters {string} as Email")
    public void enterEmail(String email) {
        page.email.clear();
        page.email.sendKeys(email);
    }

    @When("User enters {string} as Phone Number")
    public void enterPhone(String phone) {
        page.phoneNumber.clear();
        page.phoneNumber.sendKeys(phone);
    }

    @When("User sets {string} Passenger")
    public void setPassenger(String count) {
        int n = Integer.parseInt(count);
        for (int i = 0; i < n; i++) {
            page.plusButton.click();
        }
    }

    @When("User clicks on Book Ticket")
    public void clickBook() {
        page.clickBookButton();
    }

    @Then("Booking should be successful")
    public void bookingSuccessful() {
        assertTrue("Success message not displayed", page.isSuccessDisplayed());
    }

    @Then("Error message {string} should be displayed")
    public void verifyError(String expected) {
        assertEquals(expected, page.getErrorMessage());
    }

    @When("fills all other fields with valid data")
    public void fillValidDetails() {
        page.fillBookingForm("London", "Mumbai", "12/07/2025", "First Class", "Ananya", "ananya@gmail.com", "9876543210", 1);
    }

    @When("User books a ticket with valid data")
    public void bookValidTicket() {
        fillValidDetails();
        page.clickBookButton();
    }

    @When("User tries to book again with same details")
    public void bookDuplicateTicket() {
        page.clickBookButton();
    }

    @Then("Duplicate booking warning should be displayed")
    public void verifyDuplicateWarning() {
        String error = page.getErrorMessage();
        assertTrue("Duplicate booking warning not shown", error.contains("duplicate") || error.contains("already booked"));
    }

}