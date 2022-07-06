package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

public class Page_Navbar extends PageTemplate {

	private String xpathLogoHome = "";
	private String xpathLnkOurProducts = "";
	private String xpathLnkSpecialOffer = "";
	private String xpathLnkPopularItems = "";
	private String xpathLnkContactUs = "";
	private String xpathLnkSearch = "";
	private String xpathLnkUser = "";
	private String xpathLnkShoppingCart = "";
	private String xpathLnkHelp = "";
	public static final String URL = "https://advantageonlineshopping.com/#/";

	public Page_Navbar(WebDriver driver) {
		super(driver);
	}
}
