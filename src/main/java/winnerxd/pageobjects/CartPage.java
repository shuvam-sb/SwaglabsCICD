package winnerxd.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import winnerxd.abstractcomponents.AbstractComponent;


public class CartPage extends AbstractComponent{
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//div[@class='inventory_item_name']")
	List<WebElement> cartItems;
	@FindBy(xpath = "//button[@id='checkout']")
	WebElement checkOutBtn;
	
	public Boolean verifyCartItems(List<String> itemLists) {
		for (String item : itemLists) {
			Boolean flag =cartItems.stream().anyMatch(product->product.getText().equalsIgnoreCase(item));
			if(!flag) {
				return false;
			}
		}
		return true;
	}
	public CheckOutPage gotoCheckOutPage() {
		checkOutBtn.click();
		CheckOutPage cop = new CheckOutPage(driver);
		return cop;
	}

}
