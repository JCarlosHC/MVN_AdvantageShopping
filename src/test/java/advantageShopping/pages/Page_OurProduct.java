package advantageShopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openxmlformats.schemas.drawingml.x2006.main.impl.STPenAlignmentImpl;

import advantageShopping.support.Keywords;
import advantageShopping.templates.PageTemplate;

public class Page_OurProduct extends PageTemplate {

	private String xpathProductSpeaker = "//div[@id='speakersImg']";
	private String xpathProductHeadphone = "//div[@id='headphonesImg']";
	private String xpathProductLaptop = "//div[@id='laptopsImg']";
	private String xpathProductTablet = "//div[@id='tabletsImg']";
	private String xpathProductMouse = "//div[@id='miceImg']";
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
	
	public void goToTablets() {
		WebElement tabletItem = driver.findElement(By.xpath(this.xpathProductTablet));
		tabletItem.click();
	}
	
	public void goToHeadphones() {
		WebElement headphoneItem = driver.findElement(By.xpath(this.xpathProductHeadphone));
		headphoneItem.click();
	}
	
	public void goToLaptops() {
		WebElement laptopItem = driver.findElement(By.xpath(this.xpathProductLaptop));
		laptopItem.click();
	}
	
	public void goToMouse() {
		WebElement mouseItem = driver.findElement(By.xpath(this.xpathProductMouse));
		mouseItem.click();
	}
}