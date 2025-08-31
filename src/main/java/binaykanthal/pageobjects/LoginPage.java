package binaykanthal.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import binaykanthal.basePage.BasePage;

public class LoginPage extends BasePage {
	WebDriver driver;
	String url = "https://rahulshettyacademy.com/client";

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // pageFactory initMethod will initialize all element
	}

	// PageFactory
	@FindBy(css = "#userEmail")
	private WebElement userEmail;

	@FindBy(css = "#userPassword")
	private WebElement passWord;

	@FindBy(css = "#login")
	private WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	private WebElement errorMessage;

	public ProductPage loginApp(String email, String password) {
		userEmail.sendKeys(email);
		passWord.sendKeys(password);
		waitForElementTobeClickable(submit);
		submit.click();
		return new ProductPage(driver);
	}

	public void goTo() {
		driver.get(url);
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
