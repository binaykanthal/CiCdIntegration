package binaykanthal.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import binaykanthal.baseTest.BaseTest;
import binaykanthal.pageobjects.CartPage;
import binaykanthal.pageobjects.CheckoutPage;
import binaykanthal.pageobjects.ConfirmPage;
import binaykanthal.pageobjects.LoginPage;
import binaykanthal.pageobjects.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {

	public LoginPage loginPage;
	public ProductPage productPage;
	public ConfirmPage confirmPage;
	String orderNumber;

	@Given("I landed on ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
		loginPage = launchApplication();
	}

	@Given("^I logged in with username (.+) and password (.+)$")
	public void i_logged_in_with_username_and_password(String username, String password) {
		productPage = loginPage.loginApp(username, password);
	}

	@When("^I add product (.+) from cart$")
	public void i_add_product_from_cart(String productName) {
		List<WebElement> elements = productPage.getProductList();
		WebElement product = productPage.getProductByName(elements, productName);
		productPage.addProduct(product);
	}

	@When("^checkout (.+) and sumbit the order$")
	public void checkout_and_sumbit_the_order(String productName) {
		CartPage cartPage = productPage.goToCartPage();
		Boolean match = cartPage.verifyProduct(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry("ind", "India");
		confirmPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string) {
		String text = confirmPage.getConfirmationText();
		orderNumber = confirmPage.getOrderNumber().replaceAll("\\|", "").strip();
		Assert.assertTrue(text.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("I checked for error message {string}")
	public void i_checked_for_erro_message (String string) {
		String actualMessage = loginPage.getErrorMessage();
		Assert.assertEquals(string, actualMessage);
		driver.close();
	}

}
