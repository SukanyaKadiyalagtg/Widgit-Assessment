package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;

import org.openqa.selenium.ElementNotVisibleException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.remote.RemoteWebDriver;

import net.bytebuddy.asm.Advice.AllArguments;
import utils.Reporting;

public class GenericWrapper extends Reporting implements Wrapper{
	
	public static RemoteWebDriver driver;

   public void loadObjects() {
	 prop=new Properties();
	try {
		prop.load(new FileInputStream("./src/test/java/config.properties"));
	 } catch (FileNotFoundException e) {
		
		e.printStackTrace();
	 } catch (IOException e) {
		
		e.printStackTrace();
	 }
  }

    public void unloadObject() {
	     prop=null;
    }
    
  
    
	public void invokeApp(String browser, String url) {
		
		try {
			
			 if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				 driver=new ChromeDriver();
			}else if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				 driver = new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "./drivers/IEdriver.exe");
				 driver = new InternetExplorerDriver();
			}
				 driver.manage().window().maximize();
				 driver.get(url);
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				
				 reportStep("The browser "+browser+" is launched with url: "+url+" successfully", "pass");
			}
		 catch (SessionNotCreatedException e) {
			
			 reportStep("The browser "+browser+" is not launched with url: "+url, "fail");

		}
		catch (WebDriverException e) {

			//reportStep("The browser "+browser+" is not launched due to unknown error ");
			 reportStep("The browser "+browser+" is not launched with url: "+url, "fail");

			
		}
		
		}
	
	     
	
	     
	public void enterByXpath(String...xpathValue) {
		
		String XPath =null;
		try {
			if(xpathValue[0].contains("$")) {
				XPath=replaceXPath(xpathValue[0], xpathValue[1]);
				driver.findElementByXPath(XPath).sendKeys(xpathValue[2]);
				reportStep("The element with x-path "+xpathValue+" is entered with data "+xpathValue[2]+" successfully", "pass");
			}else {
				XPath=xpathValue[0];
				driver.findElementByXPath(XPath).sendKeys(xpathValue[1]);
				reportStep("The element with x-path "+xpathValue+" is entered with data "+xpathValue[1]+" successfully", "pass");
			}
			
		} catch (NoSuchElementException e) {
			
			reportStep("The element with x-path "+xpathValue+" is not found in DOM", "fail");	
		}
		catch (ElementNotVisibleException e) {
			
			reportStep("The element with x-path "+xpathValue+" is not visible in Application", "fail");	
		}
		catch (ElementNotInteractableException e) {
			
			reportStep("The element with x-path "+xpathValue+" is not in an interactable state", "fail");	
		}
		catch (StaleElementReferenceException e) {
			
			reportStep("The element with x-path "+xpathValue+" is not stable in application", "fail");
		}
		catch (WebDriverException e) {
			
			reportStep("The element with x-path "+xpathValue+" is not entered  due to unknown error", "fail");
		}
		
	}

	     
	
	     
	public void verifyTextByXpath(String xpath, String text) {
		
		try {
			String text2=driver.findElementByXPath(xpath).getText();
			if(text2.equals(text))
			{
				reportStep("The x-path of the elemnt: "+xpath+" with text "+text+" is Same as the "+text2, "pass");
			}
			else {
				reportStep("The x-path of the elemnt: "+xpath+" with text "+text+" is not Same as the "+text2, "pass");
			}
		}  catch (NoSuchElementException e) {
			
			reportStep("The element with x-path "+xpath+" is not found in DOM", "fail");	
		}
		catch (ElementNotVisibleException e) {
			
			reportStep("The element with x-path "+xpath+" is not visible in Application", "fail");	
		}
		catch (ElementNotInteractableException e) {
			
			reportStep("The element with x-path "+xpath+" is not in an interactable state", "fail");	
		}
		catch (StaleElementReferenceException e) {
			
			reportStep("The element with x-path "+xpath+" is not stable in application", "fail");
		}
		catch (WebDriverException e) {
			
			reportStep("The element with x-path "+xpath+" is not entered  due to unknown error", "fail");
		}
		
	}

	
	     
	public void mouseHoverByXPath(String...xpath) {
		
		String XPath =null;
		try {
			if(xpath[0].contains("$")) {
				XPath=replaceXPath(xpath[0], xpath[1]);
			}else {
				XPath=xpath[0];
			}
			
		WebElement h2=driver.findElementByXPath(XPath);
		Actions builder=new Actions(driver);
		builder.moveToElement(h2).perform();
		reportStep("The element which has x-path: "+XPath+" has been mouse hovered", "pass");
	} 
		catch (NoSuchElementException e) {
			
			reportStep("The element with x-path "+XPath+" is not found in DOM", "fail");	
		}
		catch (ElementNotVisibleException e) {
			
			reportStep("The element with x-path "+XPath+" is not visible in Application", "fail");	
		}
		catch (ElementNotInteractableException e) {
			
			reportStep("The element with x-path "+XPath+" is not in an interactable state", "fail");	
		}
		catch (StaleElementReferenceException e) {
			
			reportStep("The element with x-path "+XPath+" is not stable in application", "fail");
		}catch (WebDriverException e) {
		
			reportStep("The element which has x-path: "+XPath+" has not been mouse hovered due to unkown error", "fail");
	}
	
		
	}

	     
	public String replaceXPath(String xpath, String value) {
		
		if(xpath.contains("$")) {
			xpath=xpath.replace("$option$", value);
		} 
		
		return xpath;
	}

	     
	public void clickByXpath(String...xpathVal) {
		
		String XPath =null;
			try {
				if(xpathVal[0].contains("$")) {
					XPath=replaceXPath(xpathVal[0], xpathVal[1]);
				}else {
					XPath=xpathVal[0];
				}
			driver.findElementByXPath(XPath).click();
			reportStep("The element with x-path: "+XPath+ " is successfully clicked on using x-path locator", "pass");
		} catch (ElementClickInterceptedException e) {
			
			reportStep("The element with x-path: "+XPath+"  could not be clicked because the element is in a obscured state ", "fail");
		}
		catch (NoSuchElementException e) {
			
			reportStep("The element with x-path "+XPath+" is not found in DOM and can not be clicked", "fail");	
		}
		catch (ElementNotVisibleException e) {
			
			reportStep("The element with x-path "+XPath+" is not visible in Application and can not be clicked", "fail");	
		}
		catch (ElementNotInteractableException e) {
			
			reportStep("The element with x-path "+XPath+" is not in an interactable state and can not be clicked", "fail");	
		}
		catch (StaleElementReferenceException e) {
			
			reportStep("The element with x-path "+XPath+" is not stable in application and can not be clicked", "fail");
		}catch (WebDriverException e) {
		
			reportStep("The element which has x-path: "+XPath+" can not be clicked due to unkown error", "fail");
	}
		
	}

	    
	     
	public String getTextByXpath(String...xpathVal) {
		
		String t=null;
			String XPath =null;
			try {
				if(xpathVal[0].contains("$")) {
					XPath=replaceXPath(xpathVal[0], xpathVal[1]);
				}else {
					XPath=xpathVal[0];
				}
				
			t = driver.findElementByXPath(XPath).getText();
			reportStep("The value of the element  "+XPath+" is retuned using xpath", "pass");
		}  catch (NoSuchElementException e) {
			
			reportStep("The element with xpath "+XPath+" is not found in DOM and can not be read", "fail");	
		}
		catch (ElementNotVisibleException e) {
			
			reportStep("The element with xpath "+XPath+" is not visible in Application and can not be read", "fail");	
		}
		catch (ElementNotInteractableException e) {
			
			reportStep("The element with xpath "+XPath+" is not in an interactable state and can not be read", "fail");	
		}
		catch (StaleElementReferenceException e) {
			
			reportStep("The element with xpath "+XPath+" is not stable in application and can not be read", "fail");
		}catch (WebDriverException e) {
		
		reportStep("The element which has xpath: "+XPath+" can not be clicked due to unkown error", "fail");
	}
		
		return t;
	}

	     
	
	     

	     
	public long takeSnap() {
		
	
		long number=(long)(Math.floor(Math.random()*100000000)+1000000);
		
		try {
		File tmp=driver.getScreenshotAs(OutputType.FILE);
		File dest= new File("./reports/screenshots/"+number+".png");
		//fileutils.copyFile(tmp,dest);
		FileUtils.copyFile(tmp, dest);
		
	} catch (WebDriverException e) {
		
		System.err.println("could not take a screenshot due unkwon error");
	} catch (IOException e) {
		
		System.err.println("could not take a screenshot due unkwon IOerror");
	}
	return number;
	}

	     
	public void closeBrowser() {
		
		try {
			driver.close();
			reportStep("The current active browser is closed successfully", "pass",false);
		} catch (SessionNotCreatedException e) {
			
			reportStep("Cannot close current active browser as session is not created for browser ", "fail");
		}
		catch (WebDriverException e) {
			
			reportStep("Cannot close current active browser as session id is not created for browser ", "fail");
		}
		
	}

	     
	public void closeAllBrowsers() {
		
		try {
			driver.quit();
			reportStep("All the currently active browsers are closed successfully", "pass",false);
		} catch (SessionNotCreatedException e) {
			
			reportStep("Cannot close all the currently active browsers as session ids are  not created for browser ", "fail");
		}
		catch (WebDriverException e) {
			
			reportStep("Cannot close all the currently  active browser as session id is not created for browser ", "fail");
		}
		
	}
	
	public String getBackgroundColorByXPath(String...xpath) {
		
		String backcolor = null;
		String t=null;
		String XPath =null;
		try {
			if(xpath[0].contains("$")) {
				XPath=replaceXPath(xpath[0], xpath[1]);
			}else {
				XPath=xpath[0];
			}
			
			backcolor = driver.findElementByXPath(XPath).getCssValue("background-color");
			System.out.println(backcolor);
			reportStep("The element wiht xptha "+XPath+" is haviang the back ground colour "+backcolor, "pass");
		} catch (NoSuchElementException e) {
			
			reportStep("The element with xpath "+XPath+" is not found in DOM and can not be read", "fail");	
		}
		catch (ElementNotVisibleException e) {
			
			reportStep("The element with xpath "+XPath+" is not visible in Application and can not be read", "fail");	
		}
		catch (ElementNotInteractableException e) {
			
			reportStep("The element with xpath "+XPath+" is not in an interactable state and can not be read", "fail");	
		}
		catch (StaleElementReferenceException e) {
			
			reportStep("The element with xpath "+XPath+" is not stable in application and can not be read", "fail");
		}catch (WebDriverException e) {
		
		reportStep("The element which has xpath: "+XPath+" can not be clicked due to unkown error", "fail");
	}
		
		return backcolor;
	}

	     
	public void verifyIsElementDisplayedByXPath(String...xpath) {
		String XPath =null;
		try {
			if(xpath[0].contains("$")) {
				XPath=replaceXPath(xpath[0], xpath[1]);
			}else {
				XPath=xpath[0];
			}
			
			if(driver.findElementByXPath(XPath).isDisplayed()) {
				reportStep("The element with "+XPath+" is visible in the Application", "pass");
			}else {
				reportStep("The element with "+XPath+" is visible in the Application", "fail");
			}		
		}catch (NoSuchElementException e) {
			
			reportStep("The element with xpath "+XPath+" is not found in DOM and can not be read", "fail");	
		}
		catch (ElementNotVisibleException e) {
			
			reportStep("The element with xpath "+XPath+" is not visible in Application and can not be read", "fail");	
		}
		catch (ElementNotInteractableException e) {
			
			reportStep("The element with xpath "+XPath+" is not in an interactable state and can not be read", "fail");	
		}
		catch (StaleElementReferenceException e) {
			
			reportStep("The element with xpath "+XPath+" is not stable in application and can not be read", "fail");
		}catch (WebDriverException e) {
		
		reportStep("The element which has xpath: "+XPath+" can not be clicked due to unkown error", "fail");
	}
	}
	
	public List<WebElement> getListOfElementByXPath(String...xpath) {
		String XPath =null;
		List<WebElement> allElement = null;
		try {
			if(xpath[0].contains("$")) {
				XPath=replaceXPath(xpath[0], xpath[1]);
			}else {
				XPath=xpath[0];
			}
			
			 allElement = driver.findElementsByXPath(XPath);
			
		}catch (NoSuchElementException e) {
			
			reportStep("The element with xpath "+XPath+" is not found in DOM and can not be read", "fail");	
		}
		catch (ElementNotVisibleException e) {
			
			reportStep("The element with xpath "+XPath+" is not visible in Application and can not be read", "fail");	
		}
		catch (ElementNotInteractableException e) {
			
			reportStep("The element with xpath "+XPath+" is not in an interactable state and can not be read", "fail");	
		}
		catch (StaleElementReferenceException e) {
			// TODO: handle exception
			reportStep("The element with xpath "+XPath+" is not stable in application and can not be read", "fail");
		}catch (WebDriverException e) {
		
		reportStep("The element which has xpath: "+XPath+" can not be clicked due to unkown error", "fail");
	}
		
		return allElement;
		
	}
	
	
	public List<String> getListOfTextByXPath(String...xpathValue){
		
		List<String> list = new ArrayList<String>();
		List<WebElement> allElement = driver.findElementsByXPath(xpathValue[0]);
		for(Integer i=1; i<=allElement.size(); i++) {
			String xpath = replaceXPath(xpathValue[1], i.toString());
		List<WebElement> allElement1 = driver.findElementsByXPath(xpath);
		if(allElement1.size()>0) {	
		for(WebElement element :allElement1) {
			if(!element.getText().trim().isEmpty()) {
			list.add(element.getText());
			}	
		}
		}
		}
		
		return list;
	}
	
	public void waitProperty(long time) {
		
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			
		}
	}

	

}
