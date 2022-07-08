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

import advantageShopping.pages.Page_Navbar;
import advantageShopping.pages.Page_ShoppingCart;
import advantageShopping.pages.Page_Signin;
import advantageShopping.support.ExcelPropertyLoader;

public class TSD_ShoppingCart {
	
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
		
		//If excel data is empty we set an object for the test cases
		if (Objects.equals(null, dataProviderObject)) {
			return new Object[][] { new Object[] { "BOSE SOUNDLINK WIRELESS SPEAKER"}, };
		}
		return dataProviderObject;
	}
	
	@Test(dataProvider = "excel-data", description = "edits items in cart", priority = 1)
	public void editCartItems(String product) {
		try {			
			Page_Navbar navBar = new Page_Navbar(driver);
			driver.get(navBar.URL);
			navBar.goToProfileUser();
			Page_Signin signIn = new Page_Signin(driver);
			signIn.signInWithUserPassword("demo123A", "Demo123A", false);
			navBar.goToShoppingCart();			
			Page_ShoppingCart cart = new Page_ShoppingCart(driver);	
			boolean valueExpected = true;
			boolean resp = cart.editItems(product);			
			Assert.assertEquals(resp, valueExpected);
			
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(dataProvider = "excel-data", description = "delete items from cart",priority = 2)
	public void deleteCartItems(String product) {
		try {
			Page_Navbar navBar = new Page_Navbar(driver);
			driver.get(navBar.URL);
			navBar.goToProfileUser();
			Page_Signin signIn = new Page_Signin(driver);
			signIn.signInWithUserPassword("demo123A", "Demo123A", false);
			navBar.goToShoppingCart();
			Page_ShoppingCart cart = new Page_ShoppingCart(driver);
			boolean valueExpected = true;
			boolean resp = cart.deleteItems(product);			
			Assert.assertEquals(resp, valueExpected);		
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(description = "initiates check out process",priority = 3)
	public void checkOutItems() {
		try {
			Page_Navbar navBar = new Page_Navbar(driver);
			driver.get(navBar.URL);
			navBar.goToProfileUser();
			Page_Signin signIn = new Page_Signin(driver);
			signIn.signInWithUserPassword("demo123A", "Demo123A", false);
			navBar.goToShoppingCart();
			Page_ShoppingCart cart = new Page_ShoppingCart(driver);
			boolean valueExpected = true;
			boolean resp = cart.checkOut();			
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
		sheetData = excelData.getValue("sheetDataForItemUpdate");
		dataProviderObject = excelData.getExcelData(dataPath, sheetData, 1);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
