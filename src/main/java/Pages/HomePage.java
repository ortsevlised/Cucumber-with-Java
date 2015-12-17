package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


/**
 * Created by Jorge on 17/12/2015.
 */
public class HomePage extends BasePage {

    public static final String PAGE_TITLE = "X-Doria | iPhone 6 Cases | iPhone 6s Plus, Apple Watch, iPad, Samsung – X-Doria";

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "flexslider")
    public static WebElement divCarousel;

    @FindBy(css = ".fadeInUp img")
    public static List<WebElement> imgBanners;

    @FindBy(id = "cartCount")
    public static WebElement labelCartCount;

    /**
     * Returns the element for the product passed in as parameter
     *
     * @param product
     * @return
     */
    public WebElement linkToProduct(String product) {
        return driver.findElement(By.cssSelector("[title='" + product + "']"));
    }

    /**
     * Goes to the E-commerce Home Page
     */
    public HomePage navigateTo() {
        driver.navigate().to("http://xdorialife.com/");
    //    Assert.assertThat("The site didn't load properly", isLoaded(), is(true));
        return this;
    }

    /**
     * Selects a collection according to the product name passed in
     *
     * @param product
     * @return ProductsPage
     */
    public ProductsPage selectACollection(String product) {
        waitUntilElementIsClickable(linkToProduct(product)).click();
        return new ProductsPage(driver);
    }

    /**
     * Gets the value displayed in the cart counter
     *
     * @return
     */
    public int getCartCounter() {
        return Integer.parseInt(labelCartCount.getText());
    }

    /**
     * Checks the page is loaded
     * @return
     * @throws Error
     */
    protected boolean isLoaded() throws Error {
       // waitUntilElementIsVisible(divCarousel);
        return (driver.getTitle().equals(PAGE_TITLE));
    }

}
