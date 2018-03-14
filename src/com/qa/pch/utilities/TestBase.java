package com.qa.pch.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestBase {
	public WebDriver driver = null;
	public static ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriver() {
		return drivers.get();
	}
	
	public static void setDriver(WebDriver driver){
		drivers.set(driver);
	}
	
	@BeforeMethod
	@Parameters(value = { "browser", "exec" })
	public void initializeBrowser(String value, String executionFlag) throws Exception {
		DesiredCapabilities caps = null;
		if (value.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\MyDrivers\\chromedriver.exe");
			if (executionFlag.equals("local")) {
				System.out.println("Local Chrome constructed");
				driver = new ChromeDriver();
				setDriver(driver);
			} else {
				System.out.println("Remote Webdriver Constructed");
				caps = DesiredCapabilities.chrome();
				caps.setBrowserName("chrome");
				caps.setVersion("45.0");
				caps.setJavascriptEnabled(true);
				caps.setCapability("os.version", Platform.WINDOWS);
				driver = new RemoteWebDriver(new URL("http://192.168.128.219:8080/wd/hub"), caps);
				setDriver(driver);
			}
			driver.manage().window().maximize();
		} else if (value.equals("firefox")) {
			if (executionFlag.equals("local")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\MyDrivers\\geckodriver.exe");
				System.out.println("Local Firefox constructed");
				driver = new FirefoxDriver();
				setDriver(driver);
			} else {
				System.out.println("Remote Webdriver Constructed");
				caps = DesiredCapabilities.firefox();
				caps.setBrowserName("firefox");
				caps.setVersion("41.0");
				caps.setJavascriptEnabled(true);
				caps.setCapability("os.version", Platform.WINDOWS);
				driver = new RemoteWebDriver(new URL("http://192.168.128.219:8080/wd/hub"), caps);
				setDriver(driver);
			}
			driver.manage().window().maximize();
		} else if (value.equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\MyDrivers\\IEDriverServer.exe");
			if (executionFlag.equals("local")) {
				System.out.println("Local IE constructed");
				driver = new InternetExplorerDriver();
				setDriver(driver);
			} else {
				System.out.println("Remote Webdriver Constructed");
				caps = DesiredCapabilities.internetExplorer();
				caps.setBrowserName("internet explorer");
				caps.setVersion("11");
				caps.setJavascriptEnabled(true);
				caps.setCapability("os.version", Platform.WINDOWS);
				driver = new RemoteWebDriver(new URL("http://192.168.128.219:8080/wd/hub"), caps);
				setDriver(driver);
			}
			driver.manage().window().maximize();
		} else if (value.equals("phantom")) {
			System.out.println("Local Phantom constructed");
			File file = new File(System.getProperty("user.dir")
					+ "\\MyDrivers\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
			System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
			//driver = new PhantomJSDriver();
			setDriver(driver);
			driver.manage().window().maximize();
		} else {
			Assert.assertTrue(false, "Invalid browser");
		}
	}

	@AfterMethod
	public void shutdown() throws Exception {
		driver.quit();
	}
	
	protected boolean waits(int seconds, String locator) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), seconds);
		return wait.until(new Function<WebDriver, Boolean>() {
			Boolean isWindowFound = Boolean.FALSE;
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					ExpectedConditions.visibilityOf(driver.findElement(getByType(locator)));
					isWindowFound = Boolean.TRUE;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return isWindowFound;
			}
		});
	}
	
	protected boolean waitForPresence(int seconds, String locator) throws Exception {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(seconds, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return wait.until(new Function<WebDriver, Boolean>() {
			Boolean isWindowFound = Boolean.FALSE;
			public Boolean apply(WebDriver driver) {
				try {
					driver.findElement(getByType(locator));
					isWindowFound = Boolean.TRUE;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return isWindowFound;
			}
		});
	}
	
	protected void waits(int seconds, WebElement element) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), seconds);
		wait.until(new Function<WebDriver, Boolean>() {
			Boolean isWindowFound = Boolean.FALSE;
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					ExpectedConditions.visibilityOf(element);
					isWindowFound = Boolean.TRUE;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return isWindowFound;
			}
		});
	}

	public void wait(int seconds) throws Exception {
		System.out.println(getDriver());
		getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	protected void waitForPageToLoad() throws Exception {
		((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete");
	}
	
	protected String getData(String name) throws Exception{
		Properties prop = new Properties();
		InputStream in = new FileInputStream(new File(System.getProperty("user.dir")+"\\testdata.properties"));
		prop.load(in);
		return prop.getProperty(name).trim();
	}
	
	protected void launchApp(String url) throws Exception{
		getDriver().get(url);
		//Thread.sleep(10000L);
	}
	
	public By getByType(String locator) throws Exception {
		if (locator.startsWith("//"))
			return By.xpath(locator);
		else if (locator.startsWith("css="))
			return By.cssSelector(locator.split("css=")[1]);
		else if (locator.startsWith("name="))
			return By.name(locator.split("name=")[1]);
		else if (locator.startsWith("tagname="))
			return By.tagName(locator.split("tagname=")[1]);
		else
			return By.id(locator);
	}
	
	
	
}
