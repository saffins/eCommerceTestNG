package com.ecommerce.WebApp.Pages;

import com.ecommerce.WebApp.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountLogin extends BasePage {

    public MyAccountLogin(WebDriver driver) {
        super(driver);

    }
    private By email = By.name("email");
    private By pass = By.name("password");
    private By signInButton=By.xpath("//*[@value='Login']");

    private By blankAreaMsg = By.xpath("//*[contains(text(),'%s)]");

    public WebElement getPassword() {
        waitForElementToBePresent(pass);
        return getElement(pass);
    }


    public String signInWithCredentials(String email,String pass,String expectedMsg) {

        getEmailAddress().sendKeys(email);
        getPassword().sendKeys(pass);
        getSignInButton().click();
        return blankAreaMsg(expectedMsg).getText();
    }

    public WebElement getEmailAddress() {
        waitForElementToBePresent(email);
        return getElement(email);
    }
    public WebElement getSignInButton() {
        waitForElementToBePresent(signInButton);
        return getElement(signInButton);
    }

    public WebElement blankAreaMsg(String msg){
        By b = By.xpath(String.format("//*[contains(text(),'%s')]",msg));
        waitForElementToBeClickable(b);
        return getElement(b);
    }

}
