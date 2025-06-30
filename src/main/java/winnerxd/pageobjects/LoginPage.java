package winnerxd.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='login_credentials']")
	WebElement userNameDiv;
	@FindBy(xpath = "//div[@class='login_password']")
	WebElement userPassDiv;
	@FindBy(xpath = "//input[@id='user-name']")
	WebElement userName;
	@FindBy(xpath = "//input[@id='password']")
	WebElement userPass;
	@FindBy(xpath = "//input[@id='login-button']")
	WebElement loginBtn;
	@FindBy(xpath = "//div[@class='error-message-container error']//h3")
	WebElement loginErrorMessage;

	public List<String> fetchCredentials() {
		List<String> userNames = Arrays.stream(userNameDiv.getText().split("\n")).skip(1).collect(Collectors.toList());
		List<String> userPasswords = Arrays.stream(userPassDiv.getText().split("\n")).skip(1)
				.collect(Collectors.toList());
		return Arrays.asList(userNames.get(0), userPasswords.get(0));

	}

	public ProductCatalogue performLogin(String username, String password) {
		userName.sendKeys(username);
		userPass.sendKeys(password);
		loginBtn.click();
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc;
	}
	
	public String getErrorMessage() {
		return loginErrorMessage.getText();
	}

	public void goTo() {
		// TODO Auto-generated method stub
		driver.get("https://www.saucedemo.com/");
	}

}
