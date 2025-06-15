package winnerxd.abstractcomponents;

import java.awt.print.PageFormat;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import winnerxd.pageobjects.CartPage;
public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	WebElement cartBtn;
	
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait  = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	public CartPage gotoCartpage() {
		cartBtn.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
}
