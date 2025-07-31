package com.flightbooking.stepDefinitions;

import com.flightbooking.pages.EnquiryPage;
import com.flightbooking.utils.WebDriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import java.util.concurrent.TimeoutException;

public class EnquiryStepDefinition {

    WebDriver driver = WebDriverFactory.getDriver();
    EnquiryPage enquiryPage = new EnquiryPage(driver);

    @Given("User is on the enquiry form page")
    public void user_is_on_the_enquiry_form_page() {
        driver.get("https://webapps.tekstac.com/ContactForm/");
    }

    @When("User submits the form with all fields blank")
    public void user_submits_the_form_with_all_fields_blank() throws InterruptedException {
        enquiryPage.clickSubmit();
    }

    @When("User enters invalid email format {string}")
    public void user_enters_invalid_email_format(String email) throws InterruptedException {
        enquiryPage.enterEmail(email);
        enquiryPage.clickSubmit();
    }

    @When("User enters special characters in Name {string}")
    public void user_enters_special_characters_in_name(String name) throws InterruptedException {
        enquiryPage.enterName(name);
        enquiryPage.clickSubmit();
    }

    @When("User enters message longer than {int} characters")
    public void user_enters_message_longer_than_characters(Integer length) throws InterruptedException {
        String longMessage = "a".repeat(length + 10);
        enquiryPage.enterMessage(longMessage);
        enquiryPage.clickSubmit();
    }

    @When("User enters valid name {string}, email {string}, phone {string}, subject {string}, and message {string}")
    public void user_enters_valid_data(String name, String email, String phone, String subject, String message) throws InterruptedException {
        enquiryPage.enterName(name);
        enquiryPage.enterEmail(email);
        enquiryPage.enterPhone(phone);
        enquiryPage.enterSubject(subject);
        enquiryPage.enterMessage(message);
        enquiryPage.clickSubmit();
    }

    @Then("Error message should be displayed for empty Name field")
    public void error_message_should_be_displayed_for_empty_name_field() {
        Assert.assertTrue("Expected name field error", enquiryPage.isNameErrorVisible());
    }

    @Then("Error message should be displayed for invalid email")
    public void error_message_should_be_displayed_for_invalid_email() {
        Assert.assertTrue("Expected email format error", enquiryPage.isEmailErrorVisible());
    }

    @Then("Error message should be displayed for invalid phone number")
    public void error_message_should_be_displayed_for_invalid_phone() {
        Assert.assertTrue("Expected phone number error", enquiryPage.isPhoneErrorVisible());
    }

    @Then("Enquiry should be submitted successfully")
    public void enquiry_should_be_submitted_successfully() throws InterruptedException, TimeoutException {
        Assert.assertTrue("Expected success message", enquiryPage.isSuccessMessageVisible());
        Assert.assertTrue("Success message text mismatch",
                enquiryPage.getSuccessMessageText().contains("Successfully Submitted"));
    }
}
