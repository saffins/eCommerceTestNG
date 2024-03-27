/**
 * 
 */
package com.ecommerce.WebApp.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.ecommerce.WebApp.Base.BasePage;



/**
 * This is the Page class for Home page of the application
 * this class has two parts 
 * first part contains all the By locators of the Home page WebElements
 * and second part contains all the action methods on page 

 *
 */
public class HomePage extends BasePage {

	private By logo=By.cssSelector("img[class='TRRBg _R']");
			
	private By signInLink=By.cssSelector("a[class='rmyCe _G B- z _S c Wc wSSLS w jWkoZ sOtnj']");		
		
	private By signInFrame=By.cssSelector("iframe[class='bFOrV _R B- o']");				

	private By myAccLink = By.xpath("(//div[@class='info']//*[contains(text(),'My account')])[2]");

	private By logoutBtn = By.xpath("(//div[@class='info']//*[contains(text(),'Logout')])");

	private By registerBtn = By.xpath("//a[contains(text(),'Register')]");

	private By searchProductInput = By.name("search");

	private By searchBtn = By.xpath("//*[@class='search-button']/button");

	private By searchedItemHyperlink = By.xpath("//*[@data-view_id='grid']//*[@alt='HTC Touch HD']");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	/**
	 * these are the getters of WebElements these methods wait for perticular webelement to be get displayed and return that webElement to the caller
	 * @return WebElement
	 */
	public void searchProduct(String productName){
		waitForElementToBeClickable(searchProductInput);
		getElement(searchProductInput).sendKeys(productName);
		getElement(searchBtn).click();
	}

	public void clickSearchedProduct(){
		waitForElementToBeClickable(searchedItemHyperlink);
		getElement(searchedItemHyperlink).click();
	}

	public WebElement getRegisterButton() {
		waitForElementToBeClickable(registerBtn);
		return getElement(registerBtn);
	}
	public WebElement getSignInLink() {
		waitForElementToBeClickable(signInLink);
		return getElement(signInLink);
	}

	public void registerButton(){
		getRegisterButton().click();
	}
	public WebElement getMyAccLink(){
		waitForElementToBeClickable(myAccLink);
		return getElement(myAccLink);
	}
	public WebElement getLogo() {
		waitForElementToBePresent(logo);
		return getElement(logo);
	}
	
	public String getTheHomePageTitle()
	{
		waitForPageTitle("Tripadvisor: Over a billion reviews & contributions for Hotels, Attractions, Restaurants, and more");
		return getPageTitle();
	}
	
	public WebElement getSignInFrame() {
		//waitForFrame(signInFrame);
		return getElement(signInFrame);
	}
	
	public boolean isLogoDisplayed()
	{
		return getLogo().isDisplayed();
	}

	public boolean isSignInDisplayed()
	{
		return getSignInLink().isDisplayed();
	}
	
	
	/**
	 * this method click on sign in link
	 * which opens the iframe 
	 * @return SignIn Page object
	 */
	public SignInPage clickOnSignIn()
	{
		getSignInLink().click();
		driver.switchTo().frame(getSignInFrame());
		return new SignInPage(driver);
	}

	public MyAccountLogin clickMyAcc(){
		getMyAccLink().click();

		return new MyAccountLogin(driver);
	}

	public void logout(){
		hoverOnElement(myAccLink);
		getElement(logoutBtn).click();
	}

	public void registerUser(){
		getRegisterButton().click();;
	}



	
	
	
}
