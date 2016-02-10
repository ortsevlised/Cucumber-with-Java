package Tests.Login;

import Pages.LoginPage;
import Pages.MatchingDriver;
import Pages.WebDriverFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static Pages.ElementCriteria.hasText;
import static Pages.ElementCriteria.isDisabled;
import static Pages.ElementCriteria.isEnabled;
import static org.hamcrest.CoreMatchers.containsString;

public class James {

    public static final String LOGIN_EMAIL = "identity11@gmail.com";
    public static final String LOGIN_PASSWORD = "password12";
    static MatchingDriver matchingDriver = new MatchingDriver(WebDriverFactory.webdriver());
    static LoginPage loginPage;


    @BeforeMethod
    public static void startUpTest() {
        loginPage = new LoginPage(matchingDriver);
        loginPage.navigateToMockClient();
    }


    @Test(dataProvider = "providerEmailAndPassword")
    public void testLoginButtonIsDisabledWhenUsernameOrPasswordIsEmpty(String email, String password) throws Exception {
        logInUserWithEmailScope(email, password);
        loginPage.loginBtn(isDisabled());
    }


    @Test
    public void testSuccesfulLogOut() throws Exception {
        logInUserWithEmailScope(LOGIN_EMAIL, LOGIN_PASSWORD);
        loginPage.userDetails(hasText(containsString("email: " + LOGIN_EMAIL)));
        loginPage.userDetails(hasText(containsString("has your email been verified?: false")));
        loginPage.logOutBtn(isEnabled()).click();
        loginPage.userDetails(hasText(containsString("Mock Author Submissions")));
    }


    private void logInUserWithEmailScope(String login, String password) {
        loginPage.selectLogInWithEmailScope();
        loginPage.enterLogin(login);
        loginPage.enterPassword(password);
        loginPage.loginBtn(isEnabled()).click();
    }

    @DataProvider
    public static Object[][] providerEmailAndPassword() {
        return new Object[][]{
                {LOGIN_EMAIL, ""},
                {"", LOGIN_PASSWORD}
        };
    }

}
