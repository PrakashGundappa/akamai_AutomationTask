package com.akamai.careersite.stepDefinitions;

import com.akamai.careersite.base.utility;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class akamaiSiteSearchFeature extends utility {

	@Given("^Customer is on akamai career portal$")
	public void customer_is_on_akamai_career_portal() throws Throwable {
		InitializeDriver();
		launchURL();
		utility.assertStepString(driver.getTitle(), "Akamai");

	}

	@When("^Customer specifies \"([^\"]*)\"$")
	public void customer_specifies_something(String careerInput) throws Throwable {
		sendKeys(serachPageObj.findYourCareerInput(), careerInput);
		enterKeyword(serachPageObj.findYourCareerInput());
	}

	@And("^Filter by \"([^\"]*)\"$")
	public void filter_by_something(String countryInput) throws Throwable {
		sendKeys(serachPageObj.filterByCountry(), countryInput);
		click(serachPageObj.filterByCountryDropDown());

	}

	@Then("^Any job offers are found$")
	public void any_job_offers_are_found() throws Throwable {
		assertNotStepInt(getInt(serachPageObj.searchResultList()), 0);
		getTexts(serachPageObj.searchResultList());
		driverQuit();
	}

	@Then("^Notification about \"([^\"]*)\" found is displayed$")
	public void notification_about_something_found_is_displayed(String bannerMsg) throws Throwable {
		assertStepString(getText(serachPageObj.offerBanner()), bannerMsg);
		driverQuit();
	}

}
