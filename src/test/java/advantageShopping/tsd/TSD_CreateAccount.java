package advantageShopping.tsd;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import advantageShopping.pages.Page_CreateAccount;
import advantageShopping.pages.Page_Navbar;
import advantageShopping.pages.Page_Signin;
import advantageShopping.support.ExcelPropertyLoader;

/**
 * Copyright: Softtek. Description: In this file contains a basic test case of
 * demosite application especifally how to create an account.
 * 
 * @author Juan Carlos Hernández Castro<jc.hernandez>
 *
 */

public class TSD_CreateAccount {

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
		if (Objects.equals(null, dataProviderObject)) {
			return new Object[][] { new Object[] { "jc", "carlos@test.com", "demo123A", "demo123A", "Test",
					"Test", "12345678", "MX", "CDMX", "Street SN", "Iztacalco", "11000" }, };
		}
		return dataProviderObject;
	}

	@Test(dataProvider = "excel-data", description = "Create a new account")
	public void createNewAccount(String username, String email, String password, String confirmPassword,
			String firstName, String lastName, String phoneNumber, String country, String city, String address,
			String state, String postalCode) {
		try {
			Page_CreateAccount newAccount = new Page_CreateAccount(driver);
			driver.get(Page_CreateAccount.URL);
			Boolean valueExpected = true;
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			username = username + timestamp.getTime();
			Boolean resp = newAccount.createAccount(username.subSequence(0, 14).toString(), email, password, confirmPassword, firstName, lastName,
					phoneNumber, country, city, address, state, postalCode);
			Assert.assertEquals(resp, valueExpected);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(dataProvider = "excel-data", description = "Create a new account from sigin")
	public void createNewAccountFromSignin(String username, String email, String password, String confirmPassword,
			String firstName, String lastName, String phoneNumber, String country, String city, String address,
			String state, String postalCode) {
		try {
			Page_Navbar navbar = new Page_Navbar(driver);
			driver.get(Page_Navbar.URL);
			navbar.goToProfileUser();
			Page_Signin signin = new Page_Signin(driver);
			signin.goToCreateAccount();
			Page_CreateAccount newAccount = new Page_CreateAccount(driver);
			Boolean valueExpected = true;
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			username = username + timestamp.getTime();
			Boolean resp = newAccount.createAccount(username.subSequence(0, 14).toString(), email, password, confirmPassword, firstName, lastName,
					phoneNumber, country, city, address, state, postalCode);
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
		sheetData = excelData.getValue("sheetDataForAccount");
		dataProviderObject = excelData.getExcelData(dataPath, sheetData, 12);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
