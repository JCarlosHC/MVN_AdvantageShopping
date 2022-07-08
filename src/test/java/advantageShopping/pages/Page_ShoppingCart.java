package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.templates.PageTemplate;
import advantageShopping.support.*;


/**
 * Copyright: Softtek. Description: this page contains functions of the shopping cart.
 * 
 * @author Karina Andujo Luna <<KAAL>>
 *
 */

public class Page_ShoppingCart extends PageTemplate{
	
	String xpathProductColors = "//span[@id='rabbit']";
	String xpathItemsOnCart= "//label[contains(@class,'productName')]";
	String xpathOrderMenu = "//h3[contains(.,'ORDER PAYMENT')]";
	public static final String xpathPageHeading = "//h3[contains(.,'SHOPPING CART')]";
	public static final String URL = "https://advantageonlineshopping.com/#/shoppingCart";

	public Page_ShoppingCart(WebDriver driver) {
		super(driver);
	}
	

	/*
	 * Function to delete items from cart
	 * @product = Name of the product to delete (ie: "HP ELITEPAD 1000 G2 TABLET")
	 */
	public boolean deleteItems(String product) {
		String xpathDeleteItem = "//label[contains(text(),'"+product+"')]/../..//span/a[contains(@data-ng-click,'removeProduct')]";		
		int currentItemsInCart = Keywords.getListOfElements(driver, By.xpath(xpathItemsOnCart)).size();
		
		Keywords.clickElement(driver, By.xpath(xpathDeleteItem));
		
		int newItemsInCart = Keywords.getListOfElements(driver, By.xpath(xpathItemsOnCart)).size();
		
		if(currentItemsInCart != newItemsInCart) {
			return true;
		}else {
			return false;
		}			
	}
	
	/*
	 * Function to edit cart items
	 * @product = Name of the product we want to edit (ie: "HP ELITEPAD 1000 G2 TABLET")
	 */
	
	public boolean editItems(String product) {
		
		String xpathEditItem = "//label[contains(text(),'"+product+"')]/../..//span/a[contains(text(),'EDIT')]";	
		String xpathProductMenu = "//a[contains(text(),'"+product+"')]";
		
		Keywords.clickElement(driver, By.xpath(xpathEditItem));
		Keywords.waitForLoadPage(driver, By.xpath(xpathProductMenu));
				
		return Keywords.isElementPresent(driver, By.xpath(xpathProductMenu));
	}
	
	/*
	 * clicks on checkout button in cart page
	 */
	public boolean checkOut() {
		try {
			System.out.println("checking out...");
			Keywords.isPresenceOfElementLocated(driver,By.xpath("//tool-tip-cart[contains(@style,'display: none')]"));
			Keywords.waitForLoadPage(driver,By.id("checkOutButton"));
			Keywords.clickElement(driver, By.id("checkOutButton"));
			Keywords.waitForLoadPage(driver, By.xpath(xpathOrderMenu));
			return Keywords.isElementPresent(driver, By.xpath(xpathOrderMenu));
		}catch(Exception e) {
			System.out.println("Shopping cart - checking out - "+e.getMessage());
			return false;
		}
	}
	
	
}





