package binaykanthal.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import binaykanthal.baseTest.BaseTest;
import binaykanthal.pageobjects.CartPage;
import binaykanthal.pageobjects.CheckoutPage;
import binaykanthal.pageobjects.ConfirmPage;
import binaykanthal.pageobjects.ProductPage;

public class DataProviderTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void hashMapDataValidation(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductPage productPage = loginPage.loginApp(input.get("email"), input.get("password"));
		List<WebElement> elements = productPage.getProductList();
		WebElement product = productPage.getProductByName(elements, prop.getProperty("productName"));
		productPage.addProduct(product);
		CartPage cartPage = productPage.goToCartPage();
		Boolean match = cartPage.verifyProduct(prop.getProperty("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry("ind", prop.getProperty("country"));
		ConfirmPage confirmPage = checkoutPage.submitOrder();
		String text = confirmPage.getConfirmationText();
		Assert.assertTrue(text.equalsIgnoreCase(prop.getProperty("confirmationMessage")));
	}

	@Test(dataProvider="getDataFromJson", groups = { "Json" })
	public void jsonDataValidation(HashMap<String, String> input) throws IOException {
		ProductPage productPage = loginPage.loginApp(input.get("email"), input.get("password")); 
		List<WebElement> elements = productPage.getProductList();
		WebElement product = productPage.getProductByName(elements, input.get("productName"));
		productPage.addProduct(product);

		CartPage cartPage = productPage.goToCartPage();
		Boolean match = cartPage.verifyProduct(input.get("productName"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry("ind", prop.getProperty("country"));

		ConfirmPage confirmPage = checkoutPage.submitOrder(); 
		String text = confirmPage.getConfirmationText();
		Assert.assertTrue(text.equalsIgnoreCase(prop.getProperty("confirmationMessage")));
	}

	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "binaykanthal@gmail.com");
		map.put("password", "passWord1*");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "binaykanthal2995@gmail.com");
		map1.put("password", "Binay12345$");

		return new Object[][] { { map }, { map1 } };
	}

	@DataProvider
	public Object[][] getDataFromJson() throws IOException {
		List<HashMap<String, String>> data = getJsonDataMap(System.getProperty("user.dir")
				+ "/src/test/java/binaykanthal/resources/purchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

}
