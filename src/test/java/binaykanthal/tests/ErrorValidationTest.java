package binaykanthal.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import binaykanthal.baseTest.BaseTest;
import binaykanthal.pageobjects.CartPage;
import binaykanthal.pageobjects.ProductPage;
import binaykanthal.retryAnalyzer.RetryAnalyzer;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer=RetryAnalyzer.class)
	public void loginErrorValidation() {
		String email = "binaykanthal@gmail.com";
		String password = "passWord1";
		loginPage.loginApp(email, password);
		String actualMessage = loginPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", actualMessage);
	}

	@Test(groups = { "ErrorHandling" })
	public void productErrorValidation() {
		String productName = "ADIDAS ORIGINAL";

		ProductPage productPage = loginPage.loginApp(prop.getProperty("email1"), prop.getProperty("password1")); // ProductPage
		List<WebElement> elements = productPage.getProductList();
		WebElement product = productPage.getProductByName(elements, productName);
		productPage.addProduct(product);

		CartPage cartPage = productPage.goToCartPage();// CartPage
		Boolean match = cartPage.verifyProduct("ADIDAS ORIGINAL");
		Assert.assertTrue(match);

	}

}
