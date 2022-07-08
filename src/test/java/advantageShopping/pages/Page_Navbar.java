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
	private String xpathInputSearch = "//input[@id='autoComplete']";
	private String xpathPopupSearch = "//div[@id='output']";
	private String xpathCloseSearch = "//div[@data-ng-click='closeSearchForce()']";
	private String xpathTitleResults = "//h3[@id='searchResultLabel']";
	private String xpathLnkUser = "//a[@id='menuUserLink']";
	private String xpathLnkMyAccount = "(//*[contains(text(),'My account')])[2]";
	private String xpathLnkMyOrders = "(//*[contains(text(),'My orders')])[2]";
	private String xpathLnkSignOut = "(//*[contains(text(),'Sign out')])[2]";
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

	// This method click on search and find an item by name (ex 'HP ROAR MINI
	// WIRELESS SPEAKER')
	public Boolean goToSearchAndSearchByName(String nameItem) {
		try {
			Keywords.waitForLoadPage(driver, By.xpath(xpathLnkSearch));
			Keywords.clickElement(driver, By.xpath(xpathLnkSearch));
			Keywords.isPresenceOfElementLocated(driver,
					By.xpath("//div[@class='autoCompleteCover' and contains(@style,'opacity: 1')]"));
			Keywords.waitForLoadPage(driver, By.xpath(xpathInputSearch));
			Keywords.writeElement(driver, By.xpath(xpathInputSearch), nameItem);
			Keywords.waitForLoadPage(driver, By.xpath(xpathPopupSearch));
			Keywords.enterElement(driver, By.xpath(xpathInputSearch));
			Keywords.waitForLoadPage(driver, By.xpath(xpathTitleResults));
			Keywords.isElementPresent(driver, By.xpath(xpathTitleResults));
			Keywords.waitForLoadPage(driver, By.xpath(xpathCloseSearch));
			Keywords.clickElement(driver, By.xpath(xpathCloseSearch));
			Keywords.isPresenceOfElementLocated(driver,
					By.xpath("//div[@class='autoCompleteCover' and contains(@style,'opacity: 0.5')]"));
			return true;
		} catch (Exception e) {
			System.out.println("goToSearchAndSearchByName - " + e);
			return false;
		}
	}

	// This method click on search and find an item by category (ex 'laptops')
	public Boolean goToSearchAndSearchByCategory(String categoryName) {
		String xpathCategoryName = "//label[contains(text(),'" + categoryName + "')]/ancestor::a[@class='ng-scope']";
		try {
			Keywords.waitForLoadPage(driver, By.xpath(xpathLnkSearch));
			Keywords.clickElement(driver, By.xpath(xpathLnkSearch));
			Keywords.isPresenceOfElementLocated(driver,
					By.xpath("//div[@class='autoCompleteCover' and contains(@style,'opacity: 1')]"));
			Keywords.waitForLoadPage(driver, By.xpath(xpathInputSearch));
			Keywords.writeElement(driver, By.xpath(xpathInputSearch), categoryName);
			Keywords.waitForLoadPage(driver, By.xpath(xpathPopupSearch));
			Keywords.waitForLoadPage(driver, By.xpath(xpathCategoryName));
			Keywords.clickElement(driver, By.xpath(xpathCategoryName));
			Keywords.waitForLoadPage(driver, By.xpath(xpathCloseSearch));
			Keywords.clickElement(driver, By.xpath(xpathCloseSearch));
			Keywords.isPresenceOfElementLocated(driver,
					By.xpath("//div[@class='autoCompleteCover' and contains(@style,'opacity: 0.5')]"));
			return true;
		} catch (Exception e) {
			System.out.println("goToSearchAndSearchByCategory - " + e);
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

	public Boolean goToMyAccount() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkMyAccount));
			return true;
		} catch (Exception e) {
			System.out.println("goToProfileUser - " + e);
			return false;
		}
	}

	public Boolean goToMyOrders() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkMyOrders));
			return true;
		} catch (Exception e) {
			System.out.println("goToProfileUser - " + e);
			return false;
		}
	}

	public Boolean signOut() {
		try {
			if (goToProfileUser()) {
				Keywords.clickElement(driver, By.xpath(xpathLnkSignOut));
				return true;
			}
			return false;

		} catch (Exception e) {
			System.out.println("goToProfileUser - " + e);
			return false;
		}
	}

	public Boolean goToShoppingCart() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkShoppingCart));
			By shoppingTitleBy = By.xpath(Page_ShoppingCart.xpathPageHeading);
			Keywords.waitForElement(driver, shoppingTitleBy);
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
