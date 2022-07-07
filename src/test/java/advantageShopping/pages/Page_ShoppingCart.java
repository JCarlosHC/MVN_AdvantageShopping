package advantageShopping.pages;

import java.awt.RenderingHints.Key;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import advantageShopping.templates.PageTemplate;
import advantageShopping.support.*;

public class Page_ShoppingCart extends PageTemplate{

	public Page_ShoppingCart(WebDriver driver) {
		super(driver);
		driver.get("https://advantageonlineshopping.com/#/");
	}
	
	
	String XpathcloseSearch="//div[@data-ng-click='closeSearchForce()']";
	String xpathProductColors = "//span[@id='rabbit']";
	String xpathColorPos= "//span[@id='rabbit' and contains(@class,'colorSelected')]/following-sibling::*";
	String xpathColorPre= "//span[@id='rabbit' and contains(@class,'colorSelected')]/preceding-sibling::*";
	String XpathItemsOnCart= "//label[contains(@class,'productName')]";
	
	
	
	public void searchForItem(String item, int itemNo, String quantity) {
		
	
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("menuSearch"))));
		Keywords.clickElement(driver,By.id("menuSearch"));		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("autoComplete"))));
		Keywords.clickElement(driver, By.id("autoComplete"));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("autoComplete"))));
		Keywords.writeElement(driver, By.id("autoComplete"), item);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("autoComplete"))));
		Keywords.sendKey(driver, By.id("autoComplete"), Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(XpathcloseSearch))));
		Keywords.clickElement(driver, By.xpath(XpathcloseSearch));
		add(itemNo,quantity);
		checkItems(1);
	}
	
	/*
	 * Metodo para añadir un producto al carrito
	 * int itemNo = numero del resultado de la busqueda del item (ej. primer resultado, segundo resultado, etc.)
	 * String quantity = cantidad del producto deseada a agregar al carrito
	*/
	public void add(int itemNo, String quantity) {
		String xpathProduct="(//img[@class=\"imgProduct\"])["+itemNo+"]";
		
		Keywords.clickElement(driver, By.xpath(xpathProduct));
		
		Keywords.clickElement(driver, By.name("quantity"));
		
		Keywords.clearElement(driver, By.name("quantity"));
		
		Keywords.writeElement(driver, By.name("quantity"), quantity);
		
		Keywords.clickElement(driver, By.name("save_to_cart"));
	}
	
	public void checkItems(int expectedItems) {
		Keywords.clickElement(driver, By.id("menuCart"));
		
		List<WebElement> items = Keywords.getListOfElements(driver, By.className("imageUrl"));
		System.out.println(items.size());
		Assert.assertEquals(items.size(), expectedItems);
	}
	
	public void deleteItems(int itemNo) {
		String xpathDeleteItem = "(//a[contains(text(),'REMOVE')])["+itemNo+"]";
		
		Keywords.clickElement(driver, By.id("menuCart"));
		
		int currentItemsInCart = Keywords.getListOfElements(driver, By.xpath(XpathItemsOnCart)).size();
		Keywords.clickElement(driver, By.xpath(xpathDeleteItem));
		int newItemsInCart = Keywords.getListOfElements(driver, By.xpath(XpathItemsOnCart)).size();
		
		
	}
	
	public void changeColorAndQuantity(int itemNo) {
		Keywords.clickElement(driver, By.id("menuCart"));
		
		String xpathEditItem="(//a[contains(text(),'EDIT')])["+itemNo+"]";
		String xpathPrevColor="(//span[contains(@ng-style,'product.color.code')])["+itemNo+"]";
		String xpathPrevQuantity="(//span[contains(@ng-style,'product.color.code')])["+itemNo+"]/../following-sibling::*[1]/label[contains(@class,'ng-binding')]";
		
		
		String currentColor = driver.findElement(By.xpath(xpathPrevColor)).getCssValue("background-color");
		String currentQuantity = driver.findElement(By.xpath(xpathPrevQuantity)).getText();
		
		System.out.println("current Color "+ currentColor);
		System.out.println("current quantity "+currentQuantity);
		
		Keywords.clickElement(driver, By.xpath(xpathEditItem));
		
		///verificar si hay mas de un color para poder escoger
		int colors = Keywords.getListOfElements(driver, By.xpath(xpathProductColors)).size();

		
		String newColor = "";
		
		if(colors>1) {
		 try {
			Keywords.clickElement(driver, By.xpath(xpathColorPos));
			newColor = driver.findElement(By.xpath(xpathColorPos)).getCssValue("background-color");
			Assert.assertNotEquals(currentColor, newColor);
		 } catch(NoSuchElementException e) {
//			 tryPosteriorColor(currentColor,newColor);
		 }
		}
		
		Keywords.clearElement(driver, By.name("quantity"));
		Keywords.writeElement(driver, By.name("quantity"), currentQuantity+1);
		
		String newQuantity = Keywords.getText(driver, By.name("quantity"));
	
		Assert.assertNotEquals(currentQuantity, newQuantity);
	}
	
//	public String tryPosteriorColor(String currentColor, String newColor) {
//		Keywords.clickElement(driver, By.xpath(xpathColorPos));
//		newColor = driver.findElement(By.xpath(xpathColorPos)).getCssValue("background-color");
//		Assert.assertNotEquals(currentColor, newColor);
//	}
}





