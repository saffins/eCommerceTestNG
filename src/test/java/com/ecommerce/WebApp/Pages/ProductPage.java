package com.ecommerce.WebApp.Pages;

import com.ecommerce.WebApp.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private By addToCartBtn = By.xpath("(//*[@title='Add to Cart'])[2]");

    private By itemAddedToast = By.xpath("//*[@class='toast-body']//p");

    private By viewCartBtn = By.xpath("//*[contains(text(),'View Cart')]");

    private By removeItemBtn = By.xpath("//*[@title='Remove']");

    public void removeItem(){
        waitForElementToBeClickable(removeItemBtn);
        getElement(removeItemBtn).click();
    }
    public void viewCart(){
        getElement(viewCartBtn).click();
    }
    public void clickAddToCart(){
        waitForElementToBeClickable(addToCartBtn);
        getElement(addToCartBtn).click();
    }
    public String blankAreaMsg(String msg){

        By b = By.xpath(String.format("(//p[contains(text(),'%s')])[2]",msg));
        waitForElementToBeClickable(b);
        return getElement(b).getText();
    }

    public String getItemAddedToastMsg(){
        waitForElementToBePresent(itemAddedToast);
        return getElement(itemAddedToast).getText();
    }
}
