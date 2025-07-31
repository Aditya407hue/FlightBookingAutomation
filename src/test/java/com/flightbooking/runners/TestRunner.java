package com.flightbooking.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.flightbooking.stepDefinitions", "com.flightbooking.hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/html/cucumber-html-report.html",
                "json:target/cucumber-reports/json/cucumber.json",
               },
        monochrome = true

)
public class TestRunner extends AbstractTestNGCucumberTests {

}