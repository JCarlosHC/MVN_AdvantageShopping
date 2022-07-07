package advantageShopping.tsd;

import static org.testng.Assert.assertEquals;

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
import advantageShopping.pages.Page_OurProduct;
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
	String driverPath = "C:\\Academia2022_2\\libs\\chromedriver.exe";
	String excelPath = "C:\\Academia2022_2\\libs\\advantageShoppingData\\advantageShopping_parameters.xlsx";
	String dataPath = "";
	String sheetData = "";
	public WebDriver driver;
	ExcelPropertyLoader excelData;
	Object[][] dataProviderObject = null;

	@DataProvider(name = "excel-data")
	public Object[][] excelDP() throws IOException {
		
		//If excel data is empty we set an object for the test cases
		if (Objects.equals(null, dataProviderObject)) {
			return new Object[][] { new Object[] { "jc", "carlos@test.com", "demo123A", "demo123A", "Test",
					"Test", "12345678", "MX", "CDMX", "Street SN", "Iztacalco", "11000" }, };
		}
		return dataProviderObject;
	}

	
	
	@Test(description = "Buying speakers test")
	public void buySpeakerItem() {
		try {
			Page_OurProduct page = new Page_OurProduct(driver);
			driver.get(Page_OurProduct.URL);
			if (page.goToSpeakers()) {
				Assert.assertTrue(true, "ok!");
			}else{
				Assert.fail("ok!");
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(description = "Buying the special item")
	public void buySpecialItem() {
		try {
			Page_OurProduct page = new Page_OurProduct(driver);
		
			if (page.goToSpecial()) {
				Assert.assertTrue(true, "ok!");
				Keywords.waitForLoadPage(driver, null);
			}else{
				Assert.fail("ok!");
			}
			
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@BeforeClass
	public void beforeClass() {
		// Load test data required
		excelData = new ExcelPropertyLoader();
		excelData.LoadFile(excelPath);
		System.out.println(excelPath);
		System.out.println(excelData==null);
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
