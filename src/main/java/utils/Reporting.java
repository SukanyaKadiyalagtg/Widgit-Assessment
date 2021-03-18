package utils;

import java.util.Properties;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import wrappers.GenericWrapper;

public abstract class Reporting {
	public static Properties prop;
	public static ExtentReports report;
	public static ExtentTest test;
	
	 public abstract void loadObjects();
	 public abstract void unloadObject();
	 
	 
	public void startReport() {
		 report= new ExtentReports("./reports/results.html",false);
		
	}
	
	public void startTest(String testName) {
		
	 test=report.startTest(testName);
	 test.assignAuthor(prop.getProperty("Author.Test"));
	test.assignCategory(prop.getProperty("Regression.Test"));
		
	}
	
	public abstract long takeSnap();
	
	public void reportStep(String details,String status) {
		long snapnumber=takeSnap();
		
		if(status.equalsIgnoreCase("pass")) {
			test.log(LogStatus.PASS, details+test.addScreenCapture(".././reports/screenshots/"+snapnumber+".png"));
		}else if(status.equalsIgnoreCase("fail")){
			test.log(LogStatus.FAIL, details+test.addScreenCapture(".././reports/screenshots/"+snapnumber+".png"));
		}else if(status.equalsIgnoreCase("info")) {
			test.log(LogStatus.INFO, details+test.addScreenCapture(".././reports/screenshots/"+snapnumber+".png"));
		}else if(status.equalsIgnoreCase("warning"))
		{
			test.log(LogStatus.WARNING, details);
		}
		
	}
	public void reportStep(String details,String status,boolean snap) {
		//long snapnumber=takeSnap();
		if(!snap) {
		if(status.equalsIgnoreCase("pass")) {
			test.log(LogStatus.PASS, details);
		}else if(status.equalsIgnoreCase("fail")){
	 		test.log(LogStatus.FAIL, details);
		}else if(status.equalsIgnoreCase("info")) {
			test.log(LogStatus.INFO, details);
		}else if(status.equalsIgnoreCase("warning"))
		{
			test.log(LogStatus.WARNING, details);
		}
		}
	}
	public void endTest() {
		report.endTest(test);
		
	}
   public void endReport() {
	report.flush();
   }

}
