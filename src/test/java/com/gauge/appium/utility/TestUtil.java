package com.gauge.appium.utility;

import static org.testng.Assert.fail;

import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import io.appium.java_client.MobileBy;
import com.gauge.appium.base.TestBase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.KeyEvent;


	public class TestUtil {
		
		public static TestBase testBase= TestBase.getInstance();
		private AndroidDriver<AndroidElement> driver = testBase.getDriver();
		private static Properties prop = testBase.getConfig();
		private static  int IMPLICIT_WAIT;
		private static String Environment;
		
		public static int getImplicitTime() {
			IMPLICIT_WAIT = Integer.valueOf(prop.getProperty("Implicit_time"));
			return IMPLICIT_WAIT;	
		}
		
		public static String getEnvironment() {
			Environment = prop.getProperty("environment");
			return Environment;	
		}
		
		public static void waitTOSync() {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		
		public static void selectDropdownValueByText(WebElement element,String text) {
			Select drop = new Select(element);
			drop.selectByVisibleText(text);
		}

	    public void resetImplicitTimeout(int newTimeOut) {
	        driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS);
	    }
		
		public boolean elementBoolean(WebElement element) {
			resetImplicitTimeout(5);
			boolean flag = true;
			try {
				element.isDisplayed();
			}
			catch(NoSuchElementException e) {
				flag = false;
			}
			resetImplicitTimeout(IMPLICIT_WAIT);
			return flag;
		}
		
		protected WebElement getElementWhenVisible(By elementToken) {
			return getElementWhenVisible(elementToken, "");
		}
		
		public WebElement getElementWhenVisible(By elementToken, String replacement) throws NoSuchElementException {
			WebElement foundElement = null;
			try {
				By elementLocator = getLocator(elementToken, replacement);
				WebElement webElement = driver.findElement(elementLocator);
				foundElement = webElement;
			} catch (NoSuchElementException excp) {
				fail(log("[ASSERT FAILED]: Element " + elementToken + " not found on the webPage !!!"));
			} catch (NullPointerException npe) {
				fail("[UNHANDLED EXCEPTION]: " + npe.getLocalizedMessage());
			}
			return foundElement;
		}
		
		protected By getLocator(By elementToken, String replacement) {
			if (!replacement.isEmpty()) {
				String loc = elementToken.toString().replaceAll("\'", "\\\"");
				String type = loc.split(":", 2)[0].split(",")[0].split("\\.")[1];
				String variable = loc.split(":", 2)[1].replaceAll("\\$\\{.+?\\}", replacement);
				return getBy(type, variable);
			} else {
				return elementToken;
			}
		}
		
		private By getBy(String locatorType, String locatorValue) {
			switch (Locators.valueOf(locatorType)) {
			case id:
				return MobileBy.id(locatorValue);
			case xpath:
				return MobileBy.xpath(locatorValue);
			case css:
			case cssSelector:
				return MobileBy.cssSelector(locatorValue);
			case name:
				return MobileBy.name(locatorValue);
			case classname:
				return MobileBy.className(locatorValue);
			case AccessibilityId:
				return MobileBy.AccessibilityId(locatorValue);
			default:
				return MobileBy.id(locatorValue);
			}
		}
		
		public static String log(String logValue) {
			Reporter.log(logValue, true);
			return logValue;
		}	
		
		protected void InputKeyboard(KeyEvent key) {
			driver.pressKey(key);
		}
	
}
