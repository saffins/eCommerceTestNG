package com.ecommerce.WebApp.tests;

import com.ecommerce.WebApp.Factory.WebDriverFactory;
import com.ecommerce.WebApp.Pages.*;
import com.ecommerce.WebApp.Utilities.PropertyFileReader;
import com.ecommerce.WebApp.Utilities.TimeOutsProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartCheckoutTest {

    public static WebDriver driver;
    private MyAccountLogin myAccountLogin;
    private static HomePage objHomePage;

    private SignInPage objSignInPage;
    private static RegisterPage registerPage;

    private static ProductPage productPage;

    @BeforeTest
    public static void Setup() {
        driver = WebDriverFactory.initDriver(PropertyFileReader.readConfigFile().getProperty("browser"));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TimeOutsProvider.PAGETIMEOUT));

        driver.get(PropertyFileReader.readConfigFile().getProperty("url"));
        objHomePage = new HomePage(WebDriverFactory.getDriver());
        registerPage = new RegisterPage(WebDriverFactory.getDriver());
        productPage = new ProductPage(WebDriverFactory.getDriver());

    }

    @Test(priority = 1,dataProvider ="validCreds")
    public void loginWithValidCreds(String email, String password){
        myAccountLogin = objHomePage.clickMyAcc();
        String expectedMsg = "Edit your account information";
        String actual = myAccountLogin.signInWithCredentials(email, password, expectedMsg);
        Assert.assertEquals(expectedMsg, actual);
    }

    @Test(priority = 2)
    public void searchProduct(){
        objHomePage.searchProduct("htc touch hd");
    }

    @Test(priority = 3)
    public void clickSearchedProductHyperlink(){
        objHomePage.clickSearchedProduct();
    }

    @Test(priority = 4)
    public void clickAddToCart(){
        productPage.clickAddToCart();
        String msg = productPage.getItemAddedToastMsg();
        Assert.assertTrue(msg.contains("Success: You have added"));
    }

    @Test(priority = 5)
    public void removeItemFromCart(){
        productPage.viewCart();
        productPage.removeItem();
        String ms = productPage.blankAreaMsg("Your shopping cart is empty!");
        Assert.assertTrue(ms.contains("Your shopping cart is empty!"));
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
