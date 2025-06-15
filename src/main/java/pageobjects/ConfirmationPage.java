package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	private WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//h2[@class='complete-header']")
	WebElement confirmMsg;
	
	public String getConfiramtionMessage() {
		waitForWebElementToAppear(confirmMsg);
		return confirmMsg.getText();
	}

}
