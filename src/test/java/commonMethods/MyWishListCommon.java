package commonMethods;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.DriverUtil;

public class MyWishListCommon {
	protected WebDriver driver = DriverUtil.getDriver("chrome");

	public void verifyWishListCount(int count) {
		int size = driver.findElements(By.xpath("//table[contains(@class,'wishlist_table')]/tbody/tr")).size();
		assertTrue("WishList table count is NOT matching", size == count);
	}
	
	public int getLowestPriceProduct() {
		int value = 0;
		int totalRows = driver.findElements(By.xpath("//table[contains(@class,'wishlist_table')]/tbody/tr")).size();
		for(int row = 1; row <= totalRows; row++) {
			int unitPrices = driver.findElements(By.xpath("//table[contains(@class,'wishlist_table')]/tbody/tr["+ row +"]/td[4]//bdi")).size();
			if(unitPrices > 1) {
				String text = driver.findElement(By.xpath("(//table[contains(@class,'wishlist_table')]/tbody/tr["+ row +"]/td[4]//bdi)[2]")).getText();
				value = Integer.parseInt(text.trim().replace('£', '0'));
			}
				
		}
		
//		int value = 0;
//		int index = 1;
//		List<WebElement> lists = driver.findElements(By.xpath("//*[contains(@id,\"yith-wcwl-row\")]/td[4]//span/bdi"));
//		int smallest = Integer.parseInt(lists.get(0).getText().trim().replace('£', '0'));
//		for(WebElement list : lists) {
//			value = Integer.parseInt(list.getText().trim().replace('£', '0'));
//			index++;
//			if (value < smallest) {
//			      smallest = value;
//			   }
//		}
		return value;
	}
}
