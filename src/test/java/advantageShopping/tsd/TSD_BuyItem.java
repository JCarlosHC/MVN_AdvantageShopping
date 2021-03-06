package advantageShopping.tsd;

import java.io.IOException;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import advantageShopping.pages.Page_Navbar;
import advantageShopping.pages.Page_OurProduct;
import advantageShopping.pages.Page_ProductDetails;
import advantageShopping.pages.Page_ShoppingCart;
import advantageShopping.pages.Page_Signin;
import advantageShopping.support.ExcelPropertyLoader;
import advantageShopping.support.Keywords;

/**
 * Copyright: Softtek. 
 * Description: Test suit for buying items
 * @author David Morales<davidf.morales>
 *
 */

public class TSD_BuyItem {

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

	@Test(dataProvider = "excel-data", description = "Buying the special item")
	public void buySpecialItem(String username, String password) {
		try {
			Page_OurProduct page = new Page_OurProduct(driver);
			Page_ProductDetails productDetails = new Page_ProductDetails(driver);
			Page_ShoppingCart shoppingCart = new Page_ShoppingCart(driver);
			Page_Navbar navbar = new Page_Navbar(driver);
			driver.get(Page_Navbar.URL);
			navbar.goToProfileUser();
			Page_Signin signin = new Page_Signin(driver);
			Boolean valueExpected = true;
			Boolean signedinOK = signin.signInWithUserPassword(username, password, false);
			if(signedinOK) {
				Assert.assertEquals(signedinOK, valueExpected);
			}else {
				Assert.fail("Could not sign in");
			}
			
			if (page.goToSpecial()) {				
				Keywords.waitForLoadPage(driver, By.xpath(Page_ProductDetails.xpathAddToCartButton));
				Assert.assertTrue(true, "ok!");
				
			}else{
				Assert.fail("ok!");
			}
			
			//Product details
			Boolean actualResult = productDetails.changeColor("YELLOW");
			Assert.assertEquals(actualResult, valueExpected);
			actualResult =  productDetails.changeQuantity("6");
			Assert.assertEquals(actualResult, valueExpected);
			actualResult = productDetails.addToCart();
			Assert.assertEquals(actualResult, valueExpected);
			navbar.goToShoppingCart();
			actualResult = shoppingCart.checkOut();
			if(actualResult) {
				navbar.signOut();
			}
			Assert.assertEquals(actualResult, valueExpected);
			
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
