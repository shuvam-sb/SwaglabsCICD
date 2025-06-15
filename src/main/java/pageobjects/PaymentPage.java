package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
	private WebDriver driver;
	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='inventory_item_name']")
	List<WebElement> selectedItems;
	@FindBy(xpath = "//button[@id='finish']")
	WebElement finishBtn;
	
	public Boolean confirmOrderItems(List<String> itemLists) {
		for (String item : itemLists) {
			Boolean flag =selectedItems.stream().anyMatch(product->product.getText().equalsIgnoreCase(item));
			if(!flag) {
				return false;
			}
		}
		return true;
	}
	public ConfirmationPage placeOrder() {
		finishBtn.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
		
	}
}
