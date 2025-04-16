package rahulsheetyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;
import rahulshettyacademy.pageObjects.ThankYouPage;

public class SubmitOrderTest extends BaseTest{
		
		@Test(dataProvider="getData", groups= {"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
	
		//String productName = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = landingPageObject.loginApplication(input.get("email"), input.get("pwd"));
		
		
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
				
		Assert.assertTrue(cartPage.isProductNamePresentInCart(input.get("productName")));
		
		CheckoutPage checkoutPage = cartPage.checkout();
		
		checkoutPage.selectDrpDwnOption("india");
		ThankYouPage thankYouPage = checkoutPage.placeOrder();
		
		Assert.assertTrue(thankYouPage.getConfirmationText().equalsIgnoreCase("Thankyou for the order."));

		}
		
		@Test(dependsOnMethods= {"submitOrder"})
		public void orderHistoryTest() throws InterruptedException {
			
			String productName = "ZARA COAT 3";
			
			ProductCatalogue productCatalogue = landingPageObject.loginApplication("mishra.gaurav19@gmail.com", "Gaurav@123");
			
			OrderPage orderPage = productCatalogue.goToOrderPage();
			
			Assert.assertTrue(orderPage.isProductNamePresentInOrders(productName));

		}
		
		@DataProvider
		public Object[][] getData() throws IOException {
			
			List<HashMap<String, String>> data = getJsonDataToHmap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
			
			//HashMap<Object, Object> hmap = new HashMap<Object,Object>();
			//hmap.put("email", "mishra.gaurav19@gmail.com");
			//hmap.put("pwd", "Gaurav@123");
			//hmap.put("productName", "ZARA COAT 3");
			
			//HashMap<Object, Object> hmap1 = new HashMap<Object,Object>();
			//hmap.put("email", "sharma.gaurav19@gmail.com");
			//hmap.put("pwd", "Gaurav@123");
			//hmap.put("productName", "ADIDAS ORIGINAL");
			
			return new Object[][] {
				{data.get(0)},
				{data.get(1)}};

			
			//return new Object[][] {
				//{"sharma.gaurav19@gmail.com","",""},
				//{"sharma.gaurav19@gmail.com","Gaurav@123","ADIDAS ORIGINAL"}};

		}
		


	}


