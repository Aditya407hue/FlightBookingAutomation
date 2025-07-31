package com.flightbooking.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = {"com.flightbooking.stepDefinitions", "com.flightbooking.hooks"}, // Package where step definitions and hooks are
        plugin = {"pretty", "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, // Reporting plugins
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true) // Enables parallel execution of scenarios
    public Object[][] scenarios() {
        return super.scenarios();
    }
}