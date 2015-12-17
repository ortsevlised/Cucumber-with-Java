package Steps;

import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductDetails;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;

public class AddToCartSteps {

    private WebDriver driver;
    HomePage homePage;
    int cartCounter;
    String[] productDetails;

    @Before
    public void beforeScenario() {
        driver = new DriverFactory().getDriver();
    }

    @After
    public void afterScenario() {
        new DriverFactory().destroyDriver();
    }


    @Given("^I have added an item to my cart$")
    public void i_have_added_an_item_to_my_cart() throws Throwable {
        homePage = new HomePage(driver);
        productDetails = homePage.navigateTo().selectACollection("iPhone 6s/6").
                selectGenericItem().getProductDetails();
        cartCounter = homePage.getCartCounter();
        new ProductDetails(driver).addToCart();

    }

    @When("^I view the contents of my cart$")
    public void i_view_the_contents_of_my_cart() throws Throwable {
        new ProductDetails(driver).goToCart();
    }

    @Then("^I should see the contents of the cart include the item$")
    public void i_should_see_the_contents_of_the_cart_include_the_item() throws Throwable {
        Assert.assertThat("The cart counter didn't increase", cartCounter, is(homePage.getCartCounter() - 1));
        Assert.assertThat("The details of the item in the cart doesn't much the one selected"
                , new CartPage(driver).getLastItemAdded(), CoreMatchers.equalTo(productDetails));
    }
}
