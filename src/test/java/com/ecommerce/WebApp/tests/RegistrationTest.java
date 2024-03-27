package com.ecommerce.WebApp.tests;

import com.ecommerce.WebApp.Factory.WebDriverFactory;
import com.ecommerce.WebApp.Pages.HomePage;
import com.ecommerce.WebApp.Pages.MyAccountLogin;
import com.ecommerce.WebApp.Pages.RegisterPage;
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

public class RegistrationTest {

    public static WebDriver driver;
    private MyAccountLogin myAccountLogin;
    private static HomePage objHomePage;

    private SignInPage objSignInPage;
    private static RegisterPage registerPage;

    @BeforeTest
    public static void Setup() {
        driver = WebDriverFactory.initDriver(PropertyFileReader.readConfigFile().getProperty("browser"));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TimeOutsProvider.PAGETIMEOUT));

        driver.get(PropertyFileReader.readConfigFile().getProperty("url"));
        objHomePage = new HomePage(WebDriverFactory.getDriver());
        registerPage = new RegisterPage(WebDriverFactory.getDriver());

    }

    @Test(priority = 1)
    public void registerUserWithDuplicateEmail() {
        myAccountLogin = objHomePage.clickMyAcc();
        objHomePage.registerButton();
        registerPage.registerUser("saffin", "sh", "saifsh12786@gmail.com", "telephone", "passtest123");
        registerPage.agreePolicyCheck();
        registerPage.clickContinue();
        String msg = registerPage.getWarning();
        Assert.assertTrue(msg.contains("Warning: E-Mail Address is already registered!"));
    }

    @Test(priority = 2)
    public void registerUserWithValidEmail() {
        myAccountLogin = objHomePage.clickMyAcc();
        objHomePage.registerButton();
        registerPage.registerUser("saffin", "sh", "random", "telephone", "passtest123");
        registerPage.agreePolicyCheck();
        registerPage.clickContinue();
        String m = registerPage.blankAreaMsg("Your Account Has Been Created!");
        Assert.assertTrue(m.contains("Your Account Has Been Created!"));
    }


    @DataProvider(name ="inValidCreds")
    public Object[][] duplicateEmail(){

        return new Object[][] {
                {"invalid@gmail.com","test"}
        };
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }


}
