package tests;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

/**
 * TOPIC 1 - Browser commands
 *
 * What you learn: get(), getTitle(), getCurrentUrl(), getPageSource(),
 * navigate().to/back/forward/refresh(), window size and maximize.
 *
 * Practice site: https://www.saucedemo.com
 */
public class BrowserCommandsTest extends BaseTest {

    @Test
    public void titleUrlAndSource() {
        driver.get(SAUCE);
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(driver.getPageSource().contains("Swag Labs"));
    }

    @Test
    public void navigationCommands() {
        driver.get(SAUCE);
        driver.navigate().to(HEROKU);
        Assert.assertTrue(driver.getCurrentUrl().contains("herokuapp"));

        driver.navigate().back();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo"));

        driver.navigate().forward();
        Assert.assertTrue(driver.getCurrentUrl().contains("herokuapp"));

        driver.navigate().refresh();
        Assert.assertTrue(driver.getCurrentUrl().contains("herokuapp"));
    }

    @Test
    public void windowCommands() {
        driver.get(SAUCE);
        driver.manage().window().setSize(new Dimension(1000, 700));
        Assert.assertTrue(driver.manage().window().getSize().getWidth() > 0);
        driver.manage().window().maximize();
    }
}