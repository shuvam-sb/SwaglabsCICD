package winnerxd.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import winnerxd.pageobjects.LoginPage;

public class BaseTest {
	WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/winnerxd/resources/GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	@BeforeMethod
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	
	}
	
	
	/*
	 * 
	 * public List<HashMap<String, String>> getJsonDatatoMap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		// List<Map<String,String>> list = mapper.readValue(jsonContent, List.class);
		// List<Map<String,String>> list = mapper.readValue(jsonContent, new
		// TypeReference<List<Map<String, String>>());
		List<HashMap<String, String>> dataList = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return dataList;
	}
	 * */
	public List<HashMap<String, Object>> getJsonDataToMap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, Object>> dataList = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, Object>>>() {
				});
		return dataList;

	}
	
	@AfterMethod (alwaysRun = true)
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
	
}
