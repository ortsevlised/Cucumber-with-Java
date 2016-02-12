package Pages;

import Util.ElementCriteria;
import Util.Helper;
import Util.MatchingDriver;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static Util.ElementCriteria.isEnabled;
import static Util.ElementCriteria.isNotChecked;
import static org.hamcrest.CoreMatchers.allOf;

public class RegistrationPage {
    private MatchingDriver matchingDriver;


    public RegistrationPage(MatchingDriver matchingDriver) {
        this.matchingDriver = matchingDriver;
    }


    /**
     * Elements
     */
    public WebElement inputGivenName(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.id("registration-first-name"), criteria);
    }

    public WebElement inputLastName(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.id("registration-last-name"), criteria);
    }

    public WebElement inputEmail(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.id("registration-email"), criteria);
    }

    public WebElement inputPassword(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.id("registration-password"), criteria);
    }

    public WebElement checkBoxTCs(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.id("registration-terms-conditions"), criteria);
    }


    public WebElement btnRegister(Matcher<WebElement> criteria) {
        return matchingDriver.scrollToElement(matchingDriver.findElement(By.cssSelector("[type='submit']"), criteria));
    }

    public WebElement btnContinue(Matcher<WebElement> criteria) {
        return matchingDriver.scrollToElement(matchingDriver.findElement(By.linkText("Continue"), criteria));
    }

    public WebElement txtRegistrationEmailError(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.id(("registration-email-error")), criteria);
    }


    /**
     * Fills up the register form
     *
     * @param name
     * @param lastName
     * @param email
     * @param password
     */

    public void registerUser(String name, String lastName, String email, String password) {
        enterGivenName(name);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        checkBoxTCs(allOf(isEnabled(), isNotChecked())).click();
        btnRegister(isEnabled()).click();
    }


    public void enterGivenName(String name) {
        inputGivenName(ElementCriteria.hasEmptyText()).sendKeys(name);
    }

    public void enterLastName(String name) {
        inputLastName(ElementCriteria.hasEmptyText()).sendKeys(name);
    }

    public void enterEmail(String email) {
        inputEmail(ElementCriteria.hasEmptyText()).sendKeys(Helper.generateEmailAddress(email));
    }

    public void enterPassword(String name) {
        inputPassword(ElementCriteria.hasEmptyText()).sendKeys(name);
    }


}
