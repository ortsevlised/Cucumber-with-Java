package Pages;

import Util.Helper;
import Util.MatchingDriver;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

import static Util.ElementCriteria.isEnabled;

public class GenericPage {

    public static final int NUMBER_OF_RETRIES = 10;
    private MatchingDriver matchingDriver;

    public GenericPage(MatchingDriver matchingDriver) {
        this.matchingDriver = matchingDriver;
    }


    /**
     * Elements
     */

    public WebElement txtHeaderMessage(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.cssSelector(".mt10.mb30"), criteria);
    }

    public WebElement txtBodyMessage(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.cssSelector(".mb30 p:first-of-type"), criteria);
    }

    public WebElement getWebElementNoEmail() {
        return matchingDriver.findElement(By.id("noemailmsg"));
    }

    public WebElement linkEmail(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.cssSelector("#mailcontainer li:first-of-type a"), criteria);
    }

    public WebElement linkEmailAction(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.cssSelector((".mailview p:nth-child(4) a")), criteria);
    }


    /**
     * Verify Registration Email
     *
     * @throws Throwable
     */
    public void verifyEmail() throws Throwable {
        navigateToMailinator();
        openEmail();
        clickEmailActivateLink();
    }

    public void navigateToMailinator() throws Exception {
        String[] parts = Helper.get(Helper.EMAIL_KEY).split("@");
        String part1 = parts[0];
        matchingDriver.getDriver().navigate().to("http://mailinator.com/inbox.jsp?to=" + part1 + "#/#maildirpane");
    }

    public void openEmail() {
        for (int i = 0; i < NUMBER_OF_RETRIES; i++) {
            if (getWebElementNoEmail().isDisplayed()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                matchingDriver.getDriver().navigate().refresh();
            } else {
                linkEmail(isEnabled()).click();
                break;
            }
        }
    }


    public void clickEmailActivateLink() throws Throwable {
        matchingDriver.getDriver().switchTo().frame(matchingDriver.findElement(By.cssSelector("iframe[name='rendermail']")));
        linkEmailAction(isEnabled()).click();
        Set<String> windowids = matchingDriver.getDriver().getWindowHandles();
        Iterator<String> iter = windowids.iterator();
        iter.next();
        String tabbedWindowId = iter.next();
        matchingDriver.getDriver().close();
        matchingDriver.getDriver().switchTo().window(tabbedWindowId);
        matchingDriver.getDriver().manage().window().maximize();
    }
}
