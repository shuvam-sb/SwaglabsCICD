package winnerxd.tests;

import org.testng.annotations.Test;

import java.util.List;

import org.testng.Assert;

import winnerxd.pageobjects.ProductCatalogue;
import winnerxd.testcomponents.BaseTest;

public class VisualUITest extends BaseTest {
	
	@Test
	public void testVisualElements() {
		//yes it will be not equal as for visoual user pitbull!=backpack (link)
		ProductCatalogue pc = loginPage.performLogin("visual_user", "secret_sauce");
		List <String> linkList = pc.viewProductByImage("Sauce Labs Backpack");
//		for(String s:linkList) {
//			System.out.println(s);
//		}
		

	
		
	}
}
