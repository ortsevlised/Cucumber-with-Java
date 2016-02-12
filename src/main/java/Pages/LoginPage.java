package Pages;

import Util.ElementCriteria;
import Util.Helper;
import Util.MatchingDriver;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URI;

import static Pages.Constants.*;

import static Util.ElementCriteria.hasText;
import static Util.ElementCriteria.isEnabled;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class LoginPage {

    private MatchingDriver matchingDriver;

    public LoginPage(MatchingDriver matchingDriver) {
        this.matchingDriver = matchingDriver;
    }


    /**
     * Elements
     */
    public WebElement loginBtn(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.cssSelector("[type='submit']"), criteria);
    }

    public WebElement loginField(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.id("login-email"), criteria);
    }

    public WebElement inputPassword(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.id("login-password"), criteria);
    }

    public WebElement userDetails(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.xpath("html/body"), criteria);
    }

    public WebElement logOutBtn(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.linkText("Logout"), criteria);
    }

    public WebElement alertInvalidError(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.cssSelector("#login .alert-type-error"), criteria);
    }

    public WebElement linkResetPassword(Matcher<WebElement> criteria) {
        return matchingDriver.findElement(By.linkText("Need to reset your password?"), criteria);
    }


    /**
     * Logging user in
     *
     * @param scope    (Email Profile or Profile)
     * @param login    (username)
     * @param password (inputPassword)
     */
    public void logInUserWith(String scope, String login, String password) {
        if (scope.equals(EMAIL_PROFILE)) {
            selectLogInWithEmailScope();
        }
        if (scope.equals(PROFILE)) {
            selectLogInWithProfileScope();
        }

        assertThat("Page title should be " + LOGIN_PAGE_TITLE, getPageTitle(), is(LOGIN_PAGE_TITLE));
        fillOutAndSubmitLogInForm(login, password);
    }

    public void fillOutAndSubmitLogInForm(String login, String password) {
        enterLogin(login);
        enterPassword(password);
        matchingDriver.scrollToElement(loginBtn(isEnabled())).click();
    }


    /**
     * Verifies the fields once the user is logged in
     *
     * @param email    (user email)
     * @param verified (true/false or empty)
     */
    public void verifyLoggedInScreen(String email, String verified) {
        if (!email.isEmpty()) {
            email = " " + email;
        }
        if (!verified.isEmpty()) {
            verified = " " + verified;
        }
        userDetails(hasText(containsString("email:" + email)));
        userDetails(hasText(containsString("has your email been verified?:" + verified)));
        logOutBtn(isEnabled());
    }


    /**
     * Logs the user out via the log out button or the log out url
     *
     * @param mean
     */
    public void logOutUsing(String mean) {
        if (mean.equals(BUTTON)) {
            logOutBtn(isEnabled()).click();
        } else if (mean.equals(URL)) {
            matchingDriver.navigateTo(LOGOUT_URL);
        }
        userDetails(hasText(containsString(MOCK_AUTHOR_SUBMISSIONS)));

    }


    public void navigateToMockClient() {
        matchingDriver.navigateTo(URI.create("http://testid-www.nature.com/index"));
    }

    public String getPageTitle() {
        return matchingDriver.getDriver().getTitle();
    }

    public void selectLogInWithEmailScope() {
        matchingDriver.findElement(By.xpath("//*[p='Log in with id.nature.com requesting email profile scope']")).click();
    }

    public void selectLogInWithProfileScope() {
        matchingDriver.findElement(By.xpath("//*[p='Log in with id.nature.com requesting profile scope']")).click();
    }


    public void enterLogin(String login) {
        loginField(ElementCriteria.hasEmptyText()).sendKeys(login);
    }

    public void enterPassword(String login) {
        inputPassword(ElementCriteria.hasEmptyText()).sendKeys(login);
    }

    public void selectCreateAccount() {
        matchingDriver.findElement(By.linkText("Create an account")).click();
    }


}
