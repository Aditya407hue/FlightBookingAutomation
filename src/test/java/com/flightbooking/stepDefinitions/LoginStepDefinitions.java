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
        Assert.assertTrue(loginPage.getCurrentUrl().contains("home"), "User was not redirected to home page.");
    }

    @When("User enters username {string} and invalid password {string}")
    public void userEntersUsernameAndInvalidPassword(String username, String password) {
        LoggerUtil.info("Entering username and invalid password.");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @Then("An error message {string} should appear")
    public void anErrorMessageShouldAppear(String expectedErrorMessage) {
        LoggerUtil.info("Verifying error message: " + expectedErrorMessage);
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "Error message mismatch.");
    }

    @When("User enters random username {string} and password {string}")
    public void userEntersRandomUsernameAndPassword(String username, String password) {
        LoggerUtil.info("Entering random username and password.");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("User enters invalid username {string} and password {string}")
    public void userEntersInvalidUsernameAndPassword(String username, String password) {
        LoggerUtil.info("Entering invalid username and password.");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("User leaves all login fields empty")
    public void userLeavesAllLoginFieldsEmpty() {
        LoggerUtil.info("Leaving all login fields empty.");
        // No action needed as fields are already empty by default
    }

    @When("User enters username {string} and leaves password field empty")
    public void userEntersUsernameAndLeavesPasswordFieldEmpty(String username) {
        LoggerUtil.info("Entering username and leaving password empty.");
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
        LoggerUtil.info("Entering special characters in username field.");
        loginPage.enterUsername(characters);
        loginPage.enterPassword(ConfigReader.getProperty("password")); // Provide a valid password to proceed
    }

    @When("User enters XSS payload {string} in input fields")
    public void userEntersXSSPayloadInInputFields(String payload) {
        LoggerUtil.info("Entering XSS payload in input fields.");
        loginPage.enterUsername(payload);
        loginPage.enterPassword(payload);
    }

    @When("User enters special characters {string} in password field")
    public void userEntersSpecialCharactersInPasswordField(String characters) {
        LoggerUtil.info("Entering special characters in password field.");
        loginPage.enterUsername(ConfigReader.getProperty("username")); // Provide a valid username to proceed
        loginPage.enterPassword(characters);
    }
}