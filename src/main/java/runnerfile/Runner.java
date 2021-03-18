package runnerfile;



import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import wrappers.GenericWrapper;



@CucumberOptions(
		
		
		features= {"D:\\Sukanya_Workspace\\Widgit_Assesment\\src\\main\\java\\FeatureFiles"},
		glue= {"stepdefs"}
		
		
		)
public class Runner extends GenericWrapper{
	

	
	 private TestNGCucumberRunner testNGCucumberRunner;
	 
	@BeforeSuite 
	public void beforeSuite() {
		
		startReport();
	}
	
	@BeforeTest
	public void beforeTest() {
		
		loadObjects();
	}

	    @BeforeClass(alwaysRun = true)
	    public void setUpClass() {
	        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	    }

	    
	    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable {
	        // the 'featureWrapper' parameter solely exists to display the feature file in a test report
	        
	    	startTest(pickleWrapper.getPickle().getName());
	    	String url;
	    	if(pickleWrapper.getPickle().getName().equals("Check symbols appear correctly")) {
	    		url =prop.getProperty("Live.URL").concat(prop.getProperty("URLEndPointPoint"));
	    	} else {
	    		url =prop.getProperty("Live.URL").concat(prop.getProperty("URLEndPointCarouser"));
	    	}
	    	
	    	invokeApp(prop.getProperty("Browser"), url);
	    	
	    	testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
	    }

	    
	    @DataProvider
	    public Object[][] scenarios() {
	        if (testNGCucumberRunner == null) {
	            return new Object[0][0];
	        }
	        return testNGCucumberRunner.provideScenarios();
	    }
	    
	    @AfterMethod
	    public void afterMethod() {
	    	
	    	closeAllBrowsers();
	    }
	   

	    @AfterClass(alwaysRun = true)
	    public void tearDownClass() {
	        if (testNGCucumberRunner == null) {
	            return;
	        }
	        testNGCucumberRunner.finish();
	       endTest();
	    }
	    
	    @AfterTest
	    public void afterTest() {
	    	
	    	unloadObject();
	    }
	    
	    @AfterSuite
	    public void afterSuite() {
	    	
	    	endReport();
	    }
	  

}


