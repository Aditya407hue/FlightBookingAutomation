package com.flightbooking.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration; // Import Duration

public class WebDriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver dr) {
        driver.set(dr);
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    public static void initializeDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        WebDriver currentDriver;

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                currentDriver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                currentDriver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                currentDriver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser " + browser + " is not supported.");
        }
        currentDriver.manage().window().maximize();
        currentDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("timeout"))));
        setDriver(currentDriver);
    }
}