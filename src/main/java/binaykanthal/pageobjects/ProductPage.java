package binaykanthal.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import binaykanthal.basePage.BasePage;

public class ProductPage extends BasePage{
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}

	@FindBy(css = ".col-lg-4")
	private List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	private WebElement spinner;
	
	private By productsBy = By.cssSelector(".col-lg-4");
	private By productname = By.cssSelector("h5 b");
	private By addToCart = By.cssSelector("button.w-10");
	private By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(List<WebElement> element,String productName) {
		return element.stream().filter(s->s.findElement(productname)
				.getText().contentEquals(productName)).findFirst().orElse(null);
		
	}
	
	public void addProduct(WebElement ls) {
		ls.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
}
