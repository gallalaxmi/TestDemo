package Base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverUtil {
	
	public static long DEFAULT_WAIT = 20;
    protected static WebDriver driver;
    static DesiredCapabilities capabilities = new DesiredCapabilities();
    public int timeOut = 30;
    
    public static WebDriver getDriver(String browser) {
		if (driver != null) {
			return driver;
		}
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		if (browser.contains("ie")) {
			driver = createIEDriver();
		} else if (browser.contains("firefox")) {
			driver = createFirefoxDriver();
		} else if (browser.contains("chrome")) {
			driver = createChromeDriver();
		} else {
			System.out.println("Browser should be either of these IE, FF, Chrome");
			System.exit(0);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
    }
    
    public void browserInstance(String browser) throws IOException, InterruptedException {
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		if (browser.contains("ie")) {
			driver = createIEDriver();
		} else if (browser.contains("firefox")) {
			driver = createFirefoxDriver();
		} else if (browser.contains("chrome")) {
			driver = createChromeDriver();
		} else {
			System.out.println("Browser should be either of these IE, FF, Chrome");
			return;
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void windowMaximize() {
		driver.manage().window().maximize();
	}

	public static WebDriver createIEDriver() {
		InternetExplorerOptions options = new InternetExplorerOptions();
        options.setAcceptInsecureCerts(true);
        WebDriver driver=new InternetExplorerDriver(options);
		return driver;
	}

	public static WebDriver createChromeDriver() {
		ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        ChromeDriver driver = new ChromeDriver(options);
		return driver;
	}
	
	public static WebDriver createFirefoxDriver() {
//		System.setProperty("webdriver.gecko.driver", "/Users/galla/Documents/workspace/TestDemo/src/test/resources/geckodriver");
		FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        FirefoxDriver driver = new FirefoxDriver(options);
		return driver;
	}

}
