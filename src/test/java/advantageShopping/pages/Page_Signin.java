package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

/**
 * Copyright: Softtek. Description: In this file contains the page model for
 * Signin and required functions.
 * 
 * @author Juan Carlos Hernández Castro<jc.hernandez>
 *
 */

public class Page_Signin extends PageTemplate {

	private String xpathSigninFb = "//span[text()='SIGN IN WITH FACEBOOK']";
	private String xpathInputUsername = "//input[@name='username']";
	private String xpathInputPassword = "//input[@name='password']";
	private String xpathRemember = "//input[@name='remember_me']";
	private String xpathButtonSignin = "//button[@id='sign_in_btnundefined']";
	private String xpathLnkForgotPassword = "//a[@translate='FORGOT_PASSWORD']";
	private String xpathLnkCreateAccount = "//a[text()='CREATE NEW ACCOUNT']";

	public Page_Signin(WebDriver driver) {
		super(driver);
	}

	// This function is to sign in with username, password and remember option
	public Boolean signInWithUserPassword(String username, String password, boolean remember) {
		String xpathUserLoggedWelcome = "//span[@class='hi-user containMiniTitle ng-binding']";
		try {

			Keywords.writeElement(driver, By.xpath(xpathInputUsername), username);
			Keywords.writeElement(driver, By.xpath(xpathInputPassword), password);
			if (remember) {
				Keywords.clickElement(driver, By.xpath(xpathRemember));
			}
			Keywords.clickElement(driver, By.xpath(xpathButtonSignin));

			try {

				if (Keywords.verifyTextIsPresent(driver, "User is temporary blocked from login")) {
					System.out.println("User is temporary blocked from login");
					return false;
				}

				if (Keywords.verifyTextIsPresent(driver, "Incorrect user name or password.")) {
					System.out.println("Incorrect user name or password.");
					return false;
				}

				Keywords.isPresenceOfElementLocated(driver, By.xpath(xpathUserLoggedWelcome));

				if (!Keywords.isElementPresent(driver, By.xpath(xpathUserLoggedWelcome))) {
					System.err.println("xpath Element is no present " + xpathUserLoggedWelcome);
					return false;
				}

				String strNombreActual = Keywords.getText(driver, By.xpath(xpathUserLoggedWelcome));
				if (strNombreActual.equals(username)) {
					// Here we can add logout
					return true;
				} else {
					System.err.println("Actual user doesn't match with expected. Actual: " + strNombreActual
							+ " expected: " + username);
					return false;
				}
			} catch (Exception err) {
				System.err.println("Error in validation " + err.getMessage());
				return false;
			}

		} catch (Exception e) {
			System.out.println("Error in signInWithUserPassword - " + e.getMessage());
			return false;
		}
	}

	// This function is to sign in with facebook
	public Boolean signInWithFacebook() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathSigninFb));
			if (Keywords.verifyTextIsPresent(driver,
					"403 FORBIDDEN Sorry, connecting to Facebook is currently unavailable. Please try again later.")) {
				System.out.println(
						"403 FORBIDDEN Sorry, connecting to Facebook is currently unavailable. Please try again later.");
				return true;
			}

			return true;
		} catch (Exception e) {
			System.out.println("Error in signInWithFacebook - " + e.getMessage());
			return false;
		}
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

	public Boolean goToForgotPassword() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkForgotPassword));
			return true;
		} catch (Exception e) {
			System.out.println("goToForgotPassword - " + e);
			return false;
		}
	}
}
