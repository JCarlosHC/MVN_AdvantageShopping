package advantageShopping.tsd;

import java.io.IOException;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import advantageShopping.pages.Page_Navbar;
import advantageShopping.support.ExcelPropertyLoader;

/**
 * Copyright: Softtek. Description: In this file contains the test cases for
 * Search item by name
 * 
 * @author Juan Carlos Hernández Castro<jc.hernandez>
 *
 */

public class TSD_SearchByName {

	public String baseUrl = "";
	String driverPath = "C:\\Academia2206\\libs\\webdrivers\\chromedriver-102.0.5.exe";
	String excelPath = "C:\\Academia2206\\libs\\advantageShopping_parameters.xlsx";
	String dataPath = "";
	String sheetData = "";
	public WebDriver driver;
	ExcelPropertyLoader excelData;
	Object[][] dataProviderObject = null;

	@DataProvider(name = "excel-data")
	public Object[][] excelDP() throws IOException {

		// If excel data is empty we set an object for the test cases
		if (Objects.equals(null, dataProviderObject)) {
			return new Object[][] { new Object[] { "HP ROAR MINI WIRELESS SPEAKER" },
					{ "BLACK HEADPHONES" },
					{ "HP ENVY" },};
		}
		return dataProviderObject;
	}

	@Test(dataProvider = "excel-data", description = "Search item by name")
	public void searchItemByName(String nameItem) {
		try {
			Page_Navbar navbar = new Page_Navbar(driver);
			driver.get(Page_Navbar.URL);
			Boolean valueExpected = true;
			Boolean resp = navbar.goToSearchAndSearchByName(nameItem);
			Assert.assertEquals(resp, valueExpected);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@BeforeClass
	public void beforeClass() {
		// Load test data required
		excelData = new ExcelPropertyLoader();
		excelData.LoadFile(excelPath);
		baseUrl = excelData.getValue("baseUrl");
		driverPath = excelData.getValue("driverPath");
		dataPath = excelData.getValue("dataPath");
		sheetData = excelData.getValue("sheetDataForSignin");
//		dataProviderObject = excelData.getExcelData(dataPath, sheetData, 2);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}