package point.pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import wrappers.GenericWrapper;

public class AboutUsPage extends GenericWrapper{
	
	String backGroudnColorBeforeMouseHover;
	
	String backGroudnColorafterMouseHover;
	
	String word = null;
	public void getWord() {
		word=prop.getProperty("Word");
	}
	
	public void mouseHoverOnWord() {
		 backGroudnColorBeforeMouseHover = getBackgroundColorByXPath(prop.getProperty("AboutUsPage.Word.XPath"),word);
		mouseHoverByXPath(prop.getProperty("AboutUsPage.Word.XPath"),prop.getProperty("Word"));
		
	}
	

	public void shortDelay() {
		waitProperty(5000);
	}
	
	public void verifyElementIsHighlighted() {
		 backGroudnColorafterMouseHover = getBackgroundColorByXPath(prop.getProperty("AboutUsPage.Word.XPath"),word);
		 System.out.println(backGroudnColorafterMouseHover);
		 System.out.println(backGroudnColorBeforeMouseHover);
		 if(!backGroudnColorBeforeMouseHover.equals(backGroudnColorafterMouseHover)) {
			 reportStep("The element is highlighted", "pass");
		 }else {
			 reportStep("The element is not highlighted", "fail");
		 }
	}
	
	
	
	public void verifySymbolsOnTheText() {
		
		verifyIsElementDisplayedByXPath(prop.getProperty("AboutUsPage.Word.XPath"),word);
		List<WebElement> elements = getListOfElementByXPath(prop.getProperty("AboutUsPage.Word.XPath"),word);
		
		for(WebElement element :elements) {
			String text = element.getText();
			if(text.equals(prop.getProperty("Word"))) {
				reportStep("The element is holding the text "+text+" is matched with expected text "+word, "pass");
			}else {
				reportStep("The element is holding the text "+text+" is not matched with expected text "+word, "pass");
			}
			
		}
		
	}
	
	
}
