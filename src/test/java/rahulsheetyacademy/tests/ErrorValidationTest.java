package rahulsheetyacademy.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;
import rahulshettyacademy.pageObjects.ThankYouPage;

public class ErrorValidationTest extends BaseTest{
		
		@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
		public void loginErrorValidation() throws InterruptedException, IOException {
	
		landingPageObject.loginApplication("mishraa.gaurav19@gmail.com", "Gaurav@1234");
		Assert.assertEquals("Incorrect email or password.", landingPageObject.getErrorMessage());
		}
		
		@Test
		public void productErrorValidation() throws InterruptedException, IOException {
	
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = landingPageObject.loginApplication("mishra.gaurav19@gmail.com", "Gaurav@123");
		
		
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
				
		Assert.assertFalse(cartPage.isProductNamePresentInCart("ZARA COAT 33"));
		
		}

		
		


	}


