/**
 * Copyright: Softtek. Description: In this file contains the page model for
 * Product Details and required functions.
 * 
 * @author Angel Francisco Lastra Torres<angel.lastra>
 *
 */

package advantageShopping.pages;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	
	public Page_ProductDetails(WebDriver driver) {
		super(driver);	
		
	}
	
	public String getPrize() {
		return Keywords.getText(driver, By.xpath(xpathTxtPrize));
	}
	
	public String getDescription() {
		return Keywords.getText(driver, By.xpath(xpathTxtDescription));
	}
	
	
	public boolean getSpecifications() {
		List<WebElement> specificationsList = Keywords.getListOfElements(driver, By.xpath(xpathProductSpecifications));
		if(specificationsList.size()>0) return true;
		else return false;
	}
	
	public void changeQuantity(int number) {
		
		for(int i=1;i<number;i++)
		{
		Keywords.clickElement(driver, By.xpath("//div[@class='plus']"));
		}
	}
	
	public void changeColor(String color) {
		String selectedColor = xpathColors + "[@title="+"'"+color.toUpperCase()+"'"+"]";
		Keywords.clickElement(driver, By.xpath(selectedColor));
	}
	
	public boolean validateColor(String color) {
		String selectedColor = xpathColors + "[@title="+"'"+color.toUpperCase()+"'"+"and contains(@class,'colorSelected')]"+"";
		try {
			driver.findElement(By.xpath(selectedColor));
			return true;
		}
		catch(NoSuchElementException e) {
			System.out.println(e.toString());
			return false;
		}
		
	}
	
	public void addToCart() {
		Keywords.clickElement(driver, By.xpath(xpathAddToCartButton));
	}
	
	public boolean validateProductAdded() {
		String popUpProductTitle = Keywords.getText(driver, By.xpath(xpathTitleProductPopUpShoppingCart));
		String productDetailsTitle = Keywords.getText(driver, By.xpath(xpathTxtProductTitle));
		if(popUpProductTitle.toUpperCase().equals(productDetailsTitle.toUpperCase())) return true;
		else return false;
	}

}
