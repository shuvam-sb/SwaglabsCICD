package winnerxd.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import winnerxd.pageobjects.CartPage;
import winnerxd.pageobjects.ProductCatalogue;
import winnerxd.testcomponents.BaseTest;

public class ErrorValidation extends BaseTest {
	@Test
	public void loginErrorValidation() {
		loginPage.performLogin("locked_out_user", "secret_sauce");
		try {
			String errMsg = loginPage.getErrorMessage();
			Assert.assertEquals(errMsg, "Epic sadface: Sorry, this user has been locked out.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Assert.fail("Exception while fetching error message : " + e.getMessage());
		}
	}

	@Test
	public void productErrorValidation() {
		ProductCatalogue pc = loginPage.performLogin("standard_user", "secret_sauce");
		pc.addProductToCartByName(Arrays.asList("Sauce Labs Backpack")); 
		CartPage cp = pc.gotoCartpage();
		//verify wrong cart items
		//Pass if selected abc and in cart abcxyx
		Boolean flag = cp.verifyCartItems(Arrays.asList("Sauce Labs Backpack xyx"));
		Assert.assertFalse(flag);
	}



}
