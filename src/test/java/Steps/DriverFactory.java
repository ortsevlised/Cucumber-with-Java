package Steps;

import Util.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;


/**
 * Created by Jorge on 17/12/2015.
 */
public class DriverFactory {

    protected static WebDriver driver;
    private static String CHROME_DRIVER = "src/chromedriver.exe";

    public DriverFactory() {
        initialize();
    }

    /**
     * Calls to the driver initialization
     */
    public void initialize() {
        if (driver == null)
            createNewDriverInstance();
    }

    /**
     * Chooses the proper driver and deletes all cookies at the start of each scenario to avoid
     * shared state between tests
     * //TODO add support for remote webdriver, more drivers,appium, maybe sauceslab, capabilities, etc...
     */
    public void createNewDriverInstance() {
        String browser = new PropertyReader().readProperty("browser");
        switch (browser) {
            case "chrome":
                setDriverProperty("webdriver.chrome.driver", CHROME_DRIVER);
                driver = new ChromeDriver();
                break;
            case "firefox":
                // setDriverProperty("webdriver.firefox.bin", FIREFOX_DRIVER);
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("That browser is not detected, defaulting to chrome");
                setDriverProperty("webdriver.chrome.driver", CHROME_DRIVER);
                driver = new ChromeDriver();
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void destroyDriver() {
        driver.quit();
        driver = null;
    }

    /**
     * Method to set the driver location property key for the desired browser
     *
     * @param propertyKey
     * @param driverLocation
     */
    private static void setDriverProperty(String propertyKey, String driverLocation) {
        if (!System.getProperties().containsKey(propertyKey)) {
            System.setProperty(propertyKey, driverLocation);
            File driverExe = new File(driverLocation);
            if (driverExe.exists()) {
                System.setProperty(propertyKey, driverLocation);
            } else {
                // expect an error on the follow through when we try to use the driver
            }
        }
    }

}