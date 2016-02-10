package Tests.Login;

import Pages.LoginPage;
import Pages.MatchingDriver;
import Pages.WebDriverFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static Pages.ElementCriteria.isDisabled;
import static Pages.ElementCriteria.isEnabled;

public class James {

    static MatchingDriver matchingDriver = new MatchingDriver(WebDriverFactory.webdriver());
    static LoginPage loginPage;


    @BeforeTest
    public static void startUpTest() {
        loginPage = new LoginPage(matchingDriver);
        loginPage.navigateToMockClient();
    }


    @Test(dataProvider="providerEmailAndPassword")
    public void testLoginButtonIsDisabledWhenUsernameOrPasswordIsEmpty(String email,String password) throws Exception {
        logInUserWithEmailScope(email, password);
        loginPage.loginButton(isDisabled());
    }


    @Test
    public void testSuccesfulLogOut() throws Exception {
        logInUserWithEmailScope("identity11@gmail.com", "password12");
        loginPage.loginButton(isDisabled());

    }


    private void logInUserWithEmailScope(String login, String password) {
        loginPage.selectLogInWithEmailScope();
        loginPage.enterLogin(login);
        loginPage.enterPassword(password);
        loginPage.loginButton(isEnabled()).click();
    }

    private void assertLoginButtonIsDisabledForLoginAndPassword(String login, String password) {

        //page.loginButton(allOf(isDisabled(), hasText(equalTo("Invalid!"))));
    }

    @DataProvider
    public static Object[][] providerEmailAndPassword() {
        return new Object[][] {
                { "email@mail.com", "" },
                { "","password12" }
        };
    }

}
