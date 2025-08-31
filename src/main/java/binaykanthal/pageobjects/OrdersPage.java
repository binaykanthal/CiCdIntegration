package binaykanthal.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import binaykanthal.basePage.BasePage;

public class OrdersPage extends BasePage {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css= "tbody tr:nth-child(1) th")
	private WebElement lastOrderNumber;
	
	@FindBy(css= "tr td:nth-child(3)")
	private List<WebElement> ordersName;
	
	public Boolean verifyOrderDisplayed(String product) {
		return ordersName.stream().anyMatch(s->s.getText().equalsIgnoreCase(product));
	}
	
	public String getLastOrderNumber() {
		return lastOrderNumber.getText();
	}

}
