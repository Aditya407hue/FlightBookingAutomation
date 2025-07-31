package com.flightbooking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;


public class FlightEnquiryPage {

    WebDriver driver;

    public FlightEnquiryPage(WebDriver driver) {
        this.driver = driver;
    }
    private By emailField = By.id("email");
    private By nameField = By.id("name");
    private By phoneField = By.id("phone");
    private By subjectField = By.id("subject");
    private By messageField = By.id("message");

    private By sendButton = By.id("submit");
    private By errorMessage = By.id("emailError");
    private By successMsg = By.id("success-msg");
    private By errorMsgName = By.id("nameError");
    private By errorMsgPhone = By.id("phoneError");

    // Methods
    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }
    public void enterPhone(String phone) {
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phone);
    }
    public void enterSubject(String sub) {
        driver.findElement(subjectField).clear();
        driver.findElement(subjectField).sendKeys(sub);
    }


    public void enterMsg(String msg) {
        driver.findElement(messageField).clear();
        driver.findElement(messageField).sendKeys(msg);
    }

    public boolean getErrMsgName() {
        boolean exists = !driver.findElements(errorMsgName).isEmpty();
        boolean visible = exists && driver.findElement(errorMsgName).isDisplayed();  // ✅ use correct locator
        System.out.println("Name error element exists: " + exists + ", visible: " + visible);
        return visible;
    }



    public boolean getErrMsgPhone() {
        boolean exists = driver.findElements(errorMsgPhone).size() > 0;
        boolean visible = exists && driver.findElement(errorMsgPhone).isDisplayed();
        System.out.println("Phone error element exists: " + exists + ", visible: " + visible);
        return visible;
    }



    public void clickSend() throws InterruptedException{
        Thread.sleep(3000);
        driver.findElement(sendButton).click();
    }

    public boolean isErrorMessageDisplayed() {
        boolean exists = !driver.findElements(errorMessage).isEmpty();
        boolean visible = exists && driver.findElement(errorMessage).isDisplayed();
        System.out.println("Error element exists: " + exists + ", visible: " + visible);
        return visible;
    }

    public boolean isSuccessMessageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofMillis(50));
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
}
