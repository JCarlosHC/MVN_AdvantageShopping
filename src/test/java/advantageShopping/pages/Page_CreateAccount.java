package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

/**
 * Copyright: Softtek. Description: In this file contains the page model for
 * Create Account and required functions.
 * 
 * @author Juan Carlos Hernández Castro<jc.hernandez>
 *
 */

public class Page_CreateAccount extends PageTemplate {

	private String xpathUsername = "//input[@name='usernameRegisterPage']";
	private String xpathEmail = "//input[@name='emailRegisterPage']";
	private String xpathPassword = "//input[@name='passwordRegisterPage']";
	private String xpathConfirmPassword = "//input[@name='confirm_passwordRegisterPage']";
	private String xpathFirstName = "//input[@name='first_nameRegisterPage']";
	private String xpathLastName = "//input[@name='last_nameRegisterPage']";
	private String xpathPhoneNumber = "//input[@name='phone_numberRegisterPage']";
	private String xpathCountry = "//select[@name='countryListboxRegisterPage']";
	private String xpathCity = "//input[@name='cityRegisterPage']";
	private String xpathAddress = "//input[@name='addressRegisterPage']";
	private String xpathState = "//input[@name='state_/_province_/_regionRegisterPage']";
	private String xpathPostalCode = "//input[@name='postal_codeRegisterPage']";
	private String xpathChkAgreement = "//input[@name='i_agree']";
	private String xpathButtonRegister = "//button[@id='register_btnundefined']";
	private String xpathLnkHaveAccount = "//a[@translate='ALREADY_HAVE_AN_ACCOUNT']";
	public static final String URL = "https://advantageonlineshopping.com/#/register";

	public Page_CreateAccount(WebDriver driver) {
		super(driver);
	}

	//This function create a new account and validate the results
	public Boolean createAccount(String username, String email, String password, String confirmPassword,
			String firstName, String lastName, String phoneNumber, String country, String city, String address,
			String state, String postalCode) {

		try {

			Keywords.writeElement(driver, By.xpath(xpathUsername), username);
			Keywords.writeElement(driver, By.xpath(xpathEmail), email);
			Keywords.writeElement(driver, By.xpath(xpathPassword), password);
			Keywords.writeElement(driver, By.xpath(xpathConfirmPassword), confirmPassword);
			Keywords.writeElement(driver, By.xpath(xpathFirstName), firstName);
			Keywords.writeElement(driver, By.xpath(xpathLastName), lastName);
			Keywords.writeElement(driver, By.xpath(xpathPhoneNumber), phoneNumber);
//			Keywords.writeElement(driver, By.xpath(xpathCountry), country);
			Keywords.writeElement(driver, By.xpath(xpathCity), city);
			Keywords.writeElement(driver, By.xpath(xpathAddress), address);
			Keywords.writeElement(driver, By.xpath(xpathState), state);
			Keywords.writeElement(driver, By.xpath(xpathPostalCode), postalCode);
			Keywords.clickElement(driver, By.xpath(xpathChkAgreement));
			Keywords.clickElement(driver, By.xpath(xpathButtonRegister));

			try {

				String xpathUserLoggedWelcome = "//span[@class='hi-user containMiniTitle ng-binding']";
				if (Keywords.verifyTextIsPresent(driver, "User name already exist")) {
					System.out.println("User name already exist");
					return true;
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
			System.out.println("Error in createAccount - " + e.getMessage());
			return false;
		}
	}

	public Boolean goToHaveAccount() {
		try {
			Keywords.clickElement(driver, By.xpath(xpathLnkHaveAccount));
			return true;
		} catch (Exception e) {
			System.out.println("Error in goToHaveAccount - " + e);
			return false;
		}
	}
}
