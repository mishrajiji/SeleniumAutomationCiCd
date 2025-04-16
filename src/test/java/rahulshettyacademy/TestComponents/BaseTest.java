package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPageObject;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			//WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("Headless")) {
		        options.addArguments("--headless"); // Run in headless mode
		        options.addArguments("--window-size=1440,900"); // Set window size if needed
			}
			
			driver = new ChromeDriver(options);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(1440,900));
		return driver;		
	}
	
	public List<HashMap<String, String>> getJsonDataToHmap(String filePath) throws IOException {
		
		//read json
		File file = new File(filePath);
		String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		
		//string to hashmap jackson dependency
        // Jackson ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		
        // Read the JSON array into a List of HashMaps
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});

        return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot screenshot = (TakesScreenshot) driver;

        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

        String destinationFilePath = System.getProperty("user.dir")+"//reports//"+ testCaseName +".png";
        File destinationFile = new File(destinationFilePath);

        FileUtils.copyFile(screenshotFile, destinationFile);
        
        return destinationFilePath;

	}

	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPageObject = new LandingPage(driver);
		landingPageObject.goTo();
		return landingPageObject;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

}
