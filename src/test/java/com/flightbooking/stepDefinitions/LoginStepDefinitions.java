package com.flightbooking.stepDefinitions;

import com.flightbooking.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.List;

public class LoginStepDefinitions {

    WebDriver driver = com.flightbooking.utils.WebDriverFactory.getDriver();
    LoginPage login;

    @Given("Start browser and navigate to login page")
    public void start_browser() {
        login = new LoginPage(driver);
    }

    @And("user enters credentials to login")
    public void user_enters_valid_credentials(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        login.setUsername(data.get(0).get(0));
        login.setPassword(data.get(0).get(1));
    }

    @And("user enters valid username and invalid password")
    public void user_enters_valid_username_invalid_password(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        login.setUsername(data.get(0).get(0));
        login.setPassword(data.get(0).get(1));
    }

    @And("user enters invalid username and valid password")
    public void user_enters_invalid_username_valid_password(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        login.setUsername(data.get(0).get(0));
        login.setPassword(data.get(0).get(1));
    }

    @And("user leaves username and password fields empty")
    public void user_leaves_fields_empty() {
        login.setUsername("");
        login.setPassword("");
    }

    @And("user get the captcha generated and enters valid captcha code")
    public void enter_captcha() throws InterruptedException {
        login.setCaptcha();
        login.clickValidate();

    }

    @And("click on login button")
    public void click_login() throws InterruptedException {
        login.clickLogin();

    }

    @Then("it should display alert message and click ok")
    public void verify_success_alert() {
        login.verifyAlert();

    }

    @Then("it should display password error message")
    public void verify_password_error() {
        login.getPasswordError();
    }

    @Then("it should display username error message")
    public void verify_username_error() {
        login.getUsernameError();
    }

    @Then("it should display username and password error messages")
    public void verify_empty_field_error() {
        login.getEmptyError();
    }

    @And("check the Remember Me checkbox and accept alerts")
    public void check_remember_me() {
        login.checkRememberMe();
    }

    @And("credentials should be remembered on next visit")
    public void verify_remembered_credentials() {
        login.verifyRememberedCredentials("flightadmin", "flightadmin");
    }

    @And("user clicks on the Forgot your password link")
    public void click_forgot_password() {
        login.clickForgotPassword();
    }

    @Then("it should redirect to the reset password page")
    public void verify_forgot_password_redirect() {
        login.verifyForgotPasswordRedirect();
    }
}
