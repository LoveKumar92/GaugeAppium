package com.gauge.appium.test;

import com.gauge.appium.action.StepAction;
import com.gauge.appium.base.TestBase;
import com.thoughtworks.gauge.Step;

import java.net.MalformedURLException;


public class StepImplementation {
	
		StepAction stepAction = new StepAction();
		TestBase testBase = TestBase.getInstance();
			

//    @Step("Launch You tube app over android Device")
//    public void launhcAndroidApp() throws MalformedURLException {	
//    	testBase.getDriver();
//        }

    @Step("Enter text <Vr Studio A Wedding Highlight 02 Love> in search box")
    public void SearchVideo(String SearchedText) {
    	stepAction.clickOnSearchField();
    	stepAction.enterSearchText(SearchedText);
    }

    @Step("Verify and play resulted video <Vr Studio A Wedding Highlight 02 Love>")
    public void verifyResults(String ResultedVideo) {
    	stepAction.verifysearchResults(ResultedVideo);
    	stepAction.clickToPlaySearchedVideo();
        }
}
