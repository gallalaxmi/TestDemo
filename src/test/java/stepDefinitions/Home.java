package stepDefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import Base.DriverUtil;
import commonMethods.HomeCommon;
import io.cucumber.java.en.Then;
import pages.HomePage;

public class Home {
	protected WebDriver driver = DriverUtil.getDriver("chrome");
	HomePage homePage = new HomePage(driver);
	HomeCommon homeCommon = new HomeCommon();
	
	@Then("^I open test script demo site$")
	public void openURL() {
		driver.get("https://testscriptdemo.com/");
		homePage.acceptAll();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	@Then("^I add (\\d+) different products to my wish list$")
	public void addProducts(int count) throws InterruptedException {
		homePage.clickShop();
		homePage.addProductsToWIshList(count);
	}
	
	
}
