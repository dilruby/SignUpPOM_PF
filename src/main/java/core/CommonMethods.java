package core;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonMethods {
	
	WebDriver driver;
	
	static final String url = "http://learn2test.net/qa/apps/sign_up/v1/";
	
	// method to open page
		public void launchBrowser(String url) {
			driver.get(url);
		}
		
		// method to click on Submit button
		public void clickSubmit() {
			driver.findElement(By.id("id_submit_button")).click();
		} 
		
		// method to verify copyright
		public void verifyCopyright(String copyrightExpected){
			driver.findElement(By.id("copyright")).getText();
			String copyrightActual = driver.findElement(By.id("copyright")).getText();
			assertEquals (copyrightExpected,copyrightActual);
			}
}
