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
import advantageShopping.pages.Page_Signin;
import advantageShopping.support.ExcelPropertyLoader;

/**
 * Copyright: Softtek. Description: In this file contains the test cases for
 * signin
 * 
 * @author Juan Carlos Hernández Castro<jc.hernandez>
 *
 */

public class TSD_SignIn {

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
			return new Object[][] { new Object[] { "demo123A", "Demo123A" }, };
		}
		return dataProviderObject;
	}

	@Test(dataProvider = "excel-data", description = "Sigin with username, password and remember credentials")
	public void signInWithUserPasswordAndRemember(String username, String password) {
		try {
			Page_Navbar navbar = new Page_Navbar(driver);
			driver.get(Page_Navbar.URL);
			navbar.goToProfileUser();
			Page_Signin signin = new Page_Signin(driver);
			Boolean valueExpected = true;
			Boolean resp = signin.signInWithUserPassword(username, password, true);
			Assert.assertEquals(resp, valueExpected);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(dataProvider = "excel-data", description = "Sigin with username, password and without remember credentials")
	public void signInWithUserPassword(String username, String password) {
		try {
			Page_Navbar navbar = new Page_Navbar(driver);
			driver.get(Page_Navbar.URL);
			navbar.goToProfileUser();
			Page_Signin signin = new Page_Signin(driver);
			Boolean valueExpected = true;
			Boolean resp = signin.signInWithUserPassword(username, password, false);
			if(resp) {
				navbar.signOut();
			}
			Assert.assertEquals(resp, valueExpected);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(description = "Sigin with Facebook")
	public void signInWithFacebook() {
		try {
			Page_Navbar navbar = new Page_Navbar(driver);
			driver.get(Page_Navbar.URL);
			navbar.goToProfileUser();
			Page_Signin signin = new Page_Signin(driver);
			Boolean valueExpected = true;
			Boolean resp = signin.signInWithFacebook();
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
		dataProviderObject = excelData.getExcelData(dataPath, sheetData, 2);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}