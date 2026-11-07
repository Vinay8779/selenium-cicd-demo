package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.DriverFactory;
import utils.ScreenshotUtil;

/**
 * BaseTest - parent class of all browser tests.
 *
 * What you learn: TestNG @BeforeMethod / @AfterMethod, inheritance to avoid
 * repeating setup code, automatic screenshot when a test fails.
 *
 * Every test class "extends BaseTest" and gets a ready "driver" variable.
 */
public class BaseTest {

    protected WebDriver driver;

    protected static final String SAUCE = "https://www.saucedemo.com";
    protected static final String HEROKU = "https://the-internet.herokuapp.com";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                ScreenshotUtil.capture(driver, result.getName());
            }
            driver.quit();
        }
    }
}