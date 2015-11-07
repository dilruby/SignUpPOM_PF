package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

public class Confirmation_Page {
	WebDriver driver;

	public Confirmation_Page(WebDriver wd) {
		driver = wd;
	}
	
	@FindBy(id="id_submit_button")
	@CacheLookup
	static WebElement submitButton;
	
	@FindBy(id="id_fname")
	@CacheLookup
	static WebElement fnameField;
	
	@FindBy(id="id_lname")
	@CacheLookup
	static WebElement lnameField;
	
	@FindBy(id="id_email")
	@CacheLookup
	static WebElement emailField;
	
	@FindBy(id="id_phone")
	@CacheLookup
	static WebElement phoneField;
		
	@FindBy(id="id_g_radio_01")
	@CacheLookup
	static WebElement maleCheckbox;
	
	@FindBy(id="id_g_radio_02")
	@CacheLookup
	static WebElement femaleCheckbox;
	
	@FindBy(id="id_checkbox")
	@CacheLookup
	static WebElement agreed;
	
	@FindBy(id="id_state")
	@CacheLookup
	static WebElement stateUS;
	
	@FindBy(id="id_fname_conf")
	@CacheLookup
	static WebElement fnameConf;
	
	@FindBy(id="id_lname_conf")
	@CacheLookup
	static WebElement lnameConf;
	
	@FindBy(id="id_email_conf")
	@CacheLookup
	static WebElement emailConf;
	
	@FindBy(id="id_phone_conf")
	@CacheLookup
	static WebElement phoneConf;
	
	@FindBy(id="id_gender_conf")
	@CacheLookup
	static WebElement genderConf;
	
	@FindBy(id="id_state_conf")
	@CacheLookup
	static WebElement stateConf;
	
	@FindBy(id="id_terms_conf")
	@CacheLookup
	static WebElement agreedConf;
		
	@FindBy(id="id_back_button")
	@CacheLookup
	static WebElement backButton;
	
	// method to open page
	public void launchBrowser(String url) {
		driver.get(url);
	} // launchBrowser

	// method to click on Submit button
	public void clickSubmit() {		
		submitButton.click();
	} // clickSubmit

	// method to submit form
	public void submitForm(String fname, String lname, String email, String phone, String gender, String state,
			Boolean terms, String cterms, String ctitle) {
		launchBrowser("http://learn2test.net/qa/apps/sign_up/v1/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		fnameField.clear();
		fnameField.sendKeys(fname);
		lnameField.clear();
		lnameField.sendKeys(lname);
		emailField.clear();
		emailField.sendKeys(email);
		phoneField.clear();
		phoneField.sendKeys(phone);
		if (gender.equalsIgnoreCase("male")) {
			maleCheckbox.click();
		} else if (gender.equalsIgnoreCase("female")) {
			femaleCheckbox.click();
		}
		if (terms == true) {
			agreed.click();
		}
		if (state.isEmpty()) {
		} else {
			new Select(stateUS).selectByVisibleText(state);
		}
		clickSubmit();

		String fnameConfActual = fnameConf.getText();
		String lnameConfActual = lnameConf.getText();
		String emailConfActual = emailConf.getText();
		String phoneConfActual = phoneConf.getText();
		String genderConfActual = genderConf.getText();
		String stateConfActual = stateConf.getText();
		String termsConfActual = agreedConf.getText();
		assertEquals(driver.getTitle(), ctitle);
		assertEquals(fname, fnameConfActual);
		assertEquals(lname, lnameConfActual);
		assertEquals(email, emailConfActual);
		assertEquals(phone, phoneConfActual);
		assertEquals(gender, genderConfActual);
		assertEquals(state, stateConfActual);
		assertEquals(cterms, termsConfActual);
	} // submit_form

	// method for back button verification
	public void verifyBackButton(String titleExpected) {
		launchBrowser("http://learn2test.net/qa/apps/sign_up/v1/conformation.php");
		backButton.click();
		assertEquals(driver.getTitle(), titleExpected);

	} // verifyBackButton

} // class Confirmation_Page
