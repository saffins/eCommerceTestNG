/**
 * 
 */
package com.ecommerce.WebApp.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ecommerce.WebApp.Base.BasePage;

/**
 * This is the Page class for Sign in frame of the application
 * this class has two parts 
 * first part contains all the By locators of the sign in  frame WebElements
 * and second part contains all the action methods on page 

 *
 */
public class SignInPage extends BasePage{

	private By header=By.xpath("//*[@id='overlayHeader']/div");
	private By continueWithGoogle=By.cssSelector("button[id='googleBtn']");
	private By continueWithFacebook=By.cssSelector("button[id='facebookBtn']");
	private By continueWithEmail=By.cssSelector("button[class='ui_button w100p regEmailContinue newRegFormButtonStyles roundedRegFormButton emailButtonMargin']>span[class='textContainer']");
	private By EmailHeader=By.xpath("//div[@id='regSignIn']/div[2]/div");
	private By emailAddress=By.cssSelector("input[id='regSignIn.email']");
	private By errorMessageOnInvalideCredentials=By.xpath("//*[contains(@class,'alert-dismissible')]");
	private By errorMessageOnBlankEmailAddress=By.cssSelector("div[class='body_text']");

	private By email = By.name("email");
	private By pass = By.name("password");
	private By signInButton=By.xpath("Login");

	private By blankAreaMsg = By.xpath("//*[contains(text(),'%s");

	public SignInPage(WebDriver driver) {
		super(driver);
		
	}
	
	


	public WebElement getContinueWithEmail() {
		//waitForElementToBePresent(continueWithEmail);
		return getElement(continueWithEmail);
//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		WebElement element=(WebElement)js.executeScript("return document.querySelector(\"button[class='ui_button w100p regEmailContinue newRegFormButtonStyles roundedRegFormButton emailButtonMargin']>span[class='textContainer']\")");
//		return element;
	}
		
	public WebElement getEmailAddress() {
		waitForElementToBePresent(email);
		return getElement(email);
	}
	
	public WebElement getPassword() {
		waitForElementToBePresent(pass);
		return getElement(pass);
	}
	
	public WebElement getSignInButton() {
		waitForElementToBePresent(signInButton);
		return getElement(signInButton);
	}
	
	public WebElement getErrorMessageOnInvalideCredentials()
	{
		waitForElementToBePresent(errorMessageOnInvalideCredentials);
		return getElement(errorMessageOnInvalideCredentials);
	}
	
	public WebElement getErrorMessageOnBlankEmailAddress()
	{
		waitForElementToBePresent(errorMessageOnBlankEmailAddress);
		return getElement(errorMessageOnBlankEmailAddress);
	}
	

	
	public String getSignInPageHeader()
	{
		waitForElementToBePresent(header);
		return getPageHeader(header);
	}
	
	
	public void clickOnContinueWithEmail()
	{
		getContinueWithEmail().click();
		
	}

	public String getEmailHeader()
	{
		waitForElementToBePresent(EmailHeader);
		return getPageHeader(EmailHeader);
	}

	public WebElement blankAreaMsg(String msg){
		waitForElementToBeClickable(blankAreaMsg);
		return getElement(blankAreaMsg);
	}
	
	public String signInWithCredentials(String email,String pass,String expectedMsg) {

		getEmailAddress().sendKeys(email);
		getPassword().sendKeys(pass);
		getSignInButton().click();
		return blankAreaMsg(expectedMsg).getText();
	}

	
	public UserHomePage clickOnSignIn() {
		getSignInButton().click();
		return new UserHomePage(driver);
	}


	public String getTheErrorMessageOnInvalidCredetials() {
		
		return getErrorMessageOnInvalideCredentials().getText();
	}







	public String getTheErrorMessageOnBlankEmailAddress() {
		return getErrorMessageOnBlankEmailAddress().getText();
	}



	public String getTheErrorMessageOnBlankPassword() {
		return getErrorMessageOnBlankEmailAddress().getText();
	}
}
