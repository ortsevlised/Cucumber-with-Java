package Util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static Pages.Constants.*;

public class Helper {

    static String randomString = RandomStringUtils.randomAlphabetic(5);
    static int uniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
    public static String EMAIL_KEY = "email";
    public static String URL_KEY = "url";
    public static String CSRF_TOKEN = "csrf_token";
    private static Map<String, String> storage = new ConcurrentHashMap<String, String>();

    /**
     * Saves a key in the session
     * @param key
     * @param value
     */

    public static void add(String key, String value) {
        storage.put(key, value);
    }

    public static String get(String key) {
        return storage.get(key);
    }


    /**
     * Generates email
     * @param email
     * @return
     */
    public static String generateEmailAddress(String email) {
        if (email.equals("test_identity_verified@mailinator.com") || email.equals("test_identity_unverified@mailinator.com")) {
        } else {
            if (email.equals(GENERATE_EMAIL)) {
                String randomEmail = "test_identity_" + randomString + String.valueOf(uniqueId) + "@mailinator.com";
                email = randomEmail.toLowerCase();
            } else if (email.contains("test_identity_")) {
                email = email.substring(15);
                String randomValue = "test_identity_" + randomString + String.valueOf(uniqueId);
                email = randomValue.toLowerCase() + email;
            }
        }
        add(EMAIL_KEY, email);
        return email;
    }


}
