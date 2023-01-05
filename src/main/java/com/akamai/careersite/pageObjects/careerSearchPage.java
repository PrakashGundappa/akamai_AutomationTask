package com.akamai.careersite.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.akamai.careersite.base.base;

public class careerSearchPage extends base {

	By findYourCareerInput = By.id("keywordLocation");
	By searchButton = By.xpath("//button[@title='Search']");
	By filterByCountry = By.id("location");
	By filterByCountryDropDown = By.xpath("//ul[@id='location_list']/li");
	By searchResultList = By.xpath("//div[@class='row no-gutters']//div[@aria-labelledby='header-titler']/a");
	By offerBanner = By.xpath("//*[@controlid='location']//ancestor::div//div[contains(@class,'text-muted')]/span");

	public WebElement findYourCareerInput() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findYourCareerInput));
		return driver.findElement(findYourCareerInput);
	}

	public WebElement searchButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
		return driver.findElement(searchButton);
	}

	public WebElement filterByCountry() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(filterByCountry));
		return driver.findElement(filterByCountry);
	}

	public WebElement filterByCountryDropDown() throws Throwable {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(filterByCountryDropDown));
		return driver.findElement(filterByCountryDropDown);
	}

	public List<WebElement> searchResultList() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultList));
		return driver.findElements(searchResultList);
	}

	public WebElement offerBanner() {
		return driver.findElement(offerBanner);
	}

}
