package com.akamai.careersite.listeners;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.akamai.careersite.base.base;
import com.akamai.careersite.pageObjects.careerSearchPage;
import com.akamai.careersite.reports.extentReport;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.HookTestStep;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunStarted;
import io.cucumber.plugin.event.TestSourceRead;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;

public class hooks extends base implements ConcurrentEventListener {

	Map<String, ExtentTest> feature = new HashMap<String, ExtentTest>();

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(TestRunStarted.class, testRunStart);
		publisher.registerHandlerFor(TestSourceRead.class, testSource);
		publisher.registerHandlerFor(TestCaseStarted.class, testcase);
		publisher.registerHandlerFor(TestStepStarted.class, stepHandler);
		publisher.registerHandlerFor(TestStepFinished.class, stepFinish);
	}

	

	EventHandler<TestRunStarted> testRunStart = new EventHandler<TestRunStarted>() {
		@Override
		public void receive(TestRunStarted event) {
			runStarted(event);
		}
	};

	private void runStarted(TestRunStarted event) {
		FileInputStream constfile = null;
		Properties objBaseProp = null;
		try {
			constfile = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//base.properties");
			objBaseProp = new Properties(System.getProperties());
			objBaseProp.load(constfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		testURL = objBaseProp.getProperty("testURL");
		headless = objBaseProp.getProperty("headless");
		browserName = objBaseProp.getProperty("browser");
		serachPageObj = new careerSearchPage();
		extentReport.SetUp();

	};

	EventHandler<TestSourceRead> testSource = new EventHandler<TestSourceRead>() {
		@Override
		public void receive(TestSourceRead event) {
			featureRead(event);
		}
	};

	private void featureRead(TestSourceRead event) {
		String featureSource = event.getUri().toString();
		featureName = featureSource.split(".*/")[1];
		if (feature.get(featureSource) == null)
			feature.putIfAbsent(featureSource, extent.createTest(featureName.toString()));
	};

	EventHandler<TestCaseStarted> testcase = new EventHandler<TestCaseStarted>() {
		@Override
		public void receive(TestCaseStarted event) {
			ScenarioStarted(event);
		}
	};

	private void ScenarioStarted(TestCaseStarted event) {
		String featureName = event.getTestCase().getUri().toString();
		Method = feature.get(featureName).createNode(event.getTestCase().getName());
	};

	public EventHandler<TestStepStarted> stepHandler = new EventHandler<TestStepStarted>() {
		@Override
		public void receive(TestStepStarted event) {
			stepStarted(event);
		}
	};

	private void stepStarted(TestStepStarted event) {
		String keyword = "";
		if (event.getTestStep() instanceof PickleStepTestStep) {
			PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
			stepName = steps.getStep().getText();
			keyword = steps.getStep().getKeyword();
		} else {
			HookTestStep hoo = (HookTestStep) event.getTestStep();
			stepName = hoo.getHookType().name();
		}
		logger = Method.createNode(Given.class, keyword + " " + stepName);

	};
	EventHandler<TestStepFinished> stepFinish = new EventHandler<TestStepFinished>() {
		@Override
		public void receive(TestStepFinished event) {
			handleTestStepFisnished(event);
		}
	};

	private void handleTestStepFisnished(TestStepFinished event) {
		Result stepResults = event.getResult();
		if (event.getTestStep() instanceof PickleStepTestStep) {
			Status stepStatus = stepResults.getStatus();

			if (stepStatus.toString().equals("FAILED")) {
				log.error("Failed :" + stepName);
				log.error("While handling '" + stepName + "' method -> {}", stepResults.getError());
				logger.assignCategory(stepName);
				logger.log(com.aventstack.extentreports.Status.FAIL,
						MarkupHelper.createLabel(stepName + " method Failed becoz of below reason", ExtentColor.RED));
				extentReport.ExtentReportScreenshot(logger, driver, stepName);

			} else if (stepStatus.toString().equals("PASSED")) {
				log.info("Passed :" + stepName);
				logger.assignCategory(stepName);
				logger.log(com.aventstack.extentreports.Status.PASS,
						MarkupHelper.createLabel(stepName, ExtentColor.GREEN));

			} else if (stepStatus.toString().equals("SKIPPED")) {
				log.info("Skipped :" + stepName);
				logger.assignCategory(stepName);
				logger.log(com.aventstack.extentreports.Status.SKIP,
						MarkupHelper.createLabel(stepName, ExtentColor.ORANGE));
			} else {
				log.warn("Warning :" + stepName);
				logger.assignCategory(stepName);
				logger.log(com.aventstack.extentreports.Status.WARNING,
						MarkupHelper.createLabel(stepName, ExtentColor.AMBER));
			}
		}
		extentReport.flushReport();
	}

}
