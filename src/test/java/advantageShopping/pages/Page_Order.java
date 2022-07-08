package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

public class Page_Order extends PageTemplate {

public static final String URL = "https://advantageonlineshopping.com/#/orderPayment";
	

	public Page_Order(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Method to fill shipping details
	 */
	public boolean fillShippingDetails(String firstName, String lastName, String phoneNumber, String address, String city, String postalCode, String state) {
		
		try {
			Keywords.clickElement(driver, By.name("first_name"));
			Keywords.clearElement(driver, By.name("first_name"));
			Keywords.writeElement(driver, By.name("first_name"), firstName);
		
			Keywords.clickElement(driver, By.name("last_name"));
			Keywords.clearElement(driver, By.name("last_name"));
			Keywords.writeElement(driver, By.name("last_name"), lastName);
		
			Keywords.clickElement(driver, By.name("phone_number"));
			Keywords.clearElement(driver, By.name("phone_number"));
			Keywords.writeElement(driver, By.name("phone_number"), phoneNumber);
		
			Keywords.clickElement(driver, By.name("address"));
			Keywords.clearElement(driver, By.name("address"));
			Keywords.writeElement(driver, By.name("address"), address);
		
			Keywords.clickElement(driver, By.name("city"));
			Keywords.clearElement(driver, By.name("city"));
			Keywords.writeElement(driver, By.name("city"), city);
		
			Keywords.clickElement(driver, By.name("postal_code"));
			Keywords.clearElement(driver, By.name("postal_code"));
			Keywords.writeElement(driver, By.name("postal_code"), postalCode);		
		
			Keywords.clickElement(driver, By.name("state_/_province_/_region"));
			Keywords.clearElement(driver, By.name("state_/_province_/_region"));
			Keywords.writeElement(driver, By.name("state_/_province_/_region"), state);
			Keywords.clickElement(driver, By.name("next_btnundefined"));
			return true;
		} catch (Exception e) {
			System.err.println("Error in shipping details " + e.getMessage());
			return false;
		}
	}


}
