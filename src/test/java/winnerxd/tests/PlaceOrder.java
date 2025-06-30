package winnerxd.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import winnerxd.pageobjects.CartPage;
import winnerxd.pageobjects.CheckOutPage;
import winnerxd.pageobjects.ConfirmationPage;
import winnerxd.pageobjects.LoginPage;
import winnerxd.pageobjects.PaymentPage;
import winnerxd.pageobjects.ProductCatalogue;
import winnerxd.testcomponents.BaseTest;

public class PlaceOrder extends BaseTest {

	// TODO Auto-generated method stub
	@Test(dataProvider = "getData")
	public void SubmitOrder(HashMap<String, Object> input) throws InterruptedException {
		// Login
		ProductCatalogue pc = loginPage.performLogin((String)input.get("email"),(String)input.get("password"));
		pc.addProductToCartByName((List<String>) input.get("product"));

		CartPage cp = pc.gotoCartpage();
		Boolean flag = cp.verifyCartItems((List<String>) input.get("product"));
		Assert.assertTrue(flag);

		CheckOutPage cop = cp.gotoCheckOutPage();
		cop.provideUserDetails((String) input.get("firstName"), (String) input.get("lastName"),
				(String) input.get("zipCode"));

		PaymentPage pp = cop.gotoPaymentPage();
		Boolean finalItemsFlag = pp.confirmOrderItems((List<String>) input.get("product"));
		Assert.assertTrue(finalItemsFlag);

		ConfirmationPage confiramtionPage = pp.placeOrder();
		String confirmMsg = confiramtionPage.getConfiramtionMessage();
		Assert.assertEquals(confirmMsg, "Thank you for your order!");

	}

	@DataProvider
	public Object[] getData() throws IOException {
		/*
		 * List<HashMap<String, Object>> data = getJsonDataToMap(
		 * System.getProperty("user.dir") +
		 * "/src/test/java/winnerxd/data/purchaseItem.json"); return new Object[] { {
		 * data.get(0) }, { data.get(1) } };
		 */
		List<HashMap<String, Object>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "/src/test/java/winnerxd/data/purchaseItem.json");

		Object[][] result = new Object[data.size()][1];

		for (int i = 0; i < data.size(); i++) {
			result[i][0] = data.get(i); // wrap each HashMap into one Object[] row
		}

		return result;
	}

}
