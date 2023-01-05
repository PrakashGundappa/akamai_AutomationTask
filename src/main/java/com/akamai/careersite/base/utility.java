package com.akamai.careersite.base;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class utility extends base {

	public void InitializeDriver() throws IOException {

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (headless.equalsIgnoreCase("Yes"))
				options.addArguments("--headless");
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			if (headless.equalsIgnoreCase("Yes"))
				options.setHeadless(true);
			driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("ie")) {
			EdgeOptions edgeOptions = new EdgeOptions();
			if (headless.equalsIgnoreCase("Yes"))
				edgeOptions.addArguments("headless");
			driver = new EdgeDriver(edgeOptions);
		}
		log.info(browserName + " browser is used to run testcase");
		logger.log(Status.INFO, browserName + " browser is used to run testcase");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().deleteAllCookies();

	}

	public static void assertStepString(String Actual, String Expected) {
		log.info("Actual ->" + Actual + " , Expected ->" + Expected);
		logger.log(Status.INFO, "Actual ->" + Actual + " , Expected ->" + Expected);
		Assert.assertEquals(Actual, Expected);
	}

	public static void assertStepInt(int Actual, int Expected) {
		log.info("Actual ->" + Actual + " , Expected ->" + Expected);
		logger.log(Status.INFO, "Actual ->" + Actual + " , Expected ->" + Expected);
		Assert.assertEquals(Actual, Expected);
	}

	public static void assertNotStepInt(int Actual, int Expected) {
		log.info("Actual ->" + Actual + " , Expected ->" + Expected);
		logger.log(Status.INFO, "Actual ->" + Actual + " , Expected ->" + Expected);
		Assert.assertNotEquals(Actual, Expected);
	}

	public void launchURL() {
		log.info("Launch URL ->" + testURL);
		logger.log(Status.INFO, "Launch URL ->" + testURL);
		driver.get(testURL);
	}

	public void sendKeys(WebElement element, String inputValue) {
		log.info("Input Value ->" + inputValue);
		logger.log(Status.INFO, "Input Value ->" + inputValue);
		element.sendKeys(inputValue);
	}

	public void click(WebElement element) {
		log.info("User Did Click Operation");
		logger.log(Status.INFO, "User Did Click Operation");
		element.click();
	}

	public void enterKeyword(WebElement element) {
		log.info("ENTER Keyword Used");
		logger.log(Status.INFO, "ENTER Keyword Used");
		element.sendKeys(Keys.RETURN);
	}

	public List<String> getTexts(List<WebElement> element) {
		List<String> textValues = new ArrayList<String>();
		for (WebElement ele : element)
			textValues.add(ele.getText());

		log.info("Retrieved Text values are->" + textValues);
		logger.log(Status.INFO, "Retrieved Text values are->" + textValues);
		return textValues;
	}

	public String getText(WebElement element) {
		log.info("Retrieved Text value ->" + element.getText());
		logger.log(Status.INFO, "Retrieved Text value ->" + element.getText());
		return element.getText();
	}

	public int getInt(List<WebElement> element) {
		log.info("Element Size value ->" + element.size());
		logger.log(Status.INFO, "Element Size value ->" + element.size());
		return element.size();
	}

	public void driverQuit() {
		log.info("Driver Quit");
		logger.log(Status.INFO, "Driver Quit");
		driver.quit();
	}

}
