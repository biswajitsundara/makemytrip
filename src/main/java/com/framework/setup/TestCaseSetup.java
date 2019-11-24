package com.framework.setup;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class TestCaseSetup {
	
	public static WebDriver driver;
	public static String chromeDriverPath;
	public static String dataPath;
	public static Properties prop;
	public static String baseUrl;
	
	@BeforeSuite
	public void readConfig()
	{
		prop= new Properties();
		try
		{
			prop.load(new FileInputStream("./config/Config.properties"));
			baseUrl=prop.getProperty("AppURL");
			dataPath=prop.getProperty("DataPath");
			chromeDriverPath=prop.getProperty("ChromeDriverPath");			
		}
		catch(Exception e)
		{
			System.err.println("Error in reading config file"+e.getMessage());
		}
	}
	
	@BeforeClass
	public void setDriver()
	{
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver= new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}
	
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
}
