package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyWishListPage {

	public MyWishListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//h2[text()='My wishlist']")
    public WebElement pageTitle;

    @FindBy(how = How.XPATH, using = "//table[contains(@class,'wishlist_table')]")
    public WebElement wishlistTable;
    
    public void verifyWishListTable() {
    	wishlistTable.isDisplayed();
    }
}
