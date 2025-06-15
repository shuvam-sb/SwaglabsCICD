package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
	private WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='first-name']")
	WebElement firstNameEle;
	@FindBy(xpath = "//input[@id='last-name']")
	WebElement lastNameEle;
	@FindBy(xpath = "//input[@id='postal-code']")
	WebElement postalCode;
	@FindBy(xpath = "//input[@id='continue']")
	WebElement continueBtn;

	public void provideUserDetails(String fname, String lname, String zipCode) {
		firstNameEle.sendKeys(fname);
		lastNameEle.sendKeys(lname);
		postalCode.sendKeys(zipCode);
	}

	public PaymentPage gotoPaymentPage() {
		continueBtn.click();
		PaymentPage pp = new PaymentPage(driver);
		return pp;
	}

}
