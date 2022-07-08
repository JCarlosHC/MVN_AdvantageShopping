package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

/**
 * Copyright: Softtek. Description: this page contains functions of payment method in account
 * 
 * @author Karina Andujo Luna <<KAAL>>
 *
 */

public class Page_AccountPayment extends PageTemplate {

	String xpathSafePay = "//img[@alt='Safepay']/..";
	String xpathMasterCredit = "//img[@alt='Master credit']/..";
	String xpathSPUsername = "//input[@name='safepay_username']";
	String xpathSPPassword ="//input[@name='safepay_password']";
	String xpathButtonSave= "(//button[@id='save_btnundefined'])[1]";
	String xpathCardNumber= "//input[@name='card_number']";
	String xpathCVV = "//input[@name='cvv_number']";
	String xpathSelectMonth = "//select[@name='mmListbox']";
	String xpathSelectYear = "//select[@name='yyyyListbox']";
	String xpathCardName = "//input[@name='cardholder_name']";

	
	public Page_AccountPayment(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean saveSafePayMethod(String user, String password) {
		try {
			Keywords.clickElement(driver, By.xpath(xpathSafePay));
			Keywords.clickElement(driver, By.xpath(xpathSPUsername));
			Keywords.writeElement(driver, By.xpath(xpathSPUsername),user);
		
			Keywords.clickElement(driver, By.xpath(xpathSPPassword));
			Keywords.writeElement(driver, By.xpath(xpathSPPassword),password);
			
			return true;
		}catch(Exception e) {
			return false;
		}	
	}
	
	public boolean saveMasterCreditMethod (String cardNumber,String CVVNumber, String month, String year,String name) {
		String xpathMonthOptions= "//select[@name='mmListbox']//option[@label='"+month+"']";
		String xpathYearOptions= "//select[@name='yyyyListbox']//option[@label='"+year+"']";
		try {
			Keywords.clearElement(driver, By.xpath(xpathMasterCredit));
			Keywords.waitForLoadPage(driver, By.xpath(xpathCardNumber));
			Keywords.clickElement(driver, By.xpath(xpathCardNumber));
			Keywords.writeElement(driver, By.xpath(xpathCardNumber),cardNumber);
			Keywords.clickElement(driver, By.xpath(xpathSelectMonth));
			Keywords.waitForLoadPage(driver, By.xpath(xpathMonthOptions));
			Keywords.clickElement(driver, By.xpath(xpathMonthOptions));

			Keywords.clickElement(driver, By.xpath(xpathSelectYear));
			Keywords.waitForLoadPage(driver, By.xpath(xpathYearOptions));
			Keywords.clickElement(driver, By.xpath(xpathYearOptions));
			
			Keywords.clickElement(driver, By.xpath(xpathCardName));
			Keywords.writeElement(driver, By.xpath(xpathCardName), name);
			
			Keywords.clickElement(driver, By.xpath(xpathButtonSave));
			
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	

}
