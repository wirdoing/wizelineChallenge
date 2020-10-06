package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.CSVDataProvider;
import pages.Login;
import setUp.SetUp;

public class LoginTests extends SetUp {

    @Test(dataProvider = "credentials",dataProviderClass = CSVDataProvider.class)
    public void loginWithValidUser(String User, String Password) {
        Login login = new Login();

        login.enterUserName(User);
        login.eneterPassword(Password);
        login.clickLoginButton();
        login.verifyProductsPageIsOpen();
    }

    @Test
    public void loginWithInvalidUser() {
        Login login = new Login();

        login.enterUserName("user");
        login.eneterPassword("sauce");
        login.clickLoginButton();
        Assert.assertTrue(login.isErrorMessageDisplayed());
    }

    @Test
    public void logoutFromProductPage() {
        Login login = new Login();

        login.enterUserName("standard_user");
        login.eneterPassword("secret_sauce");
        login.clickLoginButton();
        login.clickMenu();
        login.clickLogoutButton();
        login.verifyLoginPageIsOpen();
    }

    @Test
    public void navigateToShoppingCart() {
        Login login = new Login();

        login.enterUserName("standard_user");
        login.eneterPassword("secret_sauce");
        login.clickLoginButton();
        login.clickShoppingCart();
        login.validateShoppingCartIsOpen();
    }

    @Test
    public void addSingleItemToCart() {
        Login login = new Login();

        login.enterUserName("standard_user");
        login.eneterPassword("secret_sauce");
        login.clickLoginButton();
        login.clickItemToAdd(1);
        String elementAdded = login.getItemName(1);
        login.clickShoppingCart();
        String elementDisplayed = login.getItemNameInCart(1);
        Assert.assertEquals(elementAdded, elementDisplayed);

    }

    @Test
    public void addMultipleItemToCart() {
        Login login = new Login();

        login.enterUserName("standard_user");
        login.eneterPassword("secret_sauce");
        login.clickLoginButton();
        login.clickItemToAdd(1,3);
        String elementAdded1 = login.getItemName(1);
        String elementAdded2 = login.getItemName(2);
        login.clickShoppingCart();
        String elementDisplayed1 = login.getItemNameInCart(1);
        String elementDisplayed2 = login.getItemNameInCart(2);
        Assert.assertEquals(elementAdded1, elementDisplayed1);
        Assert.assertEquals(elementAdded2, elementDisplayed2);
    }

    @Test
    public void fillUserInformation() {
        Login login = new Login();

        addSingleItemToCart();
        login.clickCheckoutButton();
        login.fillNameField("Name");
        login.fillLastNameField("LastName");
        login.fillZipCode("12345");
        login.clickContinueButton();
        login.validateOverviewIsOpen();

    }

    @Test
    public void finalOrderItems() {
        Login login = new Login();

        login.enterUserName("standard_user");
        login.eneterPassword("secret_sauce");
        login.clickLoginButton();
        login.clickItemToAdd(1,2);
        String elementAdded1 = login.getItemName(1);
        String elementAdded2 = login.getItemName(2);
        login.clickShoppingCart();
        login.clickCheckoutButton();
        login.fillNameField("Name");
        login.fillLastNameField("LastName");
        login.fillZipCode("12345");
        login.clickContinueButton();
        String elementDisplayed1 = login.getItemNameInCart(1);
        String elementDisplayed2 = login.getItemNameInCart(2);
        Assert.assertEquals(elementAdded1, elementDisplayed1);
        Assert.assertEquals(elementAdded2, elementDisplayed2);
    }

    @Test
    public void completePurchase() {
        Login login = new Login();

        login.enterUserName("standard_user");
        login.eneterPassword("secret_sauce");
        login.clickLoginButton();
        login.clickItemToAdd(1,2);
        login.clickShoppingCart();
        login.clickCheckoutButton();
        login.fillNameField("Name");
        login.fillLastNameField("LastName");
        login.fillZipCode("12345");
        login.clickContinueButton();
        login.clickFinishButton();
        login.validateConfirmationIsOpen();
       
    }
}
