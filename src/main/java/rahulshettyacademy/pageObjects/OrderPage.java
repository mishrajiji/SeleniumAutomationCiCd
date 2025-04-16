package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="table.table-hover tbody td:nth-child(3)")
	List<WebElement> orderProducts;
	
	public boolean isProductNamePresentInOrders(String productName) {
		return orderProducts.stream()
				.anyMatch(orderProducts->orderProducts.getText().equalsIgnoreCase(productName));
		
	}

}
