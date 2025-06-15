package winnerxd;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import winnerxd.pageobjects.CartPage;
import winnerxd.pageobjects.CheckOutPage;
import winnerxd.pageobjects.ConfirmationPage;
import winnerxd.pageobjects.LoginPage;
import winnerxd.pageobjects.PaymentPage;
import winnerxd.pageobjects.ProductCatalogue;

public class StandAlone {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.saucedemo.com/");
		//Login
		LoginPage lp  = new LoginPage(driver);
		lp.performLogin();
		
		List<String> itemLists = Arrays.asList("Sauce Labs Backpack","Sauce Labs Bike Light");
		ProductCatalogue pc = new ProductCatalogue(driver);
		pc.addProductToCartByName(itemLists);
		
		CartPage cp = pc.gotoCartpage();
		Boolean flag = cp.verifyCartItems(itemLists);
		Assert.assertTrue(flag);

		CheckOutPage cop = cp.gotoCheckOutPage();
		cop.provideUserDetails("Shuvam", "Basak", "6700998");
		
		PaymentPage pp = cop.gotoPaymentPage();
		Boolean finalItemsFlag =  pp.confirmOrderItems(itemLists);
		Assert.assertTrue(finalItemsFlag);
		
		
		ConfirmationPage confiramtionPage =  pp.placeOrder();
		String confirmMsg =   confiramtionPage.getConfiramtionMessage();
		Assert.assertEquals(confirmMsg,"Thank you for your order!");

		
		Thread.sleep(3000);
		driver.quit();

	}

}
