package core;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.Document;

public class SignUp_Page {
	WebDriver driver;

	public SignUp_Page(WebDriver wd) {
		driver = wd;
	}
	
	@FindBy(id="id_f_title")
	@CacheLookup
	static WebElement appTitle;
	
	@FindBy(id="id_img_facebook")
	@CacheLookup
	static WebElement facebookImage;
	
	@FindBy(id="id_img_twitter")
	@CacheLookup
	static WebElement twitterImage;
	
	@FindBy(id="id_img_flickr")
	@CacheLookup
	static WebElement flickrImage;
	
	@FindBy(id="id_img_youtube")
	@CacheLookup
	static WebElement youtubeImage;
	
	@FindBy(id="id_submit_button")
	@CacheLookup
	static WebElement submitButton;
	
	@FindBy(id="id_quotes")
	@CacheLookup
	static WebElement quotes;
	
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
	
	@FindBy(id="ErrorLine")
	@CacheLookup
	static WebElement error;
	
	@FindBy(id="id_current_location")
	@CacheLookup
	static WebElement currentLocation;
	
	
	@FindBy(xpath="//td[1]/img")
	@CacheLookup
	static WebElement weatherIcon;
	
	
	@FindBy(id="id_temperature")
	@CacheLookup
	static WebElement temperature;	
	
	@FindBy(id="timestamp")
	@CacheLookup
	static WebElement currentDate;
	
	@FindBy(id="copyright")
	@CacheLookup
	static WebElement copyright;
	
	
	
	// method to open page
	public void launchBrowser(String url) {
		driver.get(url);
	}

	// method for title validation
	public void verifyTitle(String titleExpected, String url) {
		launchBrowser(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String titleActual = driver.getTitle();
		assertEquals(titleExpected, titleActual);
	} // verify_title

	// method for application title validation
	public void verifyAppTitle(String appTitleExpected, String url) {
		launchBrowser(url);
		
		String appTitleActual = appTitle.getText();
		assertEquals(appTitleExpected, appTitleActual);
	} // verify_app_title

	// method for link validation
	public void verifyLink(String titleLinkExpected, String url, String idImage) {
		launchBrowser(url);
		driver.findElement(By.id(idImage)).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));
		String titleLinkActual = driver.getTitle();
		driver.close();
		driver.switchTo().window(allTabs.get(0));
		assertEquals(titleLinkExpected, titleLinkActual);
	} // verify_link

	// method to click on Submit button
	public void clickSubmit() {
		submitButton.click();
	} // clickSubmit

	// method for quote validation
	public boolean verifyQuote() {		
		String quote = quotes.getText();

		if (quote != null && quote.length() < 103 && quote.length() > 67) {
			return true;
		} else {
			return false;
		}

	} // verify_quote

	// method for error handling
	public void verifyErrorHandling(String fname, String lname, String email, String phone, String errorExpected,
			String url) {

		fnameField.clear();
		fnameField.sendKeys(fname);
		lnameField.clear();
		lnameField.sendKeys(lname);
		emailField.clear();
		emailField.sendKeys(email);
		phoneField.clear();

		clickSubmit();
		
		if (fname.matches("/^[a-zA-Z,.'-]{3,30}$/")) {
			if (lname.matches("/^[a-zA-Z,.'-]{3,30}$/")) {
				if (email.matches("/[a-zA-Z0-9]{2,}@([0-9a-zA-Z][-\\w]*\\.)+[a-zA-Z]{2,6}/")) {
					if (phone.matches("/^\\(?[\\d]{3}\\)?[\\s-]?[\\d]{3}[\\s-]?[\\d]{4}$/")) {
					} else {
						String errorActual = error.getText();
						assertEquals(errorExpected, errorActual);
					}
				} else {
					String errorActual = error.getText();
					assertEquals(errorExpected, errorActual);
				}
			} else {
				String errorActual = error.getText();
				assertEquals(errorExpected, errorActual);
			}
		} else {
			String errorActual = error.getText();
			assertEquals(errorExpected, errorActual);
		}

	} // verify_error_handling
	
	// method for current city and state validation
	public void verifyCurrentCityState() throws Exception {

		String actualCityState = readActualCityState();

		driver.get("http://learn2test.net/qa/apps/sign_up/v0/");
		currentLocation.isDisplayed();
		String expectedCityState = currentLocation.getText();
		assertEquals(expectedCityState, actualCityState);
	} // verifyCurrentCityState

	// method for reading actual City and State from XML file
	private String readActualCityState() throws Exception {
		String ip = readStringFromUrl("http://checkip.amazonaws.com");
		String urlForIp = generateUrlForIp(ip);
		Document doc_xml = readXmlDocumentFromUrl(urlForIp);

		XPath x = XPathFactory.newInstance().newXPath();
		String latitude = x.compile("geoPlugin/geoplugin_latitude").evaluate(doc_xml);
		String longitude = x.compile("geoPlugin/geoplugin_longitude").evaluate(doc_xml);

		String urlForLatitudeAndLongitude = generateUrlForLatitudeAndLongitude(latitude, longitude);
		Document doc = readXmlDocumentFromUrl(urlForLatitudeAndLongitude);

		XPath xpath = XPathFactory.newInstance().newXPath();

		return xpath.compile("//display_location/full").evaluate(doc);
	} // readActualCityState

	// method for generation URL for current IP
	
	private String generateUrlForIp(String ip) {
		return "http://www.geoplugin.net/xml.gp?ip=" + ip;
	} // generateUrlForIp

	// method for generation URL for Latitude and Longitude of the current location
	private String generateUrlForLatitudeAndLongitude(String latitude, String longitude) {
		return "http://api.wunderground.com/api/8a75c2aa5ba78758/conditions/q/" + latitude + "," + longitude + ".xml";
	} // generateUrlForLatitudeAndLongitude

	// method for reading string from URL
	private String readStringFromUrl(String url) throws IOException {
		URL myip = new URL(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(myip.openStream()));
		return in.readLine();
	} // readStringFromUrl

	// method for reading XML document from URL
	private Document readXmlDocumentFromUrl(String url) throws IOException, Exception {
		DocumentBuilderFactory f_xml = DocumentBuilderFactory.newInstance();
		DocumentBuilder b_xml = f_xml.newDocumentBuilder();
		Document document = b_xml.parse(url);
		document.getDocumentElement().normalize();
		return document;
	} // readXmlDocumentFromUrl

	// method for weather icon validation
	public void verifyCurrentWeather() throws IOException, Exception {
		WebElement image = weatherIcon;
		String src = image.getAttribute("src");
		int ind = src.lastIndexOf("/");
		String filenameExpected = src.substring(ind + 1);

		String ip = readStringFromUrl("http://checkip.amazonaws.com");
		String urlForIp = generateUrlForIp(ip);
		Document doc_xml = readXmlDocumentFromUrl(urlForIp);

		XPath x = XPathFactory.newInstance().newXPath();
		String latitude = x.compile("geoPlugin/geoplugin_latitude").evaluate(doc_xml);
		String longitude = x.compile("geoPlugin/geoplugin_longitude").evaluate(doc_xml);

		String urlForLatitudeAndLongitude = generateUrlForLatitudeAndLongitude(latitude, longitude);
		Document doc = readXmlDocumentFromUrl(urlForLatitudeAndLongitude);

		XPath xpath = XPathFactory.newInstance().newXPath();

		String weatherUrl = xpath.compile("//icon_url").evaluate(doc);
		int index = weatherUrl.lastIndexOf("/");
		String filenameActual = weatherUrl.substring(index + 1);

		assertEquals(filenameExpected, filenameActual);
	} // verifyCurrentWeather

	// method for temperature validation
	public void verifyTemperature() throws IOException, Exception {
		String ip = readStringFromUrl("http://checkip.amazonaws.com");
		String urlForIp = generateUrlForIp(ip);
		Document doc_xml = readXmlDocumentFromUrl(urlForIp);

		XPath x = XPathFactory.newInstance().newXPath();
		String latitude = x.compile("geoPlugin/geoplugin_latitude").evaluate(doc_xml);
		String longitude = x.compile("geoPlugin/geoplugin_longitude").evaluate(doc_xml);

		String urlForLatitudeAndLongitude = generateUrlForLatitudeAndLongitude(latitude, longitude);
		Document doc = readXmlDocumentFromUrl(urlForLatitudeAndLongitude);

		XPath xpath = XPathFactory.newInstance().newXPath();
		String tempActual = xpath.compile("//temp_f").evaluate(doc) + " ℉";
		String tempExpected = temperature.getText();
		assertEquals(tempExpected, tempActual);
	} // verifyTemperature
	
	// method for current date validation
	public void verifyCurrentDate (){
		DateFormat dtFormat = new SimpleDateFormat("MMMMM dd, YYYY ");
		Calendar cal = Calendar.getInstance();
		String currentDay = dtFormat.format(cal.getTime());
		String currentDateApp = currentDate.getText();
		assertEquals (currentDateApp,currentDay);
	} // verifyCurrentDate
	
	public void verifyCopyright(String copyrightExpected){
	copyright.isDisplayed();
	String copyrightActual = copyright.getText();
	assertEquals (copyrightExpected,copyrightActual);
	} // verifyCopyright

} // class SignUp_Page
