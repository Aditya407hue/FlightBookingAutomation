package com.flightbooking.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = {"com.flightbooking.stepDefinitions", "com.flightbooking.hooks"}, // Package where step definitions and hooks are
        plugin = {"pretty",
                "html:target/cucumber-reports/CucumberTestReport.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "junit:target/cucumber-reports/CucumberTestReport.xml"} // Reporting plugins
)

public class TestRunner extends AbstractTestNGCucumberTests {
}