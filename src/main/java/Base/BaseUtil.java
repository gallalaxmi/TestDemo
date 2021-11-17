package Base;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.timeout.TimeoutException;

public class BaseUtil {
	protected WebDriver driver;
	FluentWait<WebDriver> wait;
	public String timeout = "600000";
	public int timeOut = 30;
	
	public BaseUtil(String browser) {
		if (driver == null) {
		driver = DriverUtil.getDriver(browser);
		}
	}
	
	public WebElement locator(String locObj) {
		WebElement query = null;
		By by;
		by = locatorBy(locObj);
		try {
			try {
				if (!driver.findElements(by).isEmpty()) {
					query = driver.findElement(by);
				}
			} catch (NoSuchElementException te) {
				System.out.println(locObj + " No Such Element");
			}
		} catch (TimeoutException te) {
			query = null;
			return query;
		}
		return query;
	}

	public By locatorBy(String locObj) {
		By by = null;
		try {
			if (locObj.startsWith("//")) {
				by = By.xpath(locObj);
			} else if (locObj.startsWith("link")) {
				String[] locObj1 = locObj.split("link=");
				by = By.linkText(locObj1[1]);
			} else if (locObj.startsWith("css")) {
				String[] locObj1 = locObj.split("css=");
				by = By.cssSelector(locObj1[1]);
			} else if (locObj.startsWith("id")) {
				String[] locObj1 = locObj.split("id=");
				by = By.id(locObj1[1]);
			} else if (locObj.startsWith("name")) {
				String[] locObj1 = locObj.split("name=");
				by = By.name(locObj1[1]);
			} else if (locObj.startsWith("label")) {
				String[] locObj1 = locObj.split("label=");
				by = By.name(locObj1[1]);
			} else {
				by = By.id(locObj);
			}
		} catch (NoSuchElementException e) {
		}
		return by;
	}
	
	public void removeAttribute() {
		List<WebElement> inputs = driver.findElements(By.tagName("input"));
		for (WebElement input : inputs) {
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", input);
		}
	}

	public void typeNonEditable(String obj1, String value) {
		removeAttribute();
		type(obj1, value);
	}

	public void typeNonEditable(WebElement obj1, String value) {
		removeAttribute();
		type(obj1, value);
	}

	public void type(String obj1, String value) {
		WebElement query1 = locator(obj1);
		query1.clear();
		query1.sendKeys(value);
	}

	public void type(WebElement obj1, String value) {
		obj1.clear();
		obj1.sendKeys(value);
	}

	public Integer getElementsSize(String tableNameLocator) {
		List<WebElement> noOfRows = findElements(tableNameLocator);
		return noOfRows.size();
	}

	public List<WebElement> findElements(String locObj) {
		List<WebElement> query = null;
		By by;
		try {
			by = locatorBy(locObj);
			try {
				new WebDriverWait(driver, Duration.ofSeconds(timeOut)).until(ExpectedConditions.visibilityOfElementLocated(by));
				query = driver.findElements(by);
			} catch (TimeoutException te) {
				query = null;
				if (query == null) {
					by = By.name(locObj);
					new WebDriverWait(driver, Duration.ofSeconds(timeOut)).until(ExpectedConditions.visibilityOfElementLocated(by));
					query = driver.findElements(by);
				}
				return query;
			}
			if (query == null) {
				by = By.name(locObj);
				new WebDriverWait(driver, Duration.ofSeconds(timeOut)).until(ExpectedConditions.visibilityOfElementLocated(by));
				query = driver.findElements(by);
			}
		} catch (TimeoutException te) {
			query = null;
			return query;
		}
		return query;
	}

	public String[] getOptionsValues(String selectLocator) {
		int arrayondex = 0;
		List<WebElement> options = getOptions(selectLocator);
		String[] optionlist = new String[options.size() - 1];
		for (WebElement we : options) {
			optionlist[arrayondex++] = we.getText();
		}
		return optionlist;
	}

	public String[] getOptionsValues(WebElement selectLocator) {
		int arrayondex = 0;
		List<WebElement> options = getOptions(selectLocator);
		String[] optionlist = new String[options.size() - 1];
		for (WebElement we : options) {
			optionlist[arrayondex++] = we.getText();
		}
		return optionlist;
	}

	public int getOptionsSize(String selectLocator) {
		return getOptions(selectLocator).size();
	}

	public int getOptionsSize(WebElement selectLocator) {
		return getOptions(selectLocator).size();
	}

	public List<WebElement> getOptions(String selectLocator) {
		WebElement select = locator(selectLocator);
		Select dropDown = new Select(select);
		return dropDown.getOptions();
	}

	public List<WebElement> getOptions(WebElement selectLocator) {
		Select dropDown = new Select(selectLocator);
		return dropDown.getOptions();
	}
	
	public void clear(String obj1) {
		WebElement query1 = locator(obj1);
		query1.clear();
	}

	public void clear(WebElement obj1) {
		obj1.clear();
	}
	
	public void click(String obj) {
		waitForElementVisible(obj);
		WebElement query1 = locator(obj);
		try {
			query1.click();
		} catch (NoSuchWindowException e1) {
		} catch (InvalidElementStateException e) {
			query1.click();
		} catch (Exception e) {
		}
	}

	public void click(WebElement obj) {
		waitForElementVisible(obj);
		try {
			obj.click();
		} catch (NoSuchWindowException e1) {
		} catch (InvalidElementStateException e) {
			obj.click();
		} catch (Exception e) {
		}
	}
	
	public void waitForPageToLoad(String timeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}
	
	public void clickAndWait(String objectMap) {
		if (!isElementPresent(objectMap)) {
			waitForElementVisible(objectMap);
		}
		click(objectMap);
		waitForPageToLoad(timeout);
	}

	public void clickAndWait(WebElement objectMap) {
		if (!isElementPresent(objectMap)) {
			waitForElementVisible(objectMap);
		}
		click(objectMap);
		waitForPageToLoad(timeout);
	}
	
	public void waitForElementVisible(String element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locatorBy(element)));
		} catch (NoSuchWindowException e) {
		} catch (InvalidElementStateException e) {
		} catch (TimeoutException te) {
		} catch (NoSuchElementException e) {
		} catch (WebDriverException we) {
		}
	}

	public void waitForElementVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (NoSuchWindowException e) {
		} catch (InvalidElementStateException e) {
		} catch (TimeoutException te) {
		} catch (NoSuchElementException e) {
		} catch (WebDriverException we) {
		}
	}
	
	public void pause(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void waitForTextPresent(String text) {
		try {
			pause(2000);
			long timer = 0;
			while (isTextPresent(text) == false && timer < Long.valueOf(timeout)) {
				pause(500);
				timer += 5000;
			}
		} catch (NoSuchWindowException e) {
		}
	}

	public Alert waitforAlertPresent() {
		int i = 0;
		Alert alert = null;
		while (i++ < 30) {
			try {
				alert = driver.switchTo().alert();
				return alert;
			} catch (NoAlertPresentException e) {
				pause(1000);
				continue;
			}
		}
		return alert;
	}

	public void waitForFrameToLoad(String objLocator) {
		waitForElementVisible("id=" + objLocator);
	}

	public void waitForFrameToLoad(WebElement objLocator) {
		waitForElementVisible(objLocator);
	}

	public boolean isElementPresent(String obj) {
		boolean displayed = false;
		try {
			WebElement query1 = locator(obj);
			if (query1 != null) {
				displayed = true;
			}
		} catch (NoAlertPresentException e) {
		} catch (NoSuchElementException e) {
			displayed = false;
			return displayed;
		} catch (WebDriverException e) {
			displayed = false;
			return displayed;
		}
		return displayed;
	}

	public boolean isElementPresent(WebElement obj) {
		boolean displayed = false;
		try {
			if (obj != null) {
				displayed = true;
			}
		} catch (NoAlertPresentException e) {
		} catch (NoSuchElementException e) {
			displayed = false;
			return displayed;
		} catch (WebDriverException e) {
			displayed = false;
			return displayed;
		}
		return displayed;
	}

	public boolean isChecked(String obj) {
		boolean displayed = false;
		try {
			WebElement query1 = locator(obj);
			if (query1 != null) {
				boolean isChecked = query1.isSelected();
				displayed = isChecked;
			}
		} catch (NoSuchElementException e) {
			displayed = false;
			return displayed;
		}
		return displayed;
	}

	public boolean isChecked(WebElement obj) {
		boolean displayed = false;
		try {
			if (obj != null) {
				boolean isChecked = obj.isSelected();
				displayed = isChecked;
			}
		} catch (NoSuchElementException e) {
			displayed = false;
			return displayed;
		}
		return displayed;
	}
	
	public String getAttribute(String objLocator) {
		int atSign = objLocator.lastIndexOf("@");
		WebElement query1;
		String[] nameAndAttribute = new String[2];
		nameAndAttribute[0] = objLocator.substring(0, atSign);
		nameAndAttribute[1] = objLocator.substring(atSign + 1);
		query1 = locator(nameAndAttribute[0]);
		return query1.getAttribute(nameAndAttribute[1]);
	}

	public boolean isEditable(String obj) {
		boolean state = false;
		String readonly = getAttribute(obj + "@readonly");
		try {
			if (readonly.equals(true)) {
				state = false;
			}
		} catch (NullPointerException e) {
			readonly = "";
			readonly = getAttribute(obj + "@disabled");
			if (readonly.equals("") || readonly.equals(true)) {
				state = false;
			}
		}
		return state;
	}

	public boolean isEditable(WebElement obj) {
		boolean state = false;
		String readonly = getAttribute(obj, "readonly");
		try {
			if (readonly.equals(true)) {
				state = false;
			}
		} catch (NullPointerException e) {
			readonly = "";
			readonly = getAttribute(obj, "disabled");
			if (readonly.equals("") || readonly.equals(true)) {
				state = false;
			}
		}
		return state;
	}

	public boolean isDisabled(String obj) {
		boolean state = false;
		String readonly = getAttribute(obj + "@disabled");
		if (readonly.equals("") || readonly.equals(true)) {
			state = false;
		}
		return state;
	}

	public boolean isDisabled(WebElement obj) {
		boolean state = false;
		String readonly = getAttribute(obj, "disabled");
		if (readonly.equals("") || readonly.equals(true)) {
			state = false;
		}
		return state;
	}

	public boolean isVisible(String obj) {
		boolean displayed = false;
		try {
			WebElement query1 = locator(obj);
			if (query1 != null) {
				displayed = query1.isDisplayed();
			}
		} catch (NullPointerException e) {
			return false;
		}
		return displayed;
	}

	public boolean isVisible(WebElement obj) {
		boolean displayed = false;
		try {
			if (obj != null) {
				displayed = obj.isDisplayed();
			}
		} catch (NullPointerException e) {
			return false;
		}
		return displayed;
	}

	public boolean isTextPresent(String text) {
		boolean flag = false;
		try {
			flag = driver.findElement(By.xpath("//*[contains(.,'" + text + "')]")).isDisplayed();
		} catch (NoSuchElementException e) {
			return flag;
		} catch (NoSuchWindowException e) {
			return flag;
		}
		return flag;
	}
	
	public String getAttribute(WebElement objLocator, String attribute) {
		return objLocator.getAttribute(attribute);
	}

	public String getText(String objLocator) {
		WebElement query1 = locator(objLocator);
		String text = query1.getText().trim();
		return text;
	}

	public String getText(WebElement objLocator) {
		String text = objLocator.getText().trim();
		return text;
	}

	public void check(String objLocator) {
		try {
			WebElement query1 = locator(objLocator);
			if (!query1.isSelected()) {
				query1.click();
			}
		} catch (Exception e) {
		}
	}

	public void check(WebElement objLocator) {
		try {
			if (!objLocator.isSelected()) {
				objLocator.click();
			}
		} catch (Exception e) {
		}
	}

	public void uncheck(String objLocator) {
		try {
			WebElement query1 = locator(objLocator);
			if (query1.isSelected()) {
				query1.click();
			}
		} catch (Exception e) {
		}
	}

	public void uncheck(WebElement objLocator) {
		try {
			if (objLocator.isSelected()) {
				objLocator.click();
			}
		} catch (Exception e) {
		}
	}
}
