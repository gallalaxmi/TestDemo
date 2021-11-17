package stepDefinitions;

import org.openqa.selenium.WebDriver;

import Base.DriverUtil;
import io.cucumber.java.en.Then;

public class Cart {
protected WebDriver driver = DriverUtil.getDriver("chrome");
	
	@Then("^I am able to verify the item in my cart$")
	public void viewCartProduct() {

	}
}
