package com.ecommerce.WebApp.Pages;

import com.ecommerce.WebApp.Base.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class RegisterPage extends BasePage {

    private By fName = By.name("firstname");
    private By lName = By.name("lastname");
    private By email = By.name("email");
    private By telephone = By.name("telephone");
    private By password = By.name("password");
    private By confrmPass = By.name("confirm");
    private By agreePolicy = By.xpath("//*[@for='input-agree']");
    private By continueBtn = By.xpath("//*[@value='Continue']");

    private By warningMsg = By.xpath("//*[contains(@class,'alert-dismissible')]");

    private By blankAreaMsg = By.xpath("//*[contains(text(),'%s)]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public String blankAreaMsg(String msg){

        By b = By.xpath(String.format("//h1[contains(text(),'%s')]",msg));
        waitForElementToBeClickable(b);
        return getElement(b).getText();
    }
    public WebElement getMyAccLink() {
        waitForElementToBeClickable(fName);
        return getElement(fName);
    }

    public void registerUser(String fname, String lname, String email, String telephone, String pass) {
        getMyAccLink().sendKeys(fname);
        getElement(lName).sendKeys(lname);
        if (email.contains("random")) {
            logger.info("Generating random email for registration");
            getElement(this.email).sendKeys(generateRandomEmail(5));
        } else {
            getElement(this.email).sendKeys(email);
        }
        getElement(this.telephone).sendKeys(telephone);
        getElement(this.password).sendKeys(pass);
        getElement(this.confrmPass).sendKeys(pass);
    }

    public static String generateRandomEmail(int length) {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        return "username"+ randomInt +"@gmail.com";
    }

    public void agreePolicyCheck() {
        getElement(agreePolicy).click();
    }

    public void clickContinue() {
        getElement(continueBtn).click();
    }

    public WebElement geWarningMsg() {
        waitForElementToBePresent(warningMsg);
        return getElement(warningMsg);
    }

    public String getWarning() {
        return geWarningMsg().getText();
    }




}
