package com.mmt.testscripts;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mmt.pages.HomePage;
import com.mmt.pages.SearchPage;
import com.framework.setup.ExcelUtil;
import com.framework.setup.TestCaseSetup;

public class TC001_SearchFlight_ComparePrice extends TestCaseSetup
{

	@DataProvider(name="flightData")
	public Object[][] readTCData()
	{
		return ExcelUtil.getExcelData("MakeMyTrip", "FlightData");
	}
	
	
	@Test(dataProvider="flightData")
	public void SearchFlightPriceTest(String fromPlace, String toPlace, String depDate, String returnDate)
	{
		HomePage homepage= new HomePage(driver);
		homepage.selectFromPlace(fromPlace);
		homepage.selectToPlace(toPlace);
		homepage.selectDepartureDate(depDate);
		homepage.selectReturnDate(returnDate);
		homepage.takeScreenShot("homePage");
		homepage.clickSearch();
		
		SearchPage searchpage= new SearchPage(driver);
		searchpage.search_Page_Loaded();
		searchpage.getMinDepartureFlightRates();
		searchpage.getMinReturnFlightRates();
		searchpage.takeScreenShot("searchPage");
	}
}
