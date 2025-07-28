package com.flightbooking.stepDefinitions;

import com.flightbooking.pages.LoginPage;
import com.flightbooking.utils.ConfigReader;
import com.flightbooking.utils.LoggerUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepDefinitions {
    private LoginPage loginPage;

    public LoginStepDefinitions() {
        loginPage = new LoginPage();
    }

    @Given("User is on the Login page")
    public void userIsOnTheLoginPage() {
        LoggerUtil.info("Navigating to Login page.");
        loginPage.navigateToLoginPage(ConfigReader.getProperty("base.url"));
        // Basic assertion: URL should contain "login" or a specific element should be visible
        Assert.assertTrue(loginPage.getCurrentUrl().contains("login"), "User is not on the Login page.");
    }

    @When("User enters valid username {string} and password {string}")
    public void userEntersValidUsernameAndPassword(String username, String password) {
        LoggerUtil.info("Entering valid username and password.");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("Clicks on {string} button")
    public void clicksOnButton(String buttonName) {
        LoggerUtil.info("Clicking on " + buttonName + " button.");
        loginPage.clickLoginButton();
    }

    @Then("User should be successfully redirected to the home page")
    public void userShouldBeSuccessfullyRedirectedToTheHomePage() {
        LoggerUtil.info("Verifying redirection to home page.");
        // Assuming "dashboard" or "home" is part of the URL after successful login
        Assert.assertTrue(loginPage.getCurrentUrl().contains("home") || loginPage.getCurrentUrl().contains("dashboard"), "User was not redirected to home page.");
    }

    @When("User enters username {string} and invalid password {string}")
    public void userEntersUsernameAndInvalidPassword(String username, String password) {
        LoggerUtil.info("Entering username '" + username + "' and invalid password.");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("User enters random username {string} and password {string}")
    public void userEntersRandomUsernameAndPassword(String username, String password) {
        LoggerUtil.info("Entering random username '" + username + "' and password '" + password + "'.");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("User enters invalid username {string} and password {string}")
    public void userEntersInvalidUsernameAndPassword(String username, String password) {
        LoggerUtil.info("Entering invalid username '" + username + "' and password '" + password + "'.");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("User leaves all login fields empty")
    public void userLeavesAllLoginFieldsEmpty() {
        LoggerUtil.info("Leaving all login fields empty.");
        loginPage.enterUsername("");
        loginPage.enterPassword("");
    }

    @When("User enters username {string} and leaves password field empty")
    public void userEntersUsernameAndLeavesPasswordFieldEmpty(String username) {
        LoggerUtil.info("Entering username '" + username + "' and leaving password empty.");
        loginPage.enterUsername(username);
        loginPage.enterPassword("");
    }

    @When("User enters password {string} and leaves username field empty")
    public void userEntersPasswordAndLeavesUsernameFieldEmpty(String password) {
        LoggerUtil.info("Entering password and leaving username empty.");
        loginPage.enterUsername("");
        loginPage.enterPassword(password);
    }

    @When("User enters special characters {string} in username field")
    public void userEntersSpecialCharactersInUsernameField(String characters) {
        LoggerUtil.info("Entering special characters '" + characters + "' in username field.");
        loginPage.enterUsername(characters);
        loginPage.enterPassword(ConfigReader.getProperty("password")); // Use a valid password to allow submission
    }

    @When("User enters XSS payload {string} in input fields")
    public void userEntersXSSPayloadInInputFields(String payload) {
        LoggerUtil.info("Entering XSS payload '" + payload + "' in input fields.");
        loginPage.enterUsername(payload);
        loginPage.enterPassword(payload);
    }

    @When("User enters special characters {string} in password field")
    public void userEntersSpecialCharactersInPasswordField(String characters) {
        LoggerUtil.info("Entering special characters '" + characters + "' in password field.");
        loginPage.enterUsername(ConfigReader.getProperty("username")); // Use a valid username to allow submission
        loginPage.enterPassword(characters);
    }

    @Then("An error message should appear")
    public void anErrorMessageShouldAppear() {
        LoggerUtil.info("Verifying an error message appears.");
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMessage, "Error message did not appear.");
        Assert.assertFalse(errorMessage.isEmpty(), "Error message is empty.");
    }

    @Then("An error message for {string} field should appear")
    public void anErrorMessageForFieldShouldAppear(String fieldName) {
        LoggerUtil.info("Verifying error message for " + fieldName + " field.");
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMessage, "Error message did not appear for " + fieldName + ".");
        Assert.assertTrue(errorMessage.toLowerCase().contains(fieldName.toLowerCase()) || errorMessage.toLowerCase().contains("required"),
                "Error message for " + fieldName + " is not relevant or not found: " + errorMessage);
    }
}