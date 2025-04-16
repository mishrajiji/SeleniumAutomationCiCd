package rahulsheetyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.pageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		LandingPage landingPageObject = new LandingPage(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		JavascriptExecutor js =(JavascriptExecutor) driver;
		
		driver.findElement(By.id("userEmail")).sendKeys("mishra.gaurv19@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Gaurav@123");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//div[@class='card-body']"))));
		
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']"));
		//for(WebElement we: products) {
		//	
		//	String productName = we.findElement(By.xpath("//h5")).getText();
		//	
		//	if(productName.equalsIgnoreCase("ZARA COAT 3")|| productName.equalsIgnoreCase("IPHONE 13 PRO")) {
		//		
		//		we.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
		//		Thread.sleep(3000);
		//	}
		
		products.stream()
		.filter(prod->prod.findElement(By.xpath("//h5")).getText().equalsIgnoreCase(productName))
		.findFirst().orElse(null)
		.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'ngx-spinner')]")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class,'ngx-spinner')]"))));
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		
		//driver.findElements(By.xpath("//button[contains(@class,'ta-item')]"))
		//.stream().filter(we->we.getText().equalsIgnoreCase("india")).findFirst().orElse(null).click();
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'submit')]")));
		//driver.findElement(By.xpath("//a[contains(@class,'submit')]")).click();
		
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india")
		.build().perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("section.ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		Thread.sleep(5000);
		
		js.executeScript("window.scrollBy(950,650)");
		System.out.println(driver.findElement(By.xpath("//a[contains(@class,'btnn')]")).getLocation().getX());
		System.out.println(driver.findElement(By.xpath("//a[contains(@class,'btnn')]")).getLocation().getY());

		
		driver.findElement(By.xpath("//a[contains(@class,'btnn')]")).click();
		
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

		}
		


	}


