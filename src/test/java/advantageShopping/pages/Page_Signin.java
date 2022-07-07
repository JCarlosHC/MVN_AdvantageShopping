package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

public class Page_Signin extends PageTemplate {
	
	private String xpathSigninFb = "";
	private String xpathInputUsername = "";
	private String xpathInputPassword = "";
	private String xpathRemember = "";
	private String xpathButtonSignin = "";
	private String xpathLnkForgotPassword = "";
	private String xpathLnkCreateAccount = "//a[text()='CREATE NEW ACCOUNT']";
	
	public Page_Signin(WebDriver driver) {
		super(driver);
	}
	
	public Boolean goToCreateAccount() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkCreateAccount));
			return true;
		} catch (Exception e) {
			System.out.println("goToCreateAccount - " + e);
			return false;
		}
	}
}
