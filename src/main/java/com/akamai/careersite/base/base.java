package com.akamai.careersite.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.akamai.careersite.pageObjects.careerSearchPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class base {

	public static String testURL;
	public static String headless;
	public static String browserName;
	public static WebDriver driver;
	public static careerSearchPage serachPageObj;
	public static WebDriverWait wait;
	public static Logger log = LogManager.getLogger(base.class.getName());
	public static ExtentReports extent;
	public static ExtentTest Method;
	public static ExtentTest logger;
	public static String stepName;
	public String featureName;

}
