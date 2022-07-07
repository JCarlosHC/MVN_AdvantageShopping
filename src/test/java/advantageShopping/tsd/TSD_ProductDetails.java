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
import advantageShopping.pages.Page_ProductDetails;
import advantageShopping.support.ExcelPropertyLoader;

public class TSD_ProductDetails {

	public String baseUrl = "";
	String driverPath = "C:\\Academia2206\\libs\\webdriver\\chromedriver.exe";
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
			return new Object[][] { new Object[] { "jc", "carlos@test.com", "demo123A", "demo123A", "Test",
					"Test", "12345678", "MX", "CDMX", "Street SN", "Iztacalco", "11000" }, };
		}
		return dataProviderObject;
	}

	@Test(description = "Change the color and quantity from a product")
	public void changeColorandQuantity () {
		try {
			Page_ProductDetails productDetails = new Page_ProductDetails(driver);
			driver.get(Page_ProductDetails.URL);
			Boolean valueExpected = true;
			Boolean actualResult = productDetails.changeColor("YELLOW");
			Assert.assertEquals(actualResult, valueExpected);
			actualResult =  productDetails.changeQuantity("6");
			Assert.assertEquals(actualResult, valueExpected);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(description = "Add a product to the cart")
	public void addToCart() {
		try {
			Page_ProductDetails productDetails = new Page_ProductDetails(driver);
			driver.get(Page_ProductDetails.URL);
			Boolean valueExpected = true;
			Boolean actualResult = productDetails.addToCart();
			Assert.assertEquals(actualResult, valueExpected);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(description="Verify if the specifications of the product are shown")
	public void specificationsAreShown() {
		try {
			Page_ProductDetails productDetails = new Page_ProductDetails(driver);
			driver.get(Page_ProductDetails.URL);
			Boolean valueExpected = true;
			Boolean actualResult = productDetails.getSpecifications();
			Assert.assertEquals(actualResult, valueExpected);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(description="Verifiy if the prize and descrpition are shown")
	public void prizeAndDescriptionAreShown() {
		Page_ProductDetails productDetails = new Page_ProductDetails(driver);
		driver.get(Page_ProductDetails.URL);
		Boolean valueExpected = true;
		Boolean actualResult = productDetails.getPrize();
		Assert.assertEquals(actualResult, valueExpected);
		actualResult = productDetails.getDescription();
		Assert.assertEquals(actualResult, valueExpected);
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
