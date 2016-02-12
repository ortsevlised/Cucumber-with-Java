package Tests.Login;

import Pages.*;
import Util.MatchingDriver;
import Util.WebDriverFactory;
import org.testng.annotations.*;


import static Pages.Constants.*;
import static Util.ElementCriteria.*;
import static Util.Helper.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class loginTests {


    public static final String RESET_PASSWORD_CONFIRMATION = "Reset Password Confirmation";
    public static final String CONGRATULATIONS_YOU_HAVE_NOW_RESET_YOUR_PASSWORD = "Congratulations, you have now reset your password.";
    public static final String ENTER_EMAIL_CONNECTED_TO_YOUR_ACCOUNT = "Please enter the email address connected to your account and we will send a link to reset your password.";
    static LoginPage loginPage;
    static ResetPage resetPage;
    static GenericPage genericPage;
    static MatchingDriver matchingDriver;

    @BeforeMethod
    public static void startUpTest() {
        matchingDriver = new MatchingDriver(WebDriverFactory.webdriver());
        loginPage = new LoginPage(matchingDriver);
        loginPage.navigateToMockClient();
        genericPage = new GenericPage(matchingDriver);

    }


    @Test(description = "Testing the user is able to log in with EMAIL PROFILE scope and then log out through the log out button")
    public void testEmailProfileLogInAndLogOutBtn() throws Exception {
        loginPage.logInUserWith(EMAIL_PROFILE, LOGIN_EMAIL, LOGIN_PASSWORD);
        loginPage.verifyLoggedInScreen(LOGIN_EMAIL, "false");
        loginPage.logOutUsing(BUTTON);
    }


    @Test(description = "Testing the user is able to log in with PROFILE scope and then log out through the log out URL")
    public void testProfileLogInAndLogOutURL() throws Exception {
        loginPage.logInUserWith(PROFILE, LOGIN_EMAIL, LOGIN_PASSWORD);
        loginPage.verifyLoggedInScreen("", "");
        loginPage.logOutUsing(URL);
    }


    @Test(description = "Testing the user gets locked after entering a wrong password for 5th time, then unlocks it and logs in and out.")
    public void testUserGetsLocked() throws Throwable {
        resetPage = new ResetPage(matchingDriver);
        loginPage.logInUserWith(EMAIL_PROFILE, EMAIL_FOR_LOCK_TEST, WRONG_PASSWORD);

        if (loginPage.alertInvalidError(isEnabled()).getText().equals(YOUR_ACCOUNT_HAS_BEEN_LOCKED)) {
            unlockAccount();
            loginPage.navigateToMockClient();
            loginPage.logInUserWith(EMAIL_PROFILE, EMAIL_FOR_LOCK_TEST, WRONG_PASSWORD);
        }

        loginPage.alertInvalidError(hasText(equalTo(INVALID_EMAIL_OR_PASSWORD_PLEASE_TRY_AGAIN)));
        for (int i = 0; i < 3; i++) {
            loginPage.fillOutAndSubmitLogInForm(EMAIL_FOR_LOCK_TEST, WRONG_PASSWORD);
            loginPage.alertInvalidError(hasText(equalTo(INVALID_EMAIL_OR_PASSWORD_PLEASE_TRY_AGAIN)));
        }

        loginPage.fillOutAndSubmitLogInForm(EMAIL_FOR_LOCK_TEST, WRONG_PASSWORD);
        loginPage.alertInvalidError(hasText(equalTo(YOUR_ACCOUNT_HAS_BEEN_LOCKED)));
        unlockAccount();

        loginPage.navigateToMockClient();
        loginPage.logInUserWith(EMAIL_PROFILE,EMAIL_FOR_LOCK_TEST,LOGIN_PASSWORD);
        loginPage.verifyLoggedInScreen(EMAIL_FOR_LOCK_TEST, "true");
        loginPage.logOutUsing(BUTTON);

    }

    public void unlockAccount() throws Throwable {
        loginPage.linkResetPassword(isEnabled()).click();
        genericPage.txtHeaderMessage(hasText(equalTo(RESET_PASSWORD)));
        resetPage.txtResetBodyMessage(hasText(equalTo(ENTER_EMAIL_CONNECTED_TO_YOUR_ACCOUNT)));
        resetPage.enterEmail(EMAIL_FOR_LOCK_TEST);
        add(EMAIL_KEY, EMAIL_FOR_LOCK_TEST);
        resetPage.btnSendEmail(isEnabled()).click();
        genericPage.txtHeaderMessage(hasText(equalTo(RESET_PASSWORD)));
        resetPage.txtResetBodyMessage(hasText(equalTo(THANKS_RESET_PASSWORD)));
        genericPage.verifyEmail();
        resetPage.enterNewPassword(LOGIN_PASSWORD);
        resetPage.btnSetPassword(isEnabled()).click();
        genericPage.txtHeaderMessage(hasText(equalTo(RESET_PASSWORD_CONFIRMATION)));
        resetPage.txtResetBodyMessage(hasText(equalTo(CONGRATULATIONS_YOU_HAVE_NOW_RESET_YOUR_PASSWORD)));
    }


    @Test(dataProvider = "providerEmailAndPassword", description = "Testing the login button gets grayed out after " +
            "clicking on log in leaving empty fields")
    public void testLoginButtonIsDisabledWhenUserOrPasswordEmpty(String email, String password) {
        loginPage.logInUserWith(EMAIL_PROFILE, email, password);
        loginPage.loginBtn(isDisabled());
    }


    @DataProvider(name = "providerEmailAndPassword")
    public static Object[][] providerEmailAndPassword() {
        return new Object[][]{{LOGIN_EMAIL, ""},
                {"", LOGIN_PASSWORD}};
    }
}
