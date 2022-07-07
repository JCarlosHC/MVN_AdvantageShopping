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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

public class Page_ProductDetails extends PageTemplate {

	private String xpathTxtTitle = "//h1";
	private String xpathTxtPrize = "//div[@id='Description']//h2[contains(text(),'$')][1]";
	private String xpathTxtDescription = "//div[@id='Description']//p";
	private String xpathColors = "//div[@id='productProperties']//div[@class='']//span";
	private String xpathQuantityInput = "//input[@name='quantity']";
	private String xpathAddToCartButton = "//button[@name='save_to_cart']";
	private String xpathProductSpecifications ="//article[@class='max-width '][2]//div";//2 LABELS POR DIV
	private Dictionary<String,String> specifications;
	public static WebDriverWait wait;
	
	
	public Page_ProductDetails(WebDriver driver) {
		super(driver);	
		specifications =  new Hashtable<String,String>();
	}
	
	
	public void getSpecifications() {
		List<WebElement> specificationsList = driver.findElements(By.xpath(xpathProductSpecifications));
		for(int i=0;i<specificationsList.size();i++) 
		{
			List<WebElement> labels = specificationsList.get(i).findElements(By.tagName("label"));
			specifications.put(labels.get(0).getText(),labels.get(1).getText());
			System.out.println(labels.get(0).getText()+"  "+labels.get(1).getText());
		}
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
	
	public void addToCart() {
		Keywords.clickElement(driver, By.xpath(xpathAddToCartButton));
	}

}
