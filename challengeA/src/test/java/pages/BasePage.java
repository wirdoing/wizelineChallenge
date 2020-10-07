package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import setUp.CoreMethods;

public class BasePage extends CoreMethods{

    @FindBy(xpath = "//button[contains(text(),'Open Menu')]/parent::div") WebElement HamburgerMenu;
    @FindBy(id = "logout_sidebar_link") WebElement LogoutButton;
    @FindBy(xpath = "//*[local-name() = 'svg']") WebElement ShoppingCartButton;
    @FindBy(className = "subheader") WebElement ShoppingCartPage;


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
