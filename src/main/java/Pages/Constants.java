package Pages;


import java.net.URI;

public class Constants {

    /**
     * LOGIN PAGE
     */
    public static final String LOGIN_EMAIL = "identity11@gmail.com";
    public static final String EMAIL_FOR_LOCK_TEST = "unlock_testidentity@mailinator.com";
    public static final String LOGIN_PASSWORD = "password12";
    public static final String WRONG_PASSWORD = "wrong_password";
    public static final URI LOGOUT_URL = URI.create("http://testid-www.nature.com/logout");
    public static final String MOCK_AUTHOR_SUBMISSIONS = "Mock Author Submissions";
    public static final String LOGIN_PAGE_TITLE = "Nature.com";
    public static final String EMAIL_PROFILE = "email profile";
    public static final String PROFILE = "profile";
    public static final String GIVEN_NAME = "test";
    public static final String LAST_NAME = "identity";
    public static final String GENERATE_EMAIL = "generate an email";
    public static final String BUTTON = "button";
    public static final String URL = "url";


    /**
     * REGISTRATION PAGE
     */
    public static final String THANKS_REGISTRATION = "Thank you for registering. Please check your inbox to verify your email address.";
    public static final String THANKS_VERIFIED = "Thank you for verifying your email.";
    public static final String EMAIL_ADDRESS_SUCCESSFULLY_VERIFIED = "Email address successfully verified";
    public static final String CONTINUE = "Continue";
    public static final String VERIFIED_EMAIL = "test_identity_verified@mailinator.com";
    public static final String UNVERIFIED_EMAIL = "test_identity_unverified@mailinator.com";
    public static final String THIS_EMAIL_ADDRESS_IS_ALREADY_IN_USE = "This email address is already in use.";


    /**
     * RESET PASSWORD PAGE
     */
    public static final String INVALID_EMAIL_OR_PASSWORD_PLEASE_TRY_AGAIN = "Invalid email or password. Please try again.";
    public static final String YOUR_ACCOUNT_HAS_BEEN_LOCKED = "Your account has been locked due to too many failed log in attempts. Please contact Customer Services for support or reset your password .";
    public static final String RESET_PASSWORD = "Reset Password";
    public static final String THANKS_RESET_PASSWORD = "Thank you. Please check your inbox for an email with instructions on how to reset your password.";
}
