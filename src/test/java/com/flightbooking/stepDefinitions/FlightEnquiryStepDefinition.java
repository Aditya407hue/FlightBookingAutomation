package com.flightbooking.stepDefinitions;

import com.flightbooking.pages.FlightEnquiryPage;
import com.flightbooking.utils.WebDriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import java.util.concurrent.TimeoutException;

public class FlightEnquiryStepDefinition {

    WebDriver driver = WebDriverFactory.getDriver();
    FlightEnquiryPage flightEnquiryPage = new FlightEnquiryPage(driver);

    @Given("User is on the enquiry form page")
    public void user_is_on_the_enquiry_form_page() {
        driver.get("https://webapps.tekstac.com/ContactForm/");
    }

    @When("User submits the form with all fields blank")
    public void user_submits_the_form_with_all_fields_blank() throws InterruptedException {
        flightEnquiryPage.clickSubmit();
    }

    @When("User enters invalid email format {string}")
    public void user_enters_invalid_email_format(String email) throws InterruptedException {
        flightEnquiryPage.enterEmail(email);
        flightEnquiryPage.clickSubmit();
    }

    @When("User enters special characters in Name {string}")
    public void user_enters_special_characters_in_name(String name) throws InterruptedException {
        flightEnquiryPage.enterName(name);
        flightEnquiryPage.clickSubmit();
    }

    @When("User enters message longer than {int} characters")
    public void user_enters_message_longer_than_characters(Integer length) throws InterruptedException {
        String longMessage = "a".repeat(length + 10);
        flightEnquiryPage.enterMessage(longMessage);
        flightEnquiryPage.clickSubmit();
    }

    @When("User enters valid name {string}, email {string}, phone {string}, subject {string}, and message {string}")
    public void user_enters_valid_data(String name, String email, String phone, String subject, String message) throws InterruptedException {
        flightEnquiryPage.enterName(name);
        flightEnquiryPage.enterEmail(email);
        flightEnquiryPage.enterPhone(phone);
        flightEnquiryPage.enterSubject(subject);
        flightEnquiryPage.enterMessage(message);
        flightEnquiryPage.clickSubmit();
    }

    @Then("Error message should be displayed for empty Name field")
    public void error_message_should_be_displayed_for_empty_name_field() {
        Assert.assertTrue("Expected name field error", flightEnquiryPage.isNameErrorVisible());
    }

    @Then("Error message should be displayed for invalid email")
    public void error_message_should_be_displayed_for_invalid_email() {
        Assert.assertTrue("Expected email format error", flightEnquiryPage.isEmailErrorVisible());
    }

    @Then("Error message should be displayed for invalid phone number")
    public void error_message_should_be_displayed_for_invalid_phone() {
        Assert.assertTrue("Expected phone number error", flightEnquiryPage.isPhoneErrorVisible());
    }

    @Then("Enquiry should be submitted successfully")
    public void enquiry_should_be_submitted_successfully() throws InterruptedException, TimeoutException {
        Assert.assertTrue("Expected success message", flightEnquiryPage.isSuccessMessageVisible());
        Assert.assertTrue("Success message text mismatch",
                flightEnquiryPage.getSuccessMessageText().contains("Successfully Submitted"));
    }
}
