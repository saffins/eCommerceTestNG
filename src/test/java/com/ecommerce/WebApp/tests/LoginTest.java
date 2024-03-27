package com.ecommerce.WebApp.tests;

import com.ecommerce.WebApp.Factory.WebDriverFactory;
import com.ecommerce.WebApp.Pages.HomePage;
import com.ecommerce.WebApp.Pages.MyAccountLogin;
import com.ecommerce.WebApp.Pages.SignInPage;
import com.ecommerce.WebApp.Utilities.PropertyFileReader;
import com.ecommerce.WebApp.Utilities.TimeOutsProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    public static WebDriver driver;
    private MyAccountLogin myAccountLogin;
    private static HomePage objHomePage;

    private SignInPage objSignInPage;

    @BeforeTest
    public static void Setup(){
        driver= WebDriverFactory.initDriver(PropertyFileReader.readConfigFile().getProperty("browser"));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TimeOutsProvider.PAGETIMEOUT));

        driver.get(PropertyFileReader.readConfigFile().getProperty("url"));
        objHomePage = new HomePage(WebDriverFactory.getDriver());
    }

    @Test(priority = 1,dataProvider ="validCreds")
    public void loginWithValidCreds(String email, String password){
        myAccountLogin = objHomePage.clickMyAcc();
        String expectedMsg = "aa your account information";
        String actual = myAccountLogin.signInWithCredentials(email, password, expectedMsg);
        Assert.assertEquals(expectedMsg, actual);
    }

    @Test(priority = 2)
    public void logout(){
        objHomePage.logout();
    }

    @Test(priority = 3,dataProvider ="inValidCreds")
    public void loginWithInvalidCreds(String email, String password){
        driver.get(PropertyFileReader.readConfigFile().getProperty("url"));

        myAccountLogin = objHomePage.clickMyAcc();
        String actual = myAccountLogin.signInWithCredentials(email, password, "Warning: No match for E-Mail Address and/or Password.");
//        String actual = objSignInPage.getTheErrorMessageOnInvalidCredetials();
        String expected = "Warning";
        Assert.assertTrue(actual.contains(expected));
    }

    @DataProvider(name ="inValidCreds")
    public Object[][] InvalidCredentials(){

        return new Object[][] {
                {"invalid@gmail.com","test"}
        };
    }


    @DataProvider(name ="validCreds")
    public Object[][] credentials(){

        return new Object[][] {
                {"saifsh12786@gmail.com","saffin123"}
        };
    }


    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
