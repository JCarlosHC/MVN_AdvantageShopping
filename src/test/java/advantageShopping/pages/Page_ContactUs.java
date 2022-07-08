package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

public class Page_ContactUs extends PageTemplate {

	public Page_ContactUs(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean fillContactForm(String category, String product, String email, String subject) {
		String xpathCategory = "//Select[@name='categoryListboxContactUs']//option[@label='"+category+"']";
		String xpathProduct = "//select[@name='productListboxContactUs']//option[@Label='"+product+"']";
		String xpathSuccess = "//p[contains(text(),'Thank you for contacting Advantage support.')]";
		try {
			Keywords.clickElement(driver, By.name("categoryListboxContactUs"));
			Keywords.waitForLoadPage(driver, By.xpath(xpathCategory));
			Keywords.clickElement(driver,  By.xpath(xpathCategory));
		
			Keywords.clickElement(driver, By.name("productListboxContactUs"));
			Keywords.waitForLoadPage(driver, By.xpath(xpathProduct));
			Keywords.clickElement(driver,  By.xpath(xpathProduct));
		
			Keywords.writeElement(driver, By.name("emailContactUs"), email);
			Keywords.writeElement(driver, By.name("subjectTextareaContactUs"), subject);
		
			Keywords.clickElement(driver, By.id("send_btnundefined"));
			Keywords.waitForLoadPage(driver, By.xpath(xpathSuccess));
		if(Keywords.isElementPresent(driver, By.xpath(xpathSuccess))) {
			return true;
		}else {
			return false;
		}
		}catch(Exception e) {
			return false;
		}
	}

}
