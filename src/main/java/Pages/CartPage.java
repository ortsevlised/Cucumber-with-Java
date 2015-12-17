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

public class CartPage extends BasePage {

    public static final String URL = "cart";

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "remove")
    public static List<WebElement> btnRemove;

    @FindBy(className = "update")
    public static List<WebElement> btnUpdate;

    @FindBy(css = ".cart-row .btn")
    public static WebElement btnCheckOut;

    @FindBy(css = ".cart-list .product-details")
    public static List<WebElement> divCartItem;

    @FindBy(className = "wrapper-cart")
    public static WebElement divProducts;

    /**
     * Gets the last item added to the cart
     */
    public String[] getLastItemAdded() {
        waitUntilElementIsVisible(divProducts);
        String title = divCartItem.get(0).findElement(By.className("product-name")).getText().trim().replaceAll("\\W", "").toLowerCase();
        String price = divCartItem.get(0).findElement(By.className("price")).getText();
        String color = divCartItem.get(0).findElement(By.className("size")).getText();
        return new String[]{title, price, color};
    }

    /**
     * Clicks on the check out button.
     */
    public void proceedToCheckOut() {
        waitUntilElementIsClickable(btnCheckOut).click();
    }


}
