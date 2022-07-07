/**
 * Copyright: Softtek. Description: In this file contains the page model for
 * Product Details and required functions.
 * 
 * @author Angel Francisco Lastra Torres<angel.lastra>
 *
 */

package advantageShopping.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

public class Page_ProductDetails extends PageTemplate {

	private String xpathTxtProductTitle = "//div[@id='Description']//h1";
	private String xpathTxtPrize = "//div[@id='Description']//h2[contains(text(),'$')][1]";
	private String xpathTxtDescription = "//div[@id='Description']//p";
	private String xpathColors = "//div[@id='productProperties']//div[@class='']//span";
	private String xpathAddToCartButton = "//button[@name='save_to_cart']";
	private String xpathProductSpecifications ="//article[@class='max-width '][2]//div";
	private String xpathTitleProductPopUpShoppingCart = "//table//tr//td[2]//h3";
	private String xpathInputQuantity = "//input[@name='quantity']";
	public static final String URL = "https://www.advantageonlineshopping.com/#/product/3";

	public Page_ProductDetails(WebDriver driver) {
		super(driver);	
		
	}
	
	public boolean getPrize() {
		Keywords.waitForLoadPage(driver, By.xpath(xpathTxtPrize));
		Boolean val = Keywords.isElementPresent(driver,By.xpath(xpathTxtPrize));
		return val;
	}
	
	public boolean getDescription() {
		Keywords.waitForLoadPage(driver, By.xpath(xpathTxtDescription));
		Boolean val = Keywords.isElementPresent(driver,By.xpath(xpathTxtDescription));
		return val;
	}
	
	
	public boolean getSpecifications() {
		Keywords.waitForLoadPage(driver, By.xpath(xpathProductSpecifications));
		List<WebElement> specificationsList = Keywords.getListOfElements(driver, By.xpath(xpathProductSpecifications));
		if(specificationsList.size()>0) 
			return true;
		else 
			return false;
	}
	
	public boolean changeQuantity(String number) {
		
		for(int i=1;i<Integer.parseInt(number);i++)
		{
			Keywords.clickElement(driver, By.xpath("//div[@class='plus']"));
		}
		String quantity = Keywords.getTextFromInput(driver, By.xpath(xpathInputQuantity));
		if(quantity.equals(number))
			return true; 
		else
			return false;
		//Keywords.writeElement(driver, By.xpath(xpathInputQuantity),number);
	}
	
	public boolean changeColor(String color) {
		String selectedColor = xpathColors + "[@title="+"'"+color.toUpperCase()+"'"+"]";
		Keywords.clickElement(driver, By.xpath(selectedColor));
		String isSelectedColor = xpathColors + "[@title="+"'"+color.toUpperCase()+"'"+"and contains(@class,'colorSelected')]"+"";
		Keywords.waitForLoadPage(driver, By.xpath(isSelectedColor));
		return Keywords.isElementPresent(driver, By.xpath(isSelectedColor));
	}
	
	
	public boolean addToCart() {
		Keywords.clickElement(driver, By.xpath(xpathAddToCartButton));
		String popUpProductTitle = Keywords.getText(driver, By.xpath(xpathTitleProductPopUpShoppingCart));
		String productDetailsTitle = Keywords.getText(driver, By.xpath(xpathTxtProductTitle));
		if(popUpProductTitle.toUpperCase().equals(productDetailsTitle.toUpperCase())) 
			return true;
		else
			return false;
	}
	

}
