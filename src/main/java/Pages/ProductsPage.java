package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Jorge on 17/12/2015.
 */

public class ProductsPage extends BasePage {

    //I'm using only the iPhone collection, it'd might be better to create a new page object for each type of product
    public static final String PRODUCT_URL = "iphone-6s-cases";

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".products-grid .product-image")
    public static List<WebElement> imgProduct;

    /**
     * Selects the first product from the collection
     *
     * @return ProductDetails page
     */
    public ProductDetails selectGenericItem() {
        scrollToElement(imgProduct.get(0)).click();
        return new ProductDetails(driver);
    }

    /**
     * Checks the page is loaded
     * @return
     * @throws Error
     */
    protected boolean isLoaded() throws Error {
        return (driver.getCurrentUrl().endsWith(PRODUCT_URL));
    }

}
