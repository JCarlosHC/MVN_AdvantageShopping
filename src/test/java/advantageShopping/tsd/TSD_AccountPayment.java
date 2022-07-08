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

import advantageShopping.pages.Page_AccountPayment;
import advantageShopping.pages.Page_MyAccount;
import advantageShopping.pages.Page_Navbar;
import advantageShopping.pages.Page_Signin;
import advantageShopping.support.ExcelPropertyLoader;

public class TSD_AccountPayment {
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
//			return new Object[][] { new Object[] { "User","Pass"}, };
			return new Object[][] { new Object[] { "1002687623563090",	"333","02",	"2044","Jasmin Dolores"
}, };
		}
		return dataProviderObject;
	}
	
	@Test(dataProvider = "excel-data", description = "test case to change payment data of user")
	public void fillSafePay(String password, String username) {
		try {
			Page_Navbar navBar = new Page_Navbar(driver);
			driver.get(navBar.URL);
			navBar.goToProfileUser();
			Page_Signin signIn = new Page_Signin(driver);
			signIn.signInWithUserPassword("demo123A", "Demo123A", false);
			navBar.goToProfileUser();
			navBar.goToMyAccount();
			Page_MyAccount myAcc= new Page_MyAccount(driver);
			myAcc.goToEditPaymentMethod();
			Page_AccountPayment pay = new Page_AccountPayment(driver);
			pay.saveSafePayMethod(username,password);
			
		}catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(dataProvider = "excel-data", description = "test case to change payment data os master credit")
	public void fillMasterCredit(String cardNumber,String CVVNumber, String month, String year,String name) {
		try {
			Page_Navbar navBar = new Page_Navbar(driver);
			driver.get(navBar.URL);
			navBar.goToProfileUser();
			Page_Signin signIn = new Page_Signin(driver);
			signIn.signInWithUserPassword("demo123A", "Demo123A", false);
			navBar.goToProfileUser();
			navBar.goToMyAccount();
			Page_MyAccount myAcc= new Page_MyAccount(driver);
			myAcc.goToEditPaymentMethod();
			Page_AccountPayment pay = new Page_AccountPayment(driver);
			pay.saveMasterCreditMethod(cardNumber, CVVNumber, month, year, name);
			
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
//		sheetData = excelData.getValue("sheetDataForSafePay");
		sheetData = excelData.getValue("sheetDataForPaymentCredit");
		System.out.println("sheet data: "+sheetData);
//		dataProviderObject = excelData.getExcelData(dataPath, sheetData, 2);
		dataProviderObject = excelData.getExcelData(dataPath, sheetData, 5);
		
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
//		driver.close();
	}
	
}
