package Pages;

import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URI;


public class LoginPage {

    private MatchingDriver driver;

    public LoginPage(MatchingDriver driver) {
        this.driver = driver;
    }


    /**
     * Elements
     */
    public WebElement loginBtn(Matcher<WebElement> criteria) {
        return driver.findElement(By.cssSelector("[type='submit']"), criteria);
    }

    public WebElement loginField(Matcher<WebElement> criteria) {
        return driver.findElement(By.id("login-email"), criteria);
    }

    public WebElement userDetails(Matcher<WebElement> criteria) {
        return driver.findElement(By.xpath("html/body"),criteria);
    }

    public WebElement logOutBtn(Matcher<WebElement> criteria){
        return driver.findElement(By.linkText("Logout"),criteria);
    }

    /**
     *
     */


    public void navigateToMockClient() {
        driver.navigateTo(URI.create("http://testid-www.nature.com/index"));
    }

    public void selectLogInWithEmailScope() {
        driver.findElement(By.xpath("//*[p='Log in with id.nature.com requesting email profile scope']")).click();
    }

    public void enterPassword(String password) {
        driver.findElement(By.id("login-password")).sendKeys(password);
    }

    public void enterLogin(String login) {
        loginField(ElementCriteria.hasEmptyText()).sendKeys(login);
    }


}
