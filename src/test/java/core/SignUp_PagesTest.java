package core;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SignUp_PagesTest {

	private String url = "http://learn2test.net/qa/apps/sign_up/v1/";

	private String titleExpected = "Welcome to Sign Up v1";
	private String appTitleExpected = "Sign Up";

	private String titleFacebookExpected = "Facebook - Log In or Sign Up";
	private String idFacebookImage = "id_img_facebook";

	private String titleTwitterExpected = "Twitter";
	private String idTwitterImage = "id_img_twitter";

	private String titleFlickrExpected = "Flickr, a Yahoo company | Flickr - Photo Sharing!";
	private String idFlickrImage = "id_img_flickr";

	private String titleYoutubeExpected = "YouTube";
	private String idYoutubeImage = "id_img_youtube";

	private String firstNameEmpty = "";
	private String lastNameEmpty = "";
	private String emailEmpty = "";
	private String phoneNumberEmpty = "";

	String errorFirstNameExpected = "Please enter First Name";
	String errorLastNameExpected = "Please enter Last Name";
	String errorEmailExpected = "Please enter Email Address";
	String errorPhoneNumberExpected = "Please enter Phone Number";
	
	String fname = "Alex";
	String lname = "Moore";
	String email = "alexmoore@gmail.com";
	String phone = "415 555-1212";
	String gender = "Male";
	String state = "California";
	Boolean terms = true;
	String cterms = "Agreed";
	String copyrightExpected = "© 2015 learn2test.net";
	String ctitle = "Confirmation";
	String idButton = "id_back_button";

	//PageFactory.initElements(driver, Confirmation_Page.class);
	SignUp_Page sup = PageFactory.initElements(driver, SignUp_Page.class);
	Confirmation_Page cp = PageFactory.initElements(driver, Confirmation_Page.class);
	CommonMethods cm = PageFactory.initElements(driver, CommonMethods.class);
	
	static WebDriver driver = new FirefoxDriver();

	@BeforeClass
	public static void beforeClass() throws Exception {
	}

	@AfterClass
	public static void afterClass() throws Exception {
		driver.quit();
	}

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	// @Ignore
	@Test
	public void test_01_verifyTitle() {
		sup.verifyTitle(titleExpected, url);
	}

	// @Ignore
	@Test
	public void test_02_verifyAppTitle() {
		sup.verifyAppTitle(appTitleExpected, url);
	}

	// @Ignore
	@Test
	public void test_03_verifyFacebookLink() {
		sup.verifyLink(titleFacebookExpected, url, idFacebookImage);
	}

	// @Ignore
	@Test
	public void test_04_verifyTwitterLink() {
		sup.verifyLink(titleTwitterExpected, url, idTwitterImage);
	}

	// @Ignore
	@Test
	public void test_05_verifyFlickrLink() {
		sup.verifyLink(titleFlickrExpected, url, idFlickrImage);
	}

	// @Ignore
	@Test
	public void test_06_verifyYoutubeLink() {
		sup.verifyLink(titleYoutubeExpected, url, idYoutubeImage);
	}

	// @Ignore
	@Test
	public void test_07_verifyErrorHandlingFirstName() {
		sup.verifyErrorHandling(firstNameEmpty, lname, email, phone, errorFirstNameExpected);
	}

	// @Ignore
	@Test
	public void test_08_verifyErrorHandlingLastName() {
		sup.verifyErrorHandling(fname, lastNameEmpty, email, phone, errorLastNameExpected);
	}

	// @Ignore
	@Test
	public void test_09_verifyErrorHandlingEmail() {
		sup.verifyErrorHandling(fname, lname, emailEmpty, phone, errorEmailExpected);
	}

	// @Ignore
	@Test
	public void test_10_verifyErrorHandlingPhoneNumber() {
		sup.verifyErrorHandling(fname, lname, email, phoneNumberEmpty, errorPhoneNumberExpected);
	}

	// @Ignore
	@Test
	public void test_11_verifySubmitForm() {
		cp.submitForm(fname, lname, email, phone, gender, state, terms, cterms, ctitle);
	}

	@Ignore
	@Test
	public void test_12_verifyCurrentCityState() throws Exception {
		sup.verifyCurrentCityState();
	}

	@Ignore
	@Test
	public void test_13_verifyCurrentWeather() throws Exception {
		sup.verifyCurrentWeather();
	}

	@Ignore
	@Test
	public void test_14_verifyCurrentTemperature() throws Exception {
		sup.verifyTemperature();
	}

	// @Ignore
	@Test
	public void test_15_verifyBackButton() {
		cp.verifyBackButton(titleExpected);
	}

	// @Ignore
	@Test
	public void test_16_verifyCopyright() {
		cm.verifyCopyright(copyrightExpected);
	}

}
