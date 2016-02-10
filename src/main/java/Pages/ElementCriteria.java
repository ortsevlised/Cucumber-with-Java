package Pages;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;


public class ElementCriteria {

    public static Matcher<WebElement> hasEmptyText() {
        return hasText(CoreMatchers.equalTo(""));
    }

    public static Matcher<WebElement> hasText(final Matcher<String> expected) {
        return new TypeSafeMatcher<WebElement>() {
            @Override
            protected boolean matchesSafely(WebElement item) {
                return expected.matches(item.getText());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has text ").appendDescriptionOf(expected);
            }
        };
    }

    public static Matcher<WebElement> isDisabled() {
        return new TypeSafeMatcher<WebElement>() {
            @Override
            protected boolean matchesSafely(WebElement item) {
                return ! item.isEnabled();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is disabled");
            }
        };
    }

    public static Matcher<WebElement> isEnabled() {
        return new TypeSafeMatcher<WebElement>() {
            @Override
            protected boolean matchesSafely(WebElement item) {
                return item.isEnabled();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is enabled");
            }
        };
    }
}
