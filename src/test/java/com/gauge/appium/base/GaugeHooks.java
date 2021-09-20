package com.gauge.appium.base;
import com.gauge.appium.utility.TestUtil;
import com.thoughtworks.gauge.*;

public class GaugeHooks {
	
	public static TestBase testBase= TestBase.getInstance();
	
		  @BeforeSuite 
		  public void BeforeSuite() {
			  testBase.getDriver();
			  TestUtil.log("[Info] : Driver Intialized");
		  }

		  @AfterSuite
		  public void AfterSuite() {
			  testBase.closeBrowsers();
			  TestUtil.log("[Info] : Browser closed");
		  }

		  @BeforeSpec 
		  public void BeforeSpec(ExecutionContext context) {
			  try {
					context.wait(15000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		  }	

		  @AfterSpec 
		  public void AfterSpec() {

		  }
		  
		  @BeforeScenario
		  public void BeforeScenario(ExecutionContext context) {
		     Scenario SceanrioName = context.getCurrentScenario();
		     TestUtil.log("[Execution started] : "+SceanrioName);
		  }

		  @AfterScenario
		  public void AfterScenario(ExecutionContext context) {
			 Scenario SceanrioName = context.getCurrentScenario();
			 TestUtil.log("[Execution Completed] : "+SceanrioName);
		  }

		  @BeforeStep
		  public void BeforeStep(ExecutionContext context) {
			  try {
				context.wait(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }

		  @AfterStep
		  public void AfterStep() {
		   
		  }
	
}
