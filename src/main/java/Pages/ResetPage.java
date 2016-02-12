package Pages;

import Util.ElementCriteria;
import Util.Helper;
import Util.MatchingDriver;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

import static Util.ElementCriteria.hasEmptyText;
import static Util.ElementCriteria.isEnabled;
import static Util.ElementCriteria.isNotChecked;
import static org.hamcrest.CoreMatchers.allOf;

public class ResetPage {
    private MatchingDriver matchingDriver;


    public ResetPage(MatchingDriver matchingDriver) {
        this.matchingDriver = matchingDriver;
    }


    /**
     * Elements
     */
    public WebElement inputEmail(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.id("reset-password-email"), criteria);
    }

    public WebElement btnSendEmail(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.cssSelector("[type='submit']"), criteria);
    }

    public WebElement txtResetBodyMessage(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.cssSelector(".card.clear p"), criteria);
    }
    public WebElement inputPassword(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.id("new-password"), criteria);
    }

    public WebElement btnSetPassword(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.cssSelector("[type='submit']"),criteria);
    }

    /**
     *
     */

    public void enterEmail(String email) {
        inputEmail(ElementCriteria.hasEmptyText()).sendKeys(email);
    }

    /**
     * Enter new password
     * @param password
     */
    public void enterNewPassword(String password) {
        inputPassword(allOf(isEnabled(),hasEmptyText())).sendKeys(password);
    }
}
