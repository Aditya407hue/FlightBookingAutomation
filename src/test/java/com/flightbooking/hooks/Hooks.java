package com.flightbooking.hooks;

import com.flightbooking.utils.ConfigReader;
import com.flightbooking.utils.LoggerUtil;
import com.flightbooking.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.time.Duration; // Import Duration

public class Hooks {

    @Before
    public void setup() {
        LoggerUtil.info("Setting up WebDriver.");
        ConfigReader.loadProperties(); // Load properties before tests run
        WebDriverFactory.initializeDriver();
        WebDriver driver = WebDriverFactory.getDriver();
        if (driver != null) {
            // Implicit wait is already set in WebDriverFactory.initializeDriver()
            driver.manage().window().maximize();
        }
    }

    @After
    public void teardown(Scenario scenario) {
        LoggerUtil.info("Tearing down WebDriver.");
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
                LoggerUtil.error("Screenshot captured for failed scenario: " + scenario.getName());
            } catch (Exception e) {
                LoggerUtil.error("Failed to capture screenshot: " + e.getMessage());
            }
        }
        WebDriverFactory.quitDriver();
    }
}