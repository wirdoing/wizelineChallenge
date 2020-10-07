package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import setUp.CoreMethods;

public class BasePage extends CoreMethods{

    By HamburgerMenu = By.xpath("//button[contains(text(),'Open Menu')]/parent::div");
    By LogoutButton = By.id("logout_sidebar_link");
    By ShoppingCartButton = By.xpath("//*[local-name() = 'svg']");
    By ShoppingCartPage = By.className("subheader");


    public void clickMenu() {
        clickElement(HamburgerMenu);
    }

    public void clickLogoutButton() {
        clickElement(LogoutButton);
    }

    public void clickShoppingCart() {
        clickElement(ShoppingCartButton);
    }

    public void validateShoppingCartIsOpen() {
        Assert.assertEquals(getText(ShoppingCartPage), "Your Cart"); 
    }

    public void validateOverviewIsOpen(){
        Assert.assertEquals(getText(ShoppingCartPage), "Checkout: Overview"); 
    }

    public void validateConfirmationIsOpen(){
        Assert.assertEquals(getText(ShoppingCartPage), "Finish"); 
    }
}
