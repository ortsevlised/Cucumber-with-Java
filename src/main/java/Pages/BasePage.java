package Pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Jorge on 17/12/2015.
 */
public class BasePage {
    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    Long timeout = Long.parseLong(System.getProperty("timeout", "15"));

    /**
     * Waits for the element passed in to be clickable
     *
     * @param element
     */
    protected WebElement waitUntilElementIsClickable(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }


    /**
     * Waits for the element passed in to be visible
     *
     * @param element
     */
    protected WebElement waitUntilElementIsVisible(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }
    /**
     * Sets the screen focus on the WebElement passed.
     *
     * @param element
     */
    public WebElement scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        waitUntilElementIsClickable(element);
        return element;
    }
}

