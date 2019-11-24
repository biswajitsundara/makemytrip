package com.mmt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;




public class SearchPage extends BasePage {

	private static final By depRates= By.xpath("//div[contains(@class,'splitVw-sctn pull-left')]/descendant::span[@class='actual-price']");
	private static final By arrRates= By.xpath("//div[contains(@class,'splitVw-sctn pull-right')]/descendant::span[@class='actual-price']");
	private static final By offer= By.xpath("//div[@class='banner-offers-wrapper make_relative roundedbox']");
	
	public SearchPage(WebDriver driver) 
	{
		super(driver);	
	}
	
	
	public void search_Page_Loaded()
	{
		waitForElement(offer);
		Assert.assertEquals(true, urlContains("flight/search"));
	}
	
	public void getMinDepartureFlightRates()
	{
		List<Integer> minValue = getMinValue(depRates,arrRates);
		System.out.println("Minimum Departure Rate: " +minValue.get(0));
	}
	
	
	public void getMinReturnFlightRates()
	{
		List<Integer> minValue = getMinValue(depRates,arrRates);
		System.out.println("Minimum Return Rate: " +minValue.get(1));
	}

}
