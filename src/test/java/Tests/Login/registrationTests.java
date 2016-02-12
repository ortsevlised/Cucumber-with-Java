package Tests.Login;

import Pages.GenericPage;
import Pages.LoginPage;
import Util.MatchingDriver;
import Pages.RegistrationPage;
import Util.WebDriverFactory;
import org.testng.annotations.*;

import static Pages.Constants.*;
import static Pages.Constants.GIVEN_NAME;
import static Pages.Constants.LAST_NAME;
import static Util.ElementCriteria.hasText;
import static Util.ElementCriteria.isEnabled;
import static Util.Helper.EMAIL_KEY;
import static Util.Helper.get;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;

public class registrationTests {

    static LoginPage loginPage;
    static GenericPage genericPage;
    static RegistrationPage registrationPage;
    static MatchingDriver matchingDriver;


    @BeforeMethod
    public static void startUpTest() {
        matchingDriver = new MatchingDriver(WebDriverFactory.webdriver());
        loginPage = new LoginPage(matchingDriver);
        loginPage.navigateToMockClient();
    }

    @Test(description = "Registering a new user, check the email is not verified, verifies it, and checks it now reads verified: true")
    public void testRegisterNewUser() throws Throwable {
        genericPage = new GenericPage(matchingDriver);
        loginPage.selectLogInWithEmailScope();
        loginPage.selectCreateAccount();
        registrationPage = new RegistrationPage(matchingDriver);
        registrationPage.registerUser(GIVEN_NAME, LAST_NAME, GENERATE_EMAIL, LOGIN_PASSWORD);
        genericPage.txtHeaderMessage(hasText(equalTo("Welcome " + GIVEN_NAME + " " + LAST_NAME)));
        genericPage.txtBodyMessage(hasText(equalTo(THANKS_REGISTRATION)));
        registrationPage.btnContinue(isEnabled()).click();
        loginPage.verifyLoggedInScreen(get(EMAIL_KEY), "false");
        genericPage.verifyEmail();
        genericPage.txtHeaderMessage(hasText(equalTo(EMAIL_ADDRESS_SUCCESSFULLY_VERIFIED)));
        genericPage.txtBodyMessage(hasText(equalTo(THANKS_VERIFIED)));
        registrationPage.btnContinue(allOf(isEnabled(), hasText(equalTo(CONTINUE))));
        loginPage.navigateToMockClient();
        loginPage.selectLogInWithEmailScope();
        loginPage.verifyLoggedInScreen(get(EMAIL_KEY), "true");
    }

    @Test(dataProvider = "providerEmailsAlreadyUsed",description = "Trying to register a user, with an email already in use. combining verified and unverified emails")
    public void testRegisterEmailAlreadyUsed(String email) throws Throwable {
        loginPage.selectLogInWithEmailScope();
        loginPage.selectCreateAccount();
        registrationPage = new RegistrationPage(matchingDriver);
        registrationPage.registerUser(GIVEN_NAME, LAST_NAME,  email, LOGIN_PASSWORD);
        registrationPage.txtRegistrationEmailError(hasText(equalTo(THIS_EMAIL_ADDRESS_IS_ALREADY_IN_USE)));
    }

    @DataProvider()
    public static Object[][] providerEmailsAlreadyUsed() {
        return new Object[][]{{VERIFIED_EMAIL},
                {UNVERIFIED_EMAIL}};
    }
}