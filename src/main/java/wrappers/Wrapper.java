package wrappers;

public interface Wrapper {
	
	public void invokeApp(String browser, String url);
	
	public void enterByXpath(String...xpathValue);
	
	public void clickByXpath(String...XPath);
	
	public void verifyTextByXpath(String xpath, String text);
	
	public void mouseHoverByXPath(String...XPath);
	
	public void closeBrowser();
	
	public void closeAllBrowsers();
 
    public void waitProperty(long time);
    
    

}
