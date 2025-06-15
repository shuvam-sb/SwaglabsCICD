package pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='inventory_item']")
	private List<WebElement> products;

	public void addProductToCartByName(List<String> itemLists) {
		for (String item : itemLists) {
			products.stream()
					.filter(product -> product.findElement(By.xpath(".//div[@class='inventory_item_name ']")).getText()
							.equalsIgnoreCase(item))
					.findFirst().ifPresent(product -> product.findElement(By.xpath(".//button")).click());
		}
	}

}
