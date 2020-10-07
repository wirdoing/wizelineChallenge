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

    public static WebElement WaitElementToBePresent(WebElement webElement) {
        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated((By) webElement));
        return (WebElement) element;
    }

    public static WebElement WaitElementToBePresent(By locator) {
        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        return (WebElement) element;
    }

    public static WebElement WaitElementToBeClickable(WebElement webElement) {
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(webElement));
        return (WebElement) element;
    }

    public static WebElement WaitElementToBeClickable(By locator) {
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(locator));
        return (WebElement) element;
    }

    public static void clickElement(WebElement webElement) {
        WaitElementToBeClickable(webElement).click();
    }

    public static void elementIsDisplayed(WebElement webElement) {
        WaitElementToBeClickable(webElement).isDisplayed();
    }

    public static Boolean isElementDisplayed(WebElement webElement) {
        return WaitElementToBeClickable(webElement).isDisplayed();
    }

    public static void sendText(By locator, String text){
        WaitElementToBeClickable(locator).sendKeys(text);
    }
    public static void sendText(WebElement webElement, String text){
        WaitElementToBeClickable(webElement).sendKeys(text);
    }

    public static void clearField(WebElement webElement) {
        WaitElementToBeClickable(webElement).clear();
    }

    public static String getText(WebElement webElement) {
        return WaitElementToBePresent(webElement).getText();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
