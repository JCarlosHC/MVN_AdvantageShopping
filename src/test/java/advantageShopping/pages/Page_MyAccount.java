package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

/**
 * Copyright: Softtek. Description: this page contains functions pertaining account settings
 * 
 * @author Karina Andujo Luna <<KAAL>>
 *
 */

public class Page_MyAccount extends PageTemplate{
	
	String xpathEditInfo = "//h3[contains(text(),'Account details')]/a[contains(text(),'Edit')]";
	String xpathEditPayment ="//h3[contains(text(),'Preferred payment method')]/a[contains(text(),'Edit')]";
	String xpathConnectionDetails = "//h3[contains(text(),'CONNECTION DETAILS')]";
	String xpathPaymentDetails = "//label[contains(text(),'Choose payment method below')]";

	public Page_MyAccount(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean goToEditAccountDetails() {
		Keywords.clickElement(driver, By.xpath(xpathEditInfo));
		Keywords.waitForLoadPage(driver, By.xpath(xpathConnectionDetails));
		
		return Keywords.isElementPresent(driver, By.xpath(xpathConnectionDetails));
	}

	public boolean goToEditPaymentMethod() {
		Keywords.waitForLoadPage(driver, By.xpath(xpathEditPayment));
		Keywords.clickElement(driver, By.xpath(xpathEditPayment));
		Keywords.waitForLoadPage(driver, By.xpath(xpathPaymentDetails));
		
		return Keywords.isElementPresent(driver, By.xpath(xpathPaymentDetails));
	}
}
