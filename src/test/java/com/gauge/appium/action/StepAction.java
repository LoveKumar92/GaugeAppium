package com.gauge.appium.action;


import static org.testng.Assert.*;
import org.openqa.selenium.By;
import com.gauge.appium.base.TestBase;
import com.gauge.appium.utility.TestUtil;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class StepAction extends TestUtil {
	
	public static By searchBox = MobileBy.AccessibilityId("Search");
	public static By EditSearchBox = MobileBy.className("android.widget.EditText");
	public static By SearchedResults = MobileBy.xpath("(//android.widget.TextView[@resource-id=\"com.google.android.youtube:id/title\"])[1]");

	public void clickOnSearchField() {
		getElementWhenVisible(searchBox).click();
		TestUtil.log("[INFO]: User click on search box of app");
		
	}

	public void enterSearchText(String searchedText) {
		getElementWhenVisible(EditSearchBox).sendKeys(searchedText);
		InputKeyboard(new KeyEvent(AndroidKey.ENTER));	
	 	TestUtil.log("[INFO]: User enter the searched text : "+searchedText);
	}

	public void verifysearchResults(String resultedVideo) {
		String videoName = getElementWhenVisible(SearchedResults).getText();
	     assertEquals(videoName,"Vr Studio A Wedding Highlight 02 Love");
	     TestUtil.log("[INFO]: Searched video is found to be  : "+videoName);     
	}

	public void clickToPlaySearchedVideo() {
		getElementWhenVisible(SearchedResults).click();
		TestUtil.log("[INFO]: User played the Searched video");
	}

}
