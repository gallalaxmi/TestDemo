package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	 public HomePage(WebDriver driver) {
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(how = How.XPATH, using = "//a[@title='Wishlist']")
	    public WebElement wishList;
	    
	    @FindBy(how = How.XPATH, using = "//a[text()='Accept all']")
	    public WebElement acceptAll;

	    @FindBy(how = How.XPATH, using = "//a[@title='Cart']")
	    public WebElement cart;
	    
	    @FindBy(how = How.XPATH, using = "//a[@title='Shop']")
	    public WebElement shop;
	    
	    public void clickWishList()
	    {
	    	wishList.click();
	    }
	    
	    public void clickShop()
	    {
	    	shop.click();
	    }
	    
	    public void clickCart()
	    {
	    	cart.click();
	    }
	    
	    public void acceptAll()
	    {
	    	acceptAll.click();
	    }
	    
//	    @FindBy(how = How.XPATH, using = "//*[a[text()='Add to cart']]//span[text()='Add to wishlist']")
//	    public WebElement addToWishlist;

//	    public void Login(String userName, String password)
//	    {
//	        txtUserName.sendKeys(userName);
//	        txtPassword.sendKeys(password);
//	    }
	    
//	public String addToWishlist = "//*[a[text()='Add to cart']]//span[text()='Add to wishlist']";
//	public String wishList = "//a[@title='Wishlist']";
//	public String cart = "//a[@title='Cart']";

}
