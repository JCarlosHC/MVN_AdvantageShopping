package advantageShopping.support;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Copyright: Softtek. Description: In this file contains functional keywords to
 * use in the scripts
 * 
 * @author Juan Carlos Hernández Castro<jc.hernandez>
 *
 */

public class Keywords {

	public static void waitForLoadPage(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public static void waitForElement(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public static void checkUrl(WebDriver driver, String url) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.urlToBe(url));
	}

	public static void checkTextInElement(WebDriver driver, By by, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
	}
	
	public static void isPresenceOfElementLocated(WebDriver driver, By by) {
		WebDriverWait w = new WebDriverWait(driver, 30);
		w.until(ExpectedConditions.presenceOfElementLocated(by));

	}

	public static boolean isElementPresent(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static boolean verifyTextIsPresent(WebDriver driver, String value) {
		String bodyText = driver.findElement(By.tagName("body")).getText();
		return bodyText.contains(value);
	}

	public static void writeElement(WebDriver driver, By by, String text) {
		waitForLoadPage(driver, by);
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(text);
		System.out.println("Write on " + by.toString() + " - Value: " + text);
	}

	public static void clickElement(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		element.click();
		System.out.println("Click on " + by.toString());
	}

	public static void clearElement(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		element.clear();
		System.out.println("Clear element " + by.toString());
	}

	public static void clearSelect(WebDriver driver, By by) {
		Select element = new Select(driver.findElement(by));
		element.selectByValue("");
		System.out.println("Clear select " + by.toString());
	}

	public static String getText(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		System.out.println("Get text " + element.getText() + " from element " + by.toString());
		return element.getText();
	}

	public static void selectElement(WebDriver driver, By by, String value) {
		Select select = new Select(driver.findElement(by));
		select.selectByValue(value);
		System.out.println("Select element " + by.toString() + " with " + value);
	}

	public static List<WebElement> getListOfElements(WebDriver driver, By by) {
		List<WebElement> elements = driver.findElements(by);
		return elements;
	}

	public static void sendKey(WebDriver driver, By by, Keys key) {
		WebElement element = driver.findElement(by);
		element.sendKeys(key);
		System.out.println("sent key to element " + by.toString() + " - Value: " + key.toString());
	}

	public static String getTextFromInput(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		System.out.println("Get text " + element.getText() + " from element " + by.toString());
		return element.getAttribute("value");
	}
}
