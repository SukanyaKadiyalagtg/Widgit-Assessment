package point.pages;
import java.util.List;

import org.openqa.selenium.WebElement;

import wrappers.GenericWrapper;

public class IndexPage extends GenericWrapper{
	
	List<String> listBeforeClick;
	
	
	
	public void clickOnRightNavigation() {
		clickByXpath(prop.getProperty("IndexPage.RightArrow.XPath"));
	}
	
	public void clickOnLeftNavigation() {
		clickByXpath(prop.getProperty("IndexPage.LeftArrow.XPath"));
	}
	

	public void getTheDefaultWidgetList() {	
		listBeforeClick = getListOfTextByXPath(prop.getProperty("IndexPage.FullWidget.XPath"),prop.getProperty("IndexPage.ContentWidget.XPath"));
	}
	
	public void validateRightNavigation() {
		
		List<String> listAfterClick = getListOfTextByXPath(prop.getProperty("IndexPage.FullWidget.XPath"),prop.getProperty("IndexPage.ContentWidget.XPath"));
		
		System.out.println(listBeforeClick);
		System.out.println(listAfterClick);
		
		if(!listBeforeClick.equals(listAfterClick)) {
			reportStep("The content before clicking on Right navigation "+listBeforeClick+" is not same as content after click "+listAfterClick, "pass");
		if(listBeforeClick.get(1).equals(listAfterClick.get(0)) && listBeforeClick.get(2).equals(listAfterClick.get(1))) {
			reportStep("The content shifts once to the Right when clicked on right navigation button", "pass");
		}else {
			reportStep("The content does not shifts once to the Right when clicked on right navigation button", "fail");
		}
		}else {
			reportStep("The content before clicking on Right navigation "+listBeforeClick+" is same as content after click "+listAfterClick, "fail");
		}
	
		
	}
	
	public void validateLeftNavigation() {
		
		
		
		List<String> listAfterClick = getListOfTextByXPath(prop.getProperty("IndexPage.FullWidget.XPath"),prop.getProperty("IndexPage.ContentWidget.XPath"));
		
		System.out.println(listBeforeClick);
		System.out.println(listAfterClick);
		
		if(!listBeforeClick.equals(listAfterClick)) {
			reportStep("The content before clicking on Lest navigation "+listBeforeClick+" is not same as content after click "+listAfterClick, "pass");
		if(listBeforeClick.get(0).equals(listAfterClick.get(1)) && listBeforeClick.get(1).equals(listAfterClick.get(2))) {
			reportStep("The content shifts once to the Left when clicked on left navigation button", "pass");
		}else {
			reportStep("The content does not shifts once to the Left when clicked on left navigation button", "fail");
		}
		}else {
			reportStep("The content before clicking on Left navigation "+listBeforeClick+" is same as content after click "+listAfterClick, "fail");
		}
	
	}
	
	public void navigateAllContent() {
		
		List<WebElement> allElements = getListOfElementByXPath(prop.getProperty("IndexPage.FullWidget.XPath"));
		
		int num=(allElements.size()/2)-1;
		
		for(int i=1;i<=(allElements.size()/2)-1;i++) {
			waitProperty(3000);
			clickOnRightNavigation();
		}
		
	}
	
	public void validateFirstContent() {
		List<String> listAfterNavigation  = getListOfTextByXPath(prop.getProperty("IndexPage.FullWidget.XPath"),prop.getProperty("IndexPage.ContentWidget.XPath"));
		if(listBeforeClick.equals(listAfterNavigation)) {
			reportStep("The content before clicking on full navigation "+listBeforeClick+" is same as content after click "+listAfterNavigation, "pass");
		if(listBeforeClick.get(0).equals(listAfterNavigation.get(0)) && listBeforeClick.get(1).equals(listAfterNavigation.get(1)) && listBeforeClick.get(2).equals(listAfterNavigation.get(2))) {
			reportStep("The content shifts once to the first content when clicked on full navigation button", "pass");
		}else {
			reportStep("The content does not shifts once to the Left when clicked on full navigation button", "fail");
		}
		}else {
			reportStep("The content before clicking on full navigation "+listBeforeClick+" is not same as content after click "+listAfterNavigation, "fail");
		}
		
	}

}
