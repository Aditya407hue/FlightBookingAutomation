package com.flightbooking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

public class EnquiryPage {

    WebDriver driver;

    public EnquiryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By nameField = By.id("name");
    By emailField = By.id("email");
    By phoneField = By.id("phone");
    By subjectField = By.id("subject");
    By messageField = By.id("message");
    By submitButton = By.id("submit");

    By nameError = By.id("nameError");
    By emailError = By.id("emailError");
    By phoneError = By.id("phoneError");
    By successMsg = By.id("success-msg");

    // Field actions
    public void enterName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void enterSubject(String subject) {
        driver.findElement(subjectField).clear();
        driver.findElement(subjectField).sendKeys(subject);
    }

    public void enterMessage(String message) {
        driver.findElement(messageField).clear();
        driver.findElement(messageField).sendKeys(message);
    }

    public void clickSubmit() throws InterruptedException {
        Thread.sleep(2000); // For stability before clicking
        driver.findElement(submitButton).click();
    }

    // Error message checks
    public boolean isNameErrorVisible() {
        return isElementVisible(nameError);
    }

    public boolean isEmailErrorVisible() {
        return isElementVisible(emailError);
    }

    public boolean isPhoneErrorVisible() {
        return isElementVisible(phoneError);
    }

    // Success message
    public boolean isSuccessMessageVisible() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
        return success.isDisplayed();
    }

    public String getSuccessMessageText() {
        try {
            return driver.findElement(successMsg).getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    // Reusable visibility check
    private boolean isElementVisible(By locator) {
        boolean exists = driver.findElements(locator).size() > 0;
        return exists && driver.findElement(locator).isDisplayed();
    }
}
