package binaykanthal.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import binaykanthal.baseTest.BaseTest;
import binaykanthal.pageobjects.CartPage;
import binaykanthal.pageobjects.CheckoutPage;
import binaykanthal.pageobjects.ConfirmPage;
import binaykanthal.pageobjects.OrdersPage;
import binaykanthal.pageobjects.ProductPage;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";
	String orderNumber;

	@Test
	public void submitTest() throws IOException{

		ProductPage productPage = loginPage.loginApp(prop.getProperty("email"), prop.getProperty("password")); // ProductPage
		List<WebElement> elements = productPage.getProductList();
		WebElement product = productPage.getProductByName(elements, prop.getProperty("productName"));
		productPage.addProduct(product);

		CartPage cartPage = productPage.goToCartPage();// CartPage
		Boolean match = cartPage.verifyProduct(prop.getProperty("productName"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();// CheckoutPage
		checkoutPage.selectCountry("ind", prop.getProperty("country"));

		ConfirmPage confirmPage = checkoutPage.submitOrder(); // ConfirmPage
		String text = confirmPage.getConfirmationText();
		orderNumber = confirmPage.getOrderNumber().replaceAll("\\|", "").strip();
		Assert.assertTrue(text.equalsIgnoreCase(prop.getProperty("confirmationMessage")));
	}

	@Test(dependsOnMethods = { "submitTest" })
	public void verifyOrderHistory() {
		ProductPage productPage = loginPage.loginApp(prop.getProperty("email"), prop.getProperty("password"));
		OrdersPage orderPage = productPage.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplayed(productName));
		Assert.assertEquals(orderPage.getLastOrderNumber(), orderNumber);
	}

}
