package com.akamai.careersite.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/java/com/akamai/careersite/features" }, glue = {
		"com.akamai.careersite" }, plugin = { "com.akamai.careersite.listeners.hooks" }, monochrome = true)

public class testRunner extends AbstractTestNGCucumberTests {

}
