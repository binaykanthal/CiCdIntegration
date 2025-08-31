package binaykanthal.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import binaykanthal.basePage.BasePage;

public class ConfirmPage extends BasePage {

	WebDriver driver;
	public ConfirmPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(css="h1.hero-primary")
	private WebElement confirmationText;
	
	@FindBy(css="label.ng-star-inserted")
	private WebElement orderNumber;
	
	public String getConfirmationText() {
		return confirmationText.getText();	
	}
	
	public String getOrderNumber() {
		return orderNumber.getText();
	}
	
}
