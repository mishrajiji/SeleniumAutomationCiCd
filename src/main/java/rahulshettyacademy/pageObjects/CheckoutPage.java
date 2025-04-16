package rahulshettyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryDrpDwn;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement optionInDrpdwn;
	
	@FindBy(xpath="//a[contains(@class,'btnn')]")
	WebElement placeOrderButton;
	
	
	By optionsBoxBy = By.cssSelector("section.ta-results");
	
	public void enterTextInDrpDwn(String country) {
		Actions action = new Actions(driver);
		action.sendKeys(countryDrpDwn, country)
		.build().perform();
		waitForElementToAppear(optionsBoxBy);
	}
	
	public void selectDrpDwnOption(String country) throws InterruptedException {
		enterTextInDrpDwn(country);
		optionInDrpdwn.click();
		Thread.sleep(5000);

	}
	public ThankYouPage placeOrder() {
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(975,675)");
		placeOrderButton.click();
		return new ThankYouPage(driver);
	}


}
