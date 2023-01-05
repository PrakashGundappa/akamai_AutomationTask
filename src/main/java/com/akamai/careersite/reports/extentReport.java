package com.akamai.careersite.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.akamai.careersite.base.utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReport extends utility {

	public static ExtentSparkReporter reporter;

	public static void SetUp() {
		if (extent == null) {
			extent = new ExtentReports();
			Date d = new Date();
			SimpleDateFormat date = new SimpleDateFormat("ddMyyyy_hhmmss");
			reporter = new ExtentSparkReporter(
					System.getProperty("user.dir") + "//Reports//Akamai_CareerSite_Result" + date.format(d) + ".html");

			reporter.config().setTheme(Theme.STANDARD);
			reporter.config().setDocumentTitle("Akamai CareerSite Application");
			reporter.config().setEncoding("utf-8");
			reporter.config().setReportName("Automation Test Report");
			extent.attachReporter(reporter);

			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Browser Name", browserName);
			extent.setSystemInfo("Test URL", testURL);
		}
	}

	public static void ExtentReportScreenshot(ExtentTest test, WebDriver driver, String errorMsg) {
		try {
			String dateName = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);

			String destination = System.getProperty("user.dir") + "//screenshots//PageScreenshot_" + dateName + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);

			File file = new File(destination);
			byte[] fileContent = FileUtils.readFileToByteArray(file);
			String image = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
			test.addScreenCaptureFromBase64String(image, errorMsg);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(Status.FAIL, "Not able to take a screenshot:---> " + e.getMessage());
		}
	}

	public static void flushReport() {
		extent.flush();
	}
}
