package com.flightbooking.hooks;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.flightbooking.utils.WebDriverFactory;

public class Hooks {

    WebDriver driver;
    Properties p;

    @Before
    public void setup() throws IOException
    {
        driver=WebDriverFactory.initilizeBrowser();

        p=WebDriverFactory.getProperties();
        driver.get(p.getProperty("baseUrl"));
        System.out.println("Browser opened with URL: " + p.getProperty("baseUrl"));
        driver.manage().window().maximize();

    }


    @After
    public void tearDown() {
        driver.quit();
    }


    @AfterStep
    public void addScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
            } catch (Exception e) {
                System.err.println("Screenshot capture failed: " + e.getMessage());
            }
        }
    }
}
