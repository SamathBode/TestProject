package project.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	
	WebDriver driver;
	
	WebElement signInLink = driver.findElement(By.partialLinkText("Sign in"));
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
        PageFactory.initElements(driver, this); 
    }
		
	@FindBy(how = How.ID, using = "sta-cookie-save-all-button")
    private WebElement cookieButton;
	
	public void clickSignIn()
	{
		signInLink.click();
	}
	
}