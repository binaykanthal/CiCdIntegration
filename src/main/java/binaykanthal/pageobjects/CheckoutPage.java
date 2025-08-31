package binaykanthal.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import binaykanthal.basePage.BasePage;

public class CheckoutPage extends BasePage{
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}

	@FindBy(css = ".list-group.ta-results button span")
	private List<WebElement> countryList;
	
	@FindBy(css = "input[placeholder]")
	private WebElement countryPlaceholder;
	
	@FindBy(css = ".action__submit")
	private WebElement placeOrderButton;
	
	private By dropdownBox = By.cssSelector(".ta-results");
	private By orderButton = By.cssSelector(".action__submit");
	
	public void selectCountry(String initial, String countryName) {
		countryPlaceholder.sendKeys(initial);
		waitForElementToAppear(dropdownBox);
		countryList.stream().filter(s->s.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null).click();
		
	}
	
	public ConfirmPage submitOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",placeOrderButton);
		waitForElementToAppear(orderButton);
		placeOrderButton.click();
		return new ConfirmPage(driver);
	}
	
	
}
