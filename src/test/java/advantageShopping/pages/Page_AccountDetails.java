package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

/**
 * Copyright: Softtek. Description: this page contains functions of Account Details
 * 
 * @author Karina Andujo Luna <<KAAL>>
 *
 */
public class Page_AccountDetails extends PageTemplate{
	String xpathFirstName="//input[@name='first_nameAccountDetails']";
	String xpathLastName="//input[@name='last_nameAccountDetails']";
	String xpathPhone="//input[@name='phone_numberAccountDetails']";
	String xpathCity="//input[@name='cityAccountDetails']";
	String xpathAdress="//input[@name='addressAccountDetails']";
	String xpathZip="//input[@name='postal_codeAccountDetails']";
	String xpathState="//input[@name='state_/_province_/_regionAccountDetails']";
	String xpathButtonSave = "//button[@id='save_btnundefined']";
	
	public Page_AccountDetails(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean editAccountInformation(String firstName, String lastName, String phone, String city, String address, String zip, String state) {
		try {
			Keywords.clickElement(driver,  By.xpath(xpathFirstName));
			Keywords.writeElement(driver, By.xpath(xpathFirstName), firstName);
		
			Keywords.clickElement(driver,  By.xpath(xpathLastName));
			Keywords.writeElement(driver, By.xpath(xpathLastName), lastName);
		
			Keywords.clickElement(driver,  By.xpath(xpathPhone));
			Keywords.writeElement(driver, By.xpath(xpathPhone), phone);
		
			Keywords.clickElement(driver,  By.xpath(xpathCity));
			Keywords.writeElement(driver, By.xpath(xpathCity), city);
		
			Keywords.clickElement(driver,  By.xpath(xpathAdress));
			Keywords.writeElement(driver, By.xpath(xpathAdress), address);
		
			Keywords.clickElement(driver,  By.xpath(xpathZip));
			Keywords.writeElement(driver, By.xpath(xpathZip), zip);
		
			Keywords.clickElement(driver,  By.xpath(xpathState));
			Keywords.writeElement(driver, By.xpath(xpathState), state);
			
			Keywords.clickElement(driver, By.xpath(xpathButtonSave));
			return true;
		}catch(Exception e) {
			System.err.println("Error in account details: "+e);
			return false;
		}
		
	}

}
