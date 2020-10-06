package setUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CoreMethods {

    static WebDriver driver;

    public CoreMethods(){
        driver = SetUp.GetDriver();
    }

    public static WebElement WaitElementToBePresent(By locator){
        WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(locator));
        return (WebElement) element;
    }

    public static WebElement WaitElementToBeClickable(By locator){
        WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(locator));
        return (WebElement) element;
    }

    public static void clickElement(By locator) {
        WaitElementToBeClickable(locator).click();
    }

    public static void elementIsDisplayed(By locator) {
        WaitElementToBeClickable(locator).isDisplayed();
    }

    public static Boolean isElementDisplayed(By locator) {
        return WaitElementToBeClickable(locator).isDisplayed();
    }

    public static void sendText(By locator, String text){
        WaitElementToBeClickable(locator).sendKeys(text);
    }

    public static void clearField(By locator) {
        WaitElementToBeClickable(locator).clear();
    }

    public static String getText(By locator) {
        return WaitElementToBePresent(locator).getText();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
