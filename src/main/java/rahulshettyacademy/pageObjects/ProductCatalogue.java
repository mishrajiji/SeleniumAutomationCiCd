package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

	
	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> products;
	
	By productsBy =  By.xpath("//div[@class='card-body']");
	By addToCartBy = By.xpath("//button[text()=' Add To Cart']");
	By toastMessageBy = By.id("toast-container");
	By spinnerBy = By.xpath("//div[contains(@class,'ngx-spinner')]");
	

	public List<WebElement> getProductList() {
		// TODO Auto-generated method stub
		waitForElementsToAppear(productsBy);
		return products;

	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream()
				.filter(produ->produ.findElement(By.xpath("//h5")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;
				
	}


	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCartBy).click();
		waitForElementToAppear(toastMessageBy);
		waitForElementToDisappear(spinnerBy);
		Thread.sleep(5000);
		
	}
	
		


}
