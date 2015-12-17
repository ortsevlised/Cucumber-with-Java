package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by Jorge on 17/12/2015.
 */

public class ProductDetails extends BasePage {

    public ProductDetails(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "product-add-to-cart")
    public static WebElement btnAddToCart;

    @FindBy(css = ".btn-secondary.btn-go-to-cart")
    public static WebElement btnGoToCart;

    @FindBy(className = "content")
    public static WebElement popUpcontent;

    @FindBy(css = ".product-title>h2")
    public static WebElement txtItemTitle;

    @FindBy(className = "prices")
    public static WebElement txtPrice;

    @FindBy(css = ".swatch-element [checked='']")
    public static WebElement txtColor;


    /**
     * Gets the product details, title, price colour.
     * @return
     */
    public String[] getProductDetails() {
        return  new String[]{txtItemTitle.getText().trim().replaceAll("\\W", "").toLowerCase(), txtPrice.getText(), txtColor.getAttribute("value")};
    }

    /**
     * Adds the product to the cart
     *
     * @return
     */
    public ProductDetails addToCart() {
        waitUntilElementIsClickable(btnAddToCart).click();
        waitUntilElementIsVisible(popUpcontent);
        return this;
    }

    /**
     * Goes to the cart page
     *
     * @return
     */
    public CartPage goToCart() {
        btnGoToCart.click();
        return new CartPage(driver);
    }



}


