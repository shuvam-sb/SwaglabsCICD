package winnerxd.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import winnerxd.abstractcomponents.AbstractComponent;

public class ProductInfoPage extends AbstractComponent {
	WebDriver driver;


	public ProductInfoPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}
	@FindBy(xpath = "//div[@class='inventory_details_container']//div[@class='inventory_details_img_container']//img")
	WebElement itemImageLinkText;
	
	public String  checkImageLink(String productImageLink) {
		return itemImageLinkText.getAttribute("src");
	}
}
