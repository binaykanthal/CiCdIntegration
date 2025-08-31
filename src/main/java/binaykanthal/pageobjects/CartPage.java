package binaykanthal.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import binaykanthal.basePage.BasePage;

public class CartPage extends BasePage{
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartsProduct;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	private WebElement checkoutButton;
	
	public Boolean verifyProduct(String productName) {
		Boolean match = cartsProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckoutPage() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	
}
