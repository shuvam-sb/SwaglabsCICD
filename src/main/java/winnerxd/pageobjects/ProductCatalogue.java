package winnerxd.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import winnerxd.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
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

	String productImgLink;
	List<String> linkList;
	public List<String> viewProductByImage(String item) {
		linkList = new ArrayList<>();
			products.stream().filter(product -> product.findElement(By.xpath(".//div[@class='inventory_item_name ']"))
					.getText().equalsIgnoreCase(item)).findFirst().ifPresent(product -> {
						// Capture the <a> link (href) before clicking
						String linkProductElement = product.findElement(By.xpath(".//div[@class='inventory_item_img']//a//img")).getAttribute("src");
						
						linkList.add(linkProductElement);
						
						// Now click the link
						product.findElement(By.xpath(".//div[@class='inventory_item_img']//a")).click();
					});
		
		ProductInfoPage pInfo = new ProductInfoPage(driver);
		String productInfoText = pInfo.checkImageLink(productImgLink);
		linkList.add(productInfoText);
		return linkList;
	}

}
