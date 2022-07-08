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

import advantageShopping.pages.Page_AccountDetails;
import advantageShopping.pages.Page_MyAccount;
import advantageShopping.pages.Page_Navbar;
import advantageShopping.pages.Page_Signin;
import advantageShopping.support.ExcelPropertyLoader;

public class TSD_AccountDetails {
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
	
	@Test(dataProvider = "excel-data", description = "Account", priority = 1)
	public void editAccountData(String firstName, String lastName, String phone, String city, String address, String zip, String state) {
		try {
			Page_Navbar navBar = new Page_Navbar(driver);
			driver.get(navBar.URL);
			navBar.goToProfileUser();
			Page_Signin signIn = new Page_Signin(driver);
			signIn.signInWithUserPassword("demo123A", "Demo123A", false);
			navBar.goToProfileUser();
			navBar.goToMyAccount();
			Page_MyAccount myAcc= new Page_MyAccount(driver);
			Page_AccountDetails details = new Page_AccountDetails(driver);
			myAcc.goToEditAccountDetails();
			boolean valueExpected = true;
			boolean resp = details.editAccountInformation(firstName, lastName, phone, city, address, zip, state);					
			Assert.assertEquals(resp, valueExpected);
		}catch(Exception e){
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
		sheetData = excelData.getValue("sheetDataForOrder");
		dataProviderObject = excelData.getExcelData(dataPath, sheetData, 7);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
