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

import advantageShopping.pages.Page_ContactUs;
import advantageShopping.pages.Page_Navbar;
import advantageShopping.support.ExcelPropertyLoader;

public class TSD_Contact {
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
			return new Object[][] { new Object[] { "Headphones","Bose SoundLink Around-ear Wireless Headphones II","foo@foo.foo","nice headphones" }, };
		}
		return dataProviderObject;
	}
	
	@Test(dataProvider = "excel-data", description = "fills shipment details at checkout")
	public void fillContactUs(String category, String product, String email, String subject) {
		try {
			Page_Navbar navBar = new Page_Navbar(driver);
			driver.get(navBar.URL);
			navBar.goToContactUs();
			
			Page_ContactUs contact = new Page_ContactUs(driver);
			
			boolean valueExpected = true;
			boolean resp = contact.fillContactForm(category, product, email, subject);
			Assert.assertEquals(resp, valueExpected);
			
		}catch (Exception e) {
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
		sheetData = excelData.getValue("sheetForContactUsData");
		System.out.println("sheet data: "+sheetData);
		dataProviderObject = excelData.getExcelData(dataPath, sheetData, 4);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
