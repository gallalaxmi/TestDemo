package commonMethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Base.DriverUtil;


public class HomeCommon {
	protected WebDriver driver = DriverUtil.getDriver("chrome");
	
	public void addProductsToWIshList(int count) throws InterruptedException {
		List<WebElement> lists = driver.findElements(By.xpath("//*[a[text()='Add to cart']]//a[@data-title='Add to wishlist']"));
		System.out.println("Galla"+lists.size());
		for(int i = 1; i <= count; i++) {
			driver.findElement(By.xpath("//*[a[text()='Add to cart']]//a[@data-title='Add to wishlist']")).click();
			Thread.sleep(2000);
			System.out.println("Galla"+i);
		}
    }

}
