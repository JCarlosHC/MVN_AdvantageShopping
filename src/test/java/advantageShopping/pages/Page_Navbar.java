package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

/**
 * Copyright: Softtek. Description: In this file contains the page model for
 * Navbar and required functions.
 * 
 * @author Juan Carlos Hernández Castro<jc.hernandez>
 *
 */

public class Page_Navbar extends PageTemplate {

	private String xpathLogoHome = "//nav//div[@class='logo']//a";
	private String xpathLnkOurProducts = "//nav//li[@class='nav-li-Links']//a[text()='OUR PRODUCTS']";
	private String xpathLnkSpecialOffer = "//nav//li[@class='nav-li-Links']//a[text()='SPECIAL OFFER']";
	private String xpathLnkPopularItems = "//nav//li[@class='nav-li-Links']//a[text()='POPULAR ITEMS']";
	private String xpathLnkContactUs = "//nav//li[@class='nav-li-Links']//a[text()='CONTACT US']";
	private String xpathLnkSearch = "//div[@id='search']//a";
//	private String xpathInputSearch = "//input[@id='autoComplete']";
	private String xpathLnkUser = "//a[@id='menuUserLink']";
	private String xpathLnkShoppingCart = "//a[@id='shoppingCartLink']";
	private String xpathLnkHelp = "//a[@id='helpLink']";
	public static final String URL = "https://advantageonlineshopping.com/#/";

	public Page_Navbar(WebDriver driver) {
		super(driver);
	}

	public Boolean goToHome() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLogoHome));
			return true;
		} catch (Exception e) {
			System.out.println("goToHome - " + e);
			return false;
		}
	}

	public Boolean goToOurProducts() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkOurProducts));
			return true;
		} catch (Exception e) {
			System.out.println("goToOurProducts - " + e);
			return false;
		}
	}

	public Boolean goToSpecialOffer() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkSpecialOffer));
			return true;
		} catch (Exception e) {
			System.out.println("goToSpecialOffer - " + e);
			return false;
		}
	}

	public Boolean goToPopularItems() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkPopularItems));
			return true;
		} catch (Exception e) {
			System.out.println("goToPopularItems - " + e);
			return false;
		}
	}

	public Boolean goToContactUs() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkContactUs));
			return true;
		} catch (Exception e) {
			System.out.println("goToContactUs - " + e);
			return false;
		}
	}

	public Boolean goToSearch() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkSearch));
			return true;
		} catch (Exception e) {
			System.out.println("goToSearch - " + e);
			return false;
		}
	}

	public Boolean goToProfileUser() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkUser));
			return true;
		} catch (Exception e) {
			System.out.println("goToProfileUser - " + e);
			return false;
		}
	}

	public Boolean goToShoppingCart() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkShoppingCart));
			return true;
		} catch (Exception e) {
			System.out.println("goToShoppingCart - " + e);
			return false;
		}
	}

	public Boolean goToHelp() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkHelp));
			return true;
		} catch (Exception e) {
			System.out.println("goToHelp - " + e);
			return false;
		}
	}
}
