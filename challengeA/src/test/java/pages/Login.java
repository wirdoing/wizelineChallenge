package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Login extends BasePage {

    @FindBy(id = "user-name") By UserNameField;
    @FindBy(id = "password") WebElement PasswordField;
    @FindBy(id = "login-button") WebElement LoginButton;
    @FindBy(id = "last-name") WebElement LastNameField;
    @FindBy(id = "first-name") WebElement FirstNameField;
    @FindBy(id = "postal-code") WebElement ZipCodeField;
    @FindBy(xpath = "//div[@class='inventory_item']") By Items;
    @FindBy(xpath = "//div[@class='cart_item']") WebElement ItemsCheckout;
    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']") WebElement NumberOfObjectsInCart;
    @FindBy(xpath = "//button[contains(text(),'ADD')]") By AddToCartButton;
    @FindBy(xpath = "//a[contains(text(),'CHECKOUT')]") WebElement CheckoutButton;
    @FindBy(xpath = "//input[@value='CONTINUE']") WebElement ContinueButton;
    @FindBy(xpath = "//a[contains(text(),'FINISH')]") WebElement FinishButton;
    @FindBy(className = "error-button") WebElement ErrorMessageLogin;
    @FindBy(className = "product_label") WebElement ProductsPage;
    @FindBy(className = "inventory_item_name") By ItemName;


    public void enterUserName(String UserName){
        sendText(UserNameField, UserName);
    }

    public void eneterPassword(String Password) {
        sendText(PasswordField, Password);
    }

    public void clickLoginButton() {
        clickElement(LoginButton);
    }

    public boolean isErrorMessageDisplayed(){
        return isElementDisplayed(ErrorMessageLogin);
    }

    public void verifyProductsPageIsOpen() {
        WaitElementToBePresent(ProductsPage);
    }

    public void verifyLoginPageIsOpen(){
        WaitElementToBePresent(UserNameField);
    }

    public List <WebElement> listOfItems(){
        WaitElementToBePresent(Items);
        List<WebElement> itemList = getDriver().findElements(Items);
        return itemList;
    }

    public List <WebElement> listOfButtons(){
        WaitElementToBePresent(Items);
        List<WebElement> itemList = getDriver().findElements(Items);
        return itemList;
    }

    public List <WebElement> listOfItemsInCheckout(){
        WaitElementToBePresent(Items);
        List<WebElement> itemList = getDriver().findElements(Items);
        return itemList;
    }

    public void clickItemToAdd(int n) {
        listOfItems().get(n-1).findElement(AddToCartButton).click();
    }
    public void clickItemToAdd(int n, int s) {
       listOfButtons().get(n-1).findElement(AddToCartButton).click();
        listOfButtons().get(s-1).findElement(AddToCartButton).click();
    }

    public String getItemName(int n) {
        return listOfItems().get(n-1).findElement(ItemName).getText();
    }

    public String getItemNameInCart(int n) {
        return getDriver().findElements(ItemName).get(n-1).getText();
    }

    public void verifyNumberItemWasAdded(String number) {
        Assert.assertEquals(getText(NumberOfObjectsInCart), number);
    }

    public void click2ElementsToAdd(){
        try {
            listOfItems().get(0).click();
            listOfItems().get(1).click();
        } catch (Exception e) {
            Assert.fail("No elements were found");
        } 
    }

    public void clickCheckoutButton() {
        clickElement(CheckoutButton);
    }

    public void fillNameField(String Name){
        sendText(FirstNameField, Name);
    }

    public void fillLastNameField(String LastName){
        sendText(LastNameField, LastName);
    }

    public void fillZipCode(String ZipCode) {
        sendText(ZipCodeField, ZipCode);
    }

    public void clickContinueButton(){
        clickElement(ContinueButton);
    }

    public void clickFinishButton(){
        clickElement(FinishButton);
    }
}
