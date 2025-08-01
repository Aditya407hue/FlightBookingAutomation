package com.flightbooking.stepDefinitions;

import com.flightbooking.pages.FlightSearchPage;
import com.flightbooking.utils.WebDriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightSearchStepDefinitions {

    FlightSearchPage searchPage = new FlightSearchPage(WebDriverFactory.getDriver());

    @Given("the application URL is accessible and search")
    public void application_url_is_accessible() {
        // Already navigated in Hooks.java
        System.out.println("Application launched at: " + WebDriverFactory.getDriver().getCurrentUrl());
    }

    @And("I navigate to the Flight Search page")
    public void goToFlightSearchPage() {
        WebDriverFactory.getDriver().get("https://webapps.tekstac.com/FlightBooking/search.html");
    }

    @When("I enter the flight number {string}")
    public void enterFlightNumber(String number) {
        searchPage.enterFlightNumber(number);
    }

    @Then("the system should display {string} in the results")
    public void verifyFlightDisplayed(String expected) {
        Assert.assertTrue("Expected flight not displayed!", searchPage.isFlightDisplayed(expected));
    }

    @Then("the system should not display any matching airline")
    public void verifyNoFlightDisplayed() {
        Assert.assertTrue("Unexpected result found!", searchPage.isFlightNotDisplayed("SkyRider Express"));
    }

    @When("I search by flight name {string}")
    public void searchByFlightName(String name) {
        searchPage.enterFlightName(name);
    }

    @Then("the system should display flight name result {string}")
    public void flightNameShouldBeDisplayed(String expectedName) {
        Assert.assertTrue(searchPage.isFlightNameDisplayed(expectedName));
    }

    @Then("the system should display \"Data not found\" message")
    public void dataNotFoundForInvalidName() {
        Assert.assertTrue("'Data not found' message not visible!", searchPage.isNoDataMessageVisible());
    }

    @When("I search by flight type {string}")
    public void searchByFlightType(String type) {
        searchPage.enterFlightType(type);
    }

    @Then("the system should display flight type result {string}")
    public void flightTypeShouldBeDisplayed(String expectedType) {
        Assert.assertTrue(searchPage.isFlightTypeDisplayed(expectedType));
    }

    @Then("the system should show no flight type results")
    public void noFlightTypeShouldBeDisplayed() {
        Assert.assertFalse(searchPage.isFlightTypeDisplayed("Direct Flight"));
    }
}