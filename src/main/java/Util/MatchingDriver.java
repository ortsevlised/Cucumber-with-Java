package Util;

import com.google.common.base.Function;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;


public class MatchingDriver {

    private WebDriver driver;

    public MatchingDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(URI uri) {
        driver.navigate().to(uri.toString());
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement findElement(By criteria) {
        return findElement(criteria, CoreMatchers.any(WebElement.class));
    }

    public WebElement findElement(final By criteria, final Matcher<WebElement> condition) {

        FluentWait<WebDriver> waiter = new WebDriverWait(driver, 5, 100)
                .ignoring(NotFoundException.class);

        return waiter.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver input) {

                WebElement element = input.findElement(criteria);

                if (element == null) {
                    throw new NotFoundException("Didn't find element by " + criteria);
                }

                if (condition.matches(element)) {
                    return element;
                }

                StringDescription description = new StringDescription();
                condition.describeTo(description);

                throw new NotFoundException("Found element by " + criteria + ", but didn't match " + description.toString());

            }
        });
    }

    /**
     * Sets the screen focus on the WebElementFacade passed.
     *
     * @param element
     */
    public WebElement scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
        return element;
    }
}
