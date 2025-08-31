package binaykanthal.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		String productName = "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("binaykanthal@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("passWord1*");
		driver.findElement(By.cssSelector("#login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement> elements = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement ls= elements.stream().filter(s->s.findElement(By.cssSelector("h5 b"))
				.getText().contentEquals(productName)).findFirst().orElse(null);
		ls.findElement(By.cssSelector("button.w-10")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		List<WebElement> cartsProduct = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartsProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		driver.findElement(By.cssSelector("input[placeholder]")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		List<WebElement> countryList = driver.findElements(By.cssSelector(" .list-group.ta-results button span"));
		countryList.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		driver.findElement(By.cssSelector(".action__submit")).click();
		String text = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

}
