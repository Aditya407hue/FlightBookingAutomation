package com.flightbooking.pages;

import com.flightbooking.utils.ConfigReader;
import com.flightbooking.utils.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List; // Import List

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By errorMessage = By.id("errorMessage"); // Assuming an ID for error messages on login page

    public LoginPage() {
        this.driver = WebDriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("timeout"))));
    }

    public void navigateToLoginPage(String url) {
        driver.get(url);
    }

    public void enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        element.clear(); // Clear field before sending keys
        element.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.clear(); // Clear field before sending keys
        element.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getErrorMessage() {
        // Check for the error message element's visibility
        List<WebElement> errorElements = driver.findElements(errorMessage);
        if (!errorElements.isEmpty() && errorElements.get(0).isDisplayed()) {
            return errorElements.get(0).getText();
        }
        // If specific error message element is not found, try to find generic error messages
        // This is a common strategy when specific IDs/classes for error messages aren't consistent
        List<WebElement> genericErrorElements = driver.findElements(By.xpath("//*[contains(@class, 'error-message') or contains(@class, 'alert-danger')]"));
        if (!genericErrorElements.isEmpty() && genericErrorElements.get(0).isDisplayed()) {
            return genericErrorElements.get(0).getText();
        }
        return null; // No error message found
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void enterCaptcha() {
        WebElement captchaTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("code")));
        String captchaText = captchaTextElement.getText();

        // Enter captcha text into input box
        WebElement captchaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("captcha")));
        captchaInput.clear();
        captchaInput.sendKeys(captchaText);

        // Click the validate button
        WebElement validateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("captchaBtn")));
        validateButton.click();
    }
    public void enterCaptcha(String captchaText) {
//        WebElement captchaTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("code")));
//        String captchaText = captchaTextElement.getText();

        // Enter captcha text into input box
        WebElement captchaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("captcha")));
        captchaInput.clear();
        captchaInput.sendKeys(captchaText);

        // Click the validate button
        WebElement validateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("captchaBtn")));
        validateButton.click();
    }
}
