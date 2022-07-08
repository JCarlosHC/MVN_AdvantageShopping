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
import advantageShopping.pages.Page_Order;
import advantageShopping.pages.Page_ShoppingCart;
import advantageShopping.support.ExcelPropertyLoader;

public class TSD_Order {
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
			return new Object[][] { new Object[] { "Veronica","Jimenez","1234567890","calle #11","Chihuahua","31301", "Chihuahua"}, };
		}
		return dataProviderObject;
	}
	
	@Test(dataProvider = "excel-data", description = "fills shipment details at checkout")
	public void fillShipmentDetails(String firstName, String lastName, String phoneNumber, String address, String city, String postalCode, String state) {
		try {
			Page_Navbar navBar = new Page_Navbar(driver);
			driver.get(navBar.URL);
			navBar.goToShoppingCart();
			
			Page_ShoppingCart cart = new Page_ShoppingCart(driver);
			cart.checkOut();	
			
			Page_Order order = new Page_Order(driver);
			boolean valueExpected = true;
			boolean resp = order.fillShippingDetails(firstName, lastName, phoneNumber, address, city, postalCode, state);
			
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
		sheetData = excelData.getValue("shipmentOrderData");
		dataProviderObject = excelData.getExcelData(dataPath, sheetData, 7);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
