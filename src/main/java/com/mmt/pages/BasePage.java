package com.mmt.pages;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private static WebDriver driver;
	private static WebDriverWait wait;

	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		wait= new WebDriverWait(driver,30);
	}


	public static void waitForElement(By locPath)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(locPath));
			wait.until(ExpectedConditions.elementToBeClickable(locPath));	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

	public static WebElement getElement(By locPath)
	{
		return driver.findElement(locPath);
	}

	public static void clickElement(By locPath)
	{
		waitForElement(locPath);
		getElement(locPath).click();
	}

	public static void setText(By locPath, String text)
	{
		waitForElement(locPath);
		getElement(locPath).sendKeys(text);
	}

	public static By setLocator(String path, String text)
	{
		String loc= String.format(path, text);
		By locPath= By.xpath(loc);
		return locPath;
	}

	public static boolean urlContains(String text)
	{
		wait.until(ExpectedConditions.urlContains(text));
		return driver.getCurrentUrl().contains(text);
	}


	public List<Integer> getMinValue(By locPath1, By locPath2)
	{
		List<WebElement> depEles = driver.findElements(locPath1);
		List<String> depval = new ArrayList<String>();
		String val;
		for(WebElement ele: depEles)
		{
			val=ele.getText().replaceAll("[^a-zA-Z0-9]", "");
			depval.add(val);
		}

		List<WebElement> arrEles = driver.findElements(locPath2);
		List<String> arrval = new ArrayList<String>();
		String val1;
		for(WebElement ele: arrEles)
		{
			val1=ele.getText().replaceAll("[^a-zA-Z0-9]", "");
			arrval.add(val1);
		}

		depval.removeAll(arrval);

		Collections.sort(depval);

		int minDepVal=Integer.parseInt(depval.get(0));

		Collections.sort(arrval);
		int minArrVal=Integer.parseInt(arrval.get(0));

		List<Integer> minval= new ArrayList<Integer>();
		minval.add(minDepVal);
		minval.add(minArrVal);

		return minval;
	}


	public void takeScreenShot(String filename)
	{
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String filePath="./snaps"+File.separator+filename+".png";
		try 
		{
			FileUtils.copyFile(src, new File(filePath));
		}
		catch(Exception e)
		{

		}
	}
}
