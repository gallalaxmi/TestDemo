package stepDefinitions;

import org.openqa.selenium.WebDriver;

import Base.DriverUtil;
import commonMethods.MyWishListCommon;
import io.cucumber.java.en.Then;
import pages.HomePage;
import pages.MyWishListPage;

public class MyWishList {
	
	protected WebDriver driver = DriverUtil.getDriver("chrome");
	HomePage homePage = new HomePage(driver);
	MyWishListPage myWishListPage = new MyWishListPage(driver);
	MyWishListCommon myWishListCommon = new MyWishListCommon();
	
	@Then("^I view my wishlist table$")
	public void viewMyWishList() {
		homePage.clickWishList();
		myWishListPage.verifyPageTitle();
		myWishListPage.verifyWishListTable();
	}
	
	@Then("^I found total (\\d+) selected items in my wish list$")
	public void checkItems(int count) {
		myWishListCommon.verifyWishListCount(count);
	}
	
	@Then("^I search for lowest price product$")
	public void searchLowPriceProduct() {
		myWishListCommon.getLowestPriceProduct();
	}
	
	@Then("^I am able to add the lowest price item to my cart$")
	public void verifyLowPriceProduct() {

	}

}
