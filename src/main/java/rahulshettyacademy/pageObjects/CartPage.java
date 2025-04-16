package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	
	
	public boolean isProductNamePresentInCart(String productName) {
		return cartProducts.stream()
				.anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
	}
	
	public CheckoutPage checkout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}


}
