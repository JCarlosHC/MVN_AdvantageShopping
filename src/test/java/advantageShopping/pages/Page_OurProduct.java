package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openxmlformats.schemas.drawingml.x2006.main.impl.STPenAlignmentImpl;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;
/**
 * Copyright: Softtek. 
 * Description: Model for the main webpage (OurProducts)
 * @author David Morales<davidf.morales>
 *
 */
public class Page_OurProduct extends PageTemplate {

	private String xpathProductSpeaker = "//div[@id='speakersImg']";
	private String xpathProductHeadphone = "//div[@id='headphonesImg']";
	private String xpathProductLaptop = "//div[@id='laptopsImg']";
	private String xpathProductTablet = "//div[@id='tabletsImg']";
	private String xpathProductMouse = "//div[@id='miceImg']";
	
	private String xpathSeeOffer = "//button[@id='see_offer_btn']";
	
	
	
	private String xpathHeading = "//h3[contains(@class, 'Title')]";
	
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
	

	public Page_OurProduct(WebDriver driver) {
		super(driver);
		
				
	}
	
	public boolean goToSpeakers() {
		Keywords.clickElement(this.driver, By.xpath(this.xpathProductSpeaker));
		By headingBy = By.xpath(this.xpathHeading);
		Keywords.isPresenceOfElementLocated(driver, headingBy);
		String text = Keywords.getText(driver, headingBy);
		System.out.println(text);
		return text.contains("SPEAKERS");
	}
	
	public boolean goToSpecial() {
		Page_Navbar navbar = new Page_Navbar(driver);
		driver.get(Page_OurProduct.URL);
		navbar.goToSpecialOffer();
		
		By seeOfferBy = By.xpath(xpathSeeOffer);
		
		Keywords.isPresenceOfElementLocated(driver, seeOfferBy);
		Keywords.clickElement(this.driver, seeOfferBy);
		
		return true;
	}
	
	public void goToTablets() {
		By tabletsBy = By.xpath(xpathProductTablet);
		Keywords.isPresenceOfElementLocated(driver, tabletsBy);
		Keywords.clickElement(this.driver, tabletsBy);
	}
	
	public void goToHeadphones() {
		By headphonesBy = By.xpath(this.xpathProductHeadphone);
		Keywords.isPresenceOfElementLocated(driver, headphonesBy);
		Keywords.clickElement(this.driver, headphonesBy);
	}
	
	public void goToLaptops() {
		By laptopsBy = By.xpath(this.xpathProductLaptop);
		Keywords.isPresenceOfElementLocated(driver, laptopsBy);
		Keywords.clickElement(this.driver, laptopsBy);
	}
	
	public void goToMouse() {
		By mouseBy = By.xpath(this.xpathProductMouse);
		Keywords.isPresenceOfElementLocated(driver, mouseBy);
		Keywords.clickElement(this.driver, mouseBy);
	}
}
