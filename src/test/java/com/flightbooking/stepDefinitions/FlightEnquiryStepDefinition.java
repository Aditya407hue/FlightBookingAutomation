package com.flightbooking.stepDefinitions;

import com.flightbooking.pages.FlightEnquiryPage;
import com.flightbooking.utils.WebDriverFactory;
import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;


public class FlightEnquiryStepDefinition {

    WebDriver driver = WebDriverFactory.getDriver();
    FlightEnquiryPage enquiryPage  = new FlightEnquiryPage(driver);

    @Given("the application URL is accessible")
    public void application_url_is_accessible() {
        // Already navigated in Hooks.java
        System.out.println("Application launched at: " + driver.getCurrentUrl());
    }

    @And("I navigate to the Enquiry page")
    public void i_navigate_to_enquiry_page() {
      driver.get("https://webapps.tekstac.com/FlightBooking/contactus.html");
    }

    @And("I fill email field with valid data like {string}")
    public void i_enter_valid_email_format(String email) {
        enquiryPage.enterEmail(email);
    }

    @And("I fill email field with invalid data like {string}")
    public void i_enter_invalid_email_format(String email) {
        enquiryPage.enterEmail(email);
    }

    @And("I fill name field with valid data like {string}")
    public void name_field_data(String name) {
        enquiryPage.enterName(name);
    }

    @And("I fill phone field with valid data like {string}")
    public void phone_field_data(String phno)
    {
        enquiryPage.enterPhone(phno);
    }
    @And("I fill subject field with valid data like {string}")
    public void subject_field_data(String sub)
    {
        enquiryPage.enterSubject(sub);
    }

    @And("I fill message field with valid data like {string}")
    public void msg_field_data(String msg)
    {
        enquiryPage.enterMsg(msg);
    }

    @And("I fill name field with blank data like {string}")
    public void invalid_data(String s1) {
        enquiryPage.enterName(s1);
    }


    @And("I click the {string} button")
    public void i_click_the_button(String buttonName) throws InterruptedException {
        enquiryPage.clickSend();
    }

    @Then("no error message should be displayed")
    public void no_error_message_should_be_displayed() {
        Assert.assertFalse(enquiryPage.isErrorMessageDisplayed(), "Error message was displayed for a valid email!");
    }

    @Then("an appropriate error message should be displayed with proper styling")
    public void appropriate_error_message_should_be_displayed() {
        Assert.assertTrue(enquiryPage.isErrorMessageDisplayed(), "Error message was not displayed for invalid email!");
        //System.out.println("Error: " + enquiryPage.getErrorMessageText());
    }

    @And("the form submission should be prevented")
    public void form_submission_should_be_prevented() {
        Assert.assertTrue(enquiryPage.isErrorMessageDisplayed(), "Form submitted even though email was invalid!");
    }

    @And("the form should be submitted successfully")
    public void form_should_be_submitted_successfully() throws TimeoutException {
        Assert.assertTrue(enquiryPage.isSuccessMessageVisible(), "Form was not submitted successfully!");

        System.out.println("Success Message: " + enquiryPage.getSuccessMessageText());
    }


    @Then("an error message should be displayed")
    public void name_error_msg() {
        Assert.assertTrue(enquiryPage.getErrMsgName(), "Error message for name was not displayed!");

    }
    @And("the form submission should be prevented due to invalid name")
    public void form_submission_should_be_prevented_Name() {
        Assert.assertTrue(enquiryPage.getErrMsgName(), "Form submitted even though email was invalid!");
    }


    @When("I enter {string} in the Phone Number field")
    public void i_enter_invalid_phone_number(String phone) {
        enquiryPage.enterPhone(phone);
    }

    @Then("the form should not be submitted and error message should display")
    public void form_should_not_be_submitted() {
        boolean isSuccessVisible;
        try {
            isSuccessVisible = enquiryPage.isSuccessMessageVisible();
        } catch (TimeoutException e) {
            isSuccessVisible = false;
        }
        Assert.assertFalse(isSuccessVisible, "Form was submitted even with invalid phone!");
        Assert.assertTrue(enquiryPage.getErrMsgPhone(), "Error message not displayed for phone field!");
    }
}
