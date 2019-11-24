package com.mmt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{
	
	private static final By fromBox= By.xpath("//label[@for='fromCity']/parent::div");
	private static final By fromInputBox= By.xpath("//input[@placeholder='From']");
	private static final By toBox= By.xpath("//label[@for='toCity']/parent::div");
	private static final By toInputBox= By.xpath("//input[@placeholder='To']");
	private static String ddValue = "(//li[@role='option']/descendant::p[contains(text(),%s)])/parent::div";
	
	private static final By depBox= By.xpath("//label[@for='departure']/parent::div");
	private static final By retBox= By.xpath("//div[@data-cy='returnArea']/parent::div");
	private static String calDate="//div[contains(@class,'DayPicker') and contains(@aria-label,'%s')]";
	private static final By btnSearch= By.xpath("//a[text()='Search']");
	
	public HomePage(WebDriver driver)
	{
			super(driver);
	}
	
	public void selectFromPlace(String frmPlaceName)
	{
		clickElement(fromBox);
		setText(fromInputBox,frmPlaceName);
		clickElement(setLocator(ddValue,frmPlaceName));
	}
	
	public void selectToPlace(String toPlaceName)
	{
		clickElement(toBox);
		setText(toInputBox,toPlaceName);
		clickElement(setLocator(ddValue,toPlaceName));
	}
	
	public void selectDepartureDate(String depDate)
	{
		clickElement(depBox);
		clickElement(setLocator(calDate,depDate));
	}
	
	public void selectReturnDate(String retDate)
	{
		clickElement(retBox);
		clickElement(setLocator(calDate,retDate));
	}
	
	public void clickSearch()
	{
		clickElement(btnSearch);
	}
}
