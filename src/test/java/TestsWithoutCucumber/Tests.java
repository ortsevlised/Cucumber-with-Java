package TestsWithoutCucumber;


import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductDetails;
import Steps.DriverFactory;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Jorge on 17/12/2015.
 */
public class Tests {
    public static final String COLLECTION = "iPhone 6s/6";
    HomePage homePage;
    private WebDriver driver;


    @BeforeTest
    public void beforeScenario() {
        driver = new DriverFactory().getDriver();
    }

    @AfterTest
    public void afterScenario() {
        new DriverFactory().destroyDriver();
    }

    @Test(description = "Adding item to the cart")
    public void addingItemToCart() {
        homePage = new HomePage(driver);
        String[] productDetails = homePage.navigateTo().selectACollection(COLLECTION).selectGenericItem().getProductDetails();
        int cartCounter = homePage.getCartCounter();
        new ProductDetails(driver).addToCart().goToCart();

        Assert.assertThat("The cart counter didn't increase", cartCounter, is(homePage.getCartCounter() - 1));
        Assert.assertThat("The details of the item in the cart don't much the ones selected"
                , new CartPage(driver).getLastItemAdded(), CoreMatchers.equalTo(productDetails));
    }
}
