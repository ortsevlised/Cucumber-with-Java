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

    public void navigateToMockClient() {
        driver.navigateTo(URI.create("http://testid-www.nature.com/index"));
    }

    public WebElement loginButton(Matcher<WebElement> criteria) {
        return driver.findElement(By.cssSelector("[type='submit']"), criteria);
    }

    public void enterPassword(String password) {
        driver.findElement(By.id("login-password")).sendKeys(password);
    }

    public void enterLogin(String login) {
        loginField(ElementCriteria.hasEmptyText()).sendKeys(login);
    }

    public WebElement loginField(Matcher<WebElement> criteria) {
        return driver.findElement(By.id("login-email"), criteria);
    }

    public void selectLogInWithEmailScope() {
        driver.findElement(By.xpath("//*[p='Log in with id.nature.com requesting email profile scope']")).click();
    }
}
