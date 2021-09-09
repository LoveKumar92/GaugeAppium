package com.gauge.appium.base;

import static com.gauge.appium.utility.YamlReader.setYamlFilePath;
import static com.gauge.appium.utility.ConfigReader.intializeConfigFile;
import static com.gauge.appium.utility.ConfigReader.getConfigValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.gauge.appium.utility.TestUtil;
import com.thoughtworks.gauge.AfterSpec;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBase {

	private static TestBase testBase = null;
	private static AndroidDriver<AndroidElement> driver;

	
	private TestBase() {
		try {
			intializeConfigFile();
			setYamlFilePath();
			AndroidInitialization();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private AndroidDriver<AndroidElement> AndroidInitialization() throws MalformedURLException {
		File filePath = new File(System.getProperty("user.dir"));
		File appDir = new File(filePath, "/resources/TestApp");
		File app = new File(appDir, getConfigValue("App"));
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.VERSION, getConfigValue("OSVersion"));
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getConfigValue("DeviceName"));
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability("adbExecTimeout", Integer.valueOf(getConfigValue("AppConnectionTime")));
//		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.youtube");
    	capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.google.android.apps.youtube.app.WatchWhileActivity");
		driver = new AndroidDriver<AndroidElement>(new URL(getConfigValue("appiumServerAddress")), capabilities);
		driver.manage().timeouts().implicitlyWait(Integer.valueOf(getConfigValue("Implicit_time")), TimeUnit.SECONDS);
		TestUtil.log("[INFO]: Connected");
		return driver;
	}

	public AndroidDriver<AndroidElement> getDriver() {
		if (driver == null) {
			try {
				driver = AndroidInitialization();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return driver;
	}
	
	public static TestBase getInstance() {
		if (testBase == null) {
			testBase = new TestBase();
		}
		return testBase;
	}
	
	@AfterSpec
	public void closeBrowsers() {
		driver.quit();
		TestUtil.log("[INFO]: Session completed");
	}

}
