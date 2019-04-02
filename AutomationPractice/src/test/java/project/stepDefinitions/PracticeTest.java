package project.stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PracticeTest 
{
	private static final Logger LOGGER	= Logger.getLogger(PracticeTest.class);
	
	/**
     * Browser timeout.
     */
    private static final long   TIMEOUT	= 10;
    
    /**
     * PRACTICE_SITE.
     */
    public static final String  PRACTICE_SITE	= "http://automationpractice.com/index.php";
    
	private WebDriver driver;
	WebDriverWait wait;

	/**
	 * Cucumber Before class.
	 * @param scenario
	 */
	@Before
	public void before(Scenario scenario) {
		
		LOGGER.info("Starting Scenario- " + scenario.getName());
	}
	
	/**
	 * Opens the PRACTICE_SITE site.
	 * @throws Throwable
	 */
	@Given("^practice site is open$")
	public void practice_site_is_open() throws Throwable {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        
        LOGGER.info("Connecting to : " + PRACTICE_SITE);
        driver.get(PRACTICE_SITE);
	}

	/**
	 * Registers and user in the Automation Practice website.
	 * @throws Throwable
	 */
	@Then("^register an user$")
	public void register_an_user() throws Throwable {
		
		wait = new WebDriverWait(driver, 30);
		
		LOGGER.info("Click on Sign In");
		driver.findElement(By.partialLinkText("Sign in")).click();
		
		LOGGER.info("Check if login page is opened");
		assertEquals("Login page NOT opened", "Login - My Store", driver.getTitle());
		
		LOGGER.info("Enter Email Adress");
		driver.findElement(By.id("email_create")).sendKeys("aFirst.aLast@IT.com");
		
		LOGGER.info("Click on Create an Account button");
		driver.findElement(By.id("SubmitCreate")).click();
			
		LOGGER.info("Check if the registration page opens");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
		assertEquals("Registration page NOT opened", "CREATE AN ACCOUNT", driver.findElement(By.xpath("//*[@id=\"noSlide\"]/h1")).getText());
		
		LOGGER.info("Entering Registration Details");
		LOGGER.info("Selecting Title");
		driver.findElement(By.id("id_gender1")).click();
		
		LOGGER.info("Entering First Name");
		driver.findElement(By.id("customer_firstname")).sendKeys("abcdFirst");
		
		LOGGER.info("Entering Last Name");
		driver.findElement(By.id("customer_lastname")).sendKeys("abcdLast");
		
		LOGGER.info("Entering Password");
		driver.findElement(By.id("passwd")).sendKeys("abcd1234");
		
		LOGGER.info("Entering Company");
		driver.findElement(By.id("company")).sendKeys("IT");
		
		LOGGER.info("Entering Address");
		driver.findElement(By.id("address1")).sendKeys("1, first line");
		
		LOGGER.info("Entering City");
		driver.findElement(By.id("city")).sendKeys("Newcastle");
		
		LOGGER.info("Entering State");
		Select dropdown = new Select(driver.findElement(By.id("id_state")));
		dropdown.selectByVisibleText("Colorado");
		
		LOGGER.info("Entering Postcode");
		driver.findElement(By.id("postcode")).sendKeys("95672");
		
		LOGGER.info("Entering phone_mobile");
		driver.findElement(By.id("phone_mobile")).sendKeys("1234567890");
		
		LOGGER.info("Entering alias");
		driver.findElement(By.id("alias")).sendKeys("My Address");
		
		LOGGER.info("Click on Register Button");
		driver.findElement(By.id("submitAccount")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women")));
			
		LOGGER.info("Check if the User resistration is successful");
		assertEquals("Registration NOT Successful", "MY ACCOUNT", driver.findElement(By.tagName("h1")).getText());
	}
	
	
	/**
	 * Logs out the new user form Automation Practice website.
	 * @throws Throwable
	 */
	@Then("^logout the new user$")
	public void logout_user() throws Throwable {
		LOGGER.info("CLick on the Sign out link");
		driver.findElement(By.linkText("Sign out")).click();
	}
	
	
	/**
	 * Login the new user in the Automation Practice website.
	 * @throws Throwable
	 */
	@Then("^login back gain with the new user$")
	public void login_user() throws Throwable {
		wait = new WebDriverWait(driver, 30);
		
		LOGGER.info("Click on Sign In");
		driver.findElement(By.partialLinkText("Sign in")).click();
		
		LOGGER.info("Enter Username");
		driver.findElement(By.id("email")).sendKeys("aFirst.aLast@IT.com");
		
		LOGGER.info("Enter Password");
		driver.findElement(By.id("passwd")).sendKeys("abcd1234");
		
		LOGGER.info("Click Submit");
		driver.findElement(By.id("SubmitLogin")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women")));
		
		LOGGER.info("Check if the Login is successful");
		assertEquals("Login NOT Successful", "MY ACCOUNT", driver.findElement(By.tagName("h1")).getText());
	}
	
	/**
	 * Add item to cart.
	 * @throws Throwable
	 */
	@And("^add item to cart$")
	public void add_item() throws Throwable {
		LOGGER.info("Click on Link 'Women'");
		driver.findElement(By.linkText("Women")).click();
		
		LOGGER.info("Entering State");
		Select dropdown = new Select(driver.findElement(By.id("selectProductSort")));
		dropdown.selectByVisibleText("Price: Highest first");
		Thread.sleep(20000);
		
		LOGGER.info("Add Item to cart");
		Actions actions = new Actions(driver);
		WebElement item = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img"));
		actions.moveToElement(item);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[1]")).click();		
	}
	
	
	/**
	 * Validate Cart.
	 * @throws Throwable
	 */
	@Then("^validate cart$")
	public void validate() throws Throwable {
		System.out.println(driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/span[1]")).getText());
		assertEquals("Login NOT Successful", "1", driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/span[1]")).getText());
	}
	
	
	@After
	public void after(Scenario scenario) {
		LOGGER.info(scenario.getName() + " Status - " + scenario.getStatus());
	}
}
