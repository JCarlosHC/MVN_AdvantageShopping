package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

/**
 * Copyright: Softtek. Description: In this file contains the page model for
 * Category and required functions.
 * 
 * @author Juan Carlos Hernández Castro<jc.hernandez>
 *
 */

public class Page_Category extends PageTemplate {
	private String xpathButtonBuyNow = "//button[@name='buy_now']";
	public static final String URL = "https://advantageonlineshopping.com/#/category/";

	public Page_Category(WebDriver driver) {
		super(driver);
	}

	// This function select a category in the filter (ex. SPEAKERS)
	public Boolean selectCategory(String categoryName) {
		String xpathCategoryChk = "//div[@class='fill']//span[text()='" + categoryName + "']/preceding-sibling::input";
		try {
			Keywords.waitForLoadPage(driver, By.xpath(xpathCategoryChk));
			Keywords.clickElement(driver, By.xpath(xpathCategoryChk));
			return true;
		} catch (Exception e) {
			System.out.println("Error in selectCategory - " + e.getMessage());
			return false;
		}
	}

	// This function select an item by item and filter type (ex. display,
	// manufacturer, processor, compatibility, weight, wireless_technology,
	// scroller_type )
	public Boolean selectCustomItemFilter(String filterType, String id) {
		String xpathFilter = "//input[@name='" + filterType + "_" + id + "']";
		try {
			Keywords.waitForLoadPage(driver, By.xpath(xpathFilter));
			Keywords.clickElement(driver, By.xpath(xpathFilter));
			return true;
		} catch (Exception e) {
			System.out.println("Error in selectCustomItemFilter - " + e.getMessage());
			return false;
		}
	}

	public Boolean selectColor(String color) {
		String xpathColors = "//div[@ng-repeat='color in productsColors']//a[@title='" + color.toUpperCase() + "']";
		try {
			Keywords.clickElement(driver, By.xpath(xpathColors));
			String isSelectedColor = "//div[@ng-repeat='color in productsColors']//a[@title='" + color.toUpperCase()
					+ "' and contains(@class,'colorSelected')]";
			Keywords.waitForLoadPage(driver, By.xpath(isSelectedColor));
			return true;
		} catch (Exception e) {
			System.out.println("Error in selectColor - " + e.getMessage());
			return false;
		}
	}

	// This function select an item (ex. HP ElitePad 1000 G2 Tablet)
	public Boolean selectItemByName(String name) {
		String xpathItemBox = "//a[@class='productName ng-binding' and text()='" + name + "']/ancestor::li";
		try {
			Keywords.waitForLoadPage(driver, By.xpath(xpathItemBox));
			Keywords.clickElement(driver, By.xpath(xpathItemBox));
			return true;
		} catch (Exception e) {
			System.out.println("Error in selectItemByName - " + e.getMessage());
			return false;
		}
	}

	// This function click on buy now
	public Boolean clickOnBuyNow() {
		try {
			Keywords.waitForLoadPage(driver, By.xpath(xpathButtonBuyNow));
			Keywords.clickElement(driver, By.xpath(xpathButtonBuyNow));
			return true;
		} catch (Exception e) {
			System.out.println("Error in clickOnBuyNow - " + e.getMessage());
			return false;
		}
	}
}
