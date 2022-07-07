package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

/**
 * Copyright: Softtek. Description: this page contains functions of the Order page.
 * 
 * @author Karina Andujo Luna <<KAAL>>
 *
 */

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
		
		Keywords.clickElement(driver, By.name("first_name"));
		Keywords.clearElement(driver, By.className("first_name"));
		Keywords.writeElement(driver, By.className("first_name"), firstName);
		
		Keywords.clickElement(driver, By.name("last_name"));
		Keywords.clearElement(driver, By.className("last_name"));
		Keywords.writeElement(driver, By.className("last_name"), lastName);
		
		Keywords.clickElement(driver, By.name("phone_number"));
		Keywords.clearElement(driver, By.className("phone_number"));
		Keywords.writeElement(driver, By.className("phone_number"), phoneNumber);
		
		Keywords.clickElement(driver, By.name("address"));
		Keywords.clearElement(driver, By.className("address"));
		Keywords.writeElement(driver, By.className("address"), address);
		
		Keywords.clickElement(driver, By.name("city"));
		Keywords.clearElement(driver, By.className("city"));
		Keywords.writeElement(driver, By.className("city"), city);
		
		Keywords.clickElement(driver, By.name("postal_code"));
		Keywords.clearElement(driver, By.className("postal_code"));
		Keywords.writeElement(driver, By.className("postal_code"), postalCode);		
		
		Keywords.clickElement(driver, By.name("state_/_province_/_region"));
		Keywords.clearElement(driver, By.className("state_/_province_/_region"));
		Keywords.writeElement(driver, By.className("state_/_province_/_region"), state);
		
		try {
			Keywords.clickElement(driver, By.name("next_btnundefined"));
			return true;
		} catch (Exception e) {
			System.err.println("Error in shipping details " + e.getMessage());
			return false;
		}
	}
	
	

}
