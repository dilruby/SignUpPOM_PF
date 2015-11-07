package core;

import static org.junit.Assert.assertEquals;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class CommonMethods {
	
	WebDriver driver;
	
	@FindBy(id="id_submit_button")
	@CacheLookup
	static WebElement submitButton;
	
	@FindBy(id="copyright")
	@CacheLookup
	static WebElement copyright;
				
		// method to click on Submit button
		public void clickSubmit() {
			submitButton.click();
		} 
		
		// method to verify copyright
		public void verifyCopyright(String copyrightExpected){
			copyright.isDisplayed();
			String copyrightActual = copyright.getText();
			assertEquals (copyrightExpected,copyrightActual);
			}
}
