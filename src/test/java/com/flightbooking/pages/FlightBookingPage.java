package com.flightbooking.pages;

import com.flightbooking.utils.ConfigReader;
import com.flightbooking.utils.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select; // Import Select class
import java.time.Duration;
import java.util.List;

public class FlightBookingPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private final By travelFromField = By.id("travelFrom");
    private final By travelToField = By.id("travelTo");
    private By dateField = By.id("date");
    private By classDropdown = By.id("class"); // Assuming a <select> element
    private By nameField = By.id("name");
    private By emailField = By.id("email");
    private By phoneNumberField = By.id("phoneNumber");
    private By passengerCountField = By.id("passengerCount"); // Assuming input field
    private By passengerCountIncreaseButton = By.id("increaseCount");
    private By passengerCountDecreaseButton = By.id("decreaseCount");
    private By bookNowButton = By.xpath("//button[text()='Book Now']");
    private By confirmationMessage = By.id("confirmationMessage"); // Assuming an ID for confirmation
    private By genericErrorMessages = By.className("error-message"); // A common class for field-level errors

    // Locators for booking details table (after successful booking)
    private By bookingDetailsTable = By.id("bookingDetailsTable");
    private By subtotalElement = By.id("subtotal"); // Assuming IDs for these elements
    private By vatElement = By.id("vat");
    private By totalElement = By.id("total");


    public FlightBookingPage() {
        this.driver = WebDriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("timeout"))));
    }

    public void navigateToBookingPage(String url) {
        driver.get(url);
    }

    public void enterTravelFrom(String from) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(travelFromField));
        element.clear();
        element.sendKeys(from);
    }

    public void enterTravelTo(String to) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(travelToField));
        element.clear();
        element.sendKeys(to);
    }

    public void enterDate(String date) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dateField));
        element.clear();
        element.sendKeys(date);
    }

    public void selectClass(String flightClass) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(classDropdown)));
        select.selectByVisibleText(flightClass);
    }

    public void enterName(String name) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        element.clear();
        element.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        element.clear();
        element.sendKeys(email);
    }

    public void enterPhoneNumber(String phone) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberField));
        element.clear();
        element.sendKeys(phone);
    }

    public void enterPassengerCount(String count) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passengerCountField));
        element.clear();
        element.sendKeys(count);
    }

    public void increasePassengerCount() {
        wait.until(ExpectedConditions.elementToBeClickable(passengerCountIncreaseButton)).click();
    }

    public void decreasePassengerCount() {
        wait.until(ExpectedConditions.elementToBeClickable(passengerCountDecreaseButton)).click();
    }

    public void clickBookNow() {
        wait.until(ExpectedConditions.elementToBeClickable(bookNowButton)).click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).getText();
    }

    public String getFieldErrorMessage(String fieldName) {
        // This method needs to be robust enough to find error messages specific to a field.
        // This is a generic approach; you might need more specific locators if your app has them.
        List<WebElement> errors = driver.findElements(genericErrorMessages);
        for (WebElement error : errors) {
            // Assuming error message text contains the field name or is associated visually
            if (error.isDisplayed() && error.getText().toLowerCase().contains(fieldName.toLowerCase())) {
                return error.getText();
            }
        }
        // Fallback for general error messages, if any
        List<WebElement> generalErrors = driver.findElements(By.xpath("//*[contains(@class, 'alert') or contains(@class, 'error')]"));
        for (WebElement error : generalErrors) {
            if(error.isDisplayed()) {
                return error.getText();
            }
        }
        return null;
    }


    public String getDisplayedPassengerCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passengerCountField)).getAttribute("value");
    }

    public boolean isBookingTableDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(bookingDetailsTable)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public double getSubtotal() {
        return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalElement)).getText().replaceAll("[^\\d.]", ""));
    }

    public double getVat() {
        return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(vatElement)).getText().replaceAll("[^\\d.]", ""));
    }

    public double getTotal() {
        return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(totalElement)).getText().replaceAll("[^\\d.]", ""));
    }

    public void navigateToLoginPage(String replace) {
        // Assuming the login page URL is passed as a parameter
        String loginUrl = ConfigReader.getProperty("loginUrl").replace("{replace}", replace);
        driver.get(loginUrl);
    }
}