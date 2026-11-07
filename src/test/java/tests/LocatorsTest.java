package tests;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

/**
 * TOPIC 2 - Locators (how Selenium finds elements)
 *
 * What you learn: id, name, className, tagName, linkText, partialLinkText,
 * cssSelector, xpath, and Selenium 4 relative locators (below, above, toLeftOf...).
 *
 * Practice sites: saucedemo.com (login page) and the-internet.herokuapp.com
 */
public class LocatorsTest extends BaseTest {

    @Test
    public void idNameClassTag() {
        driver.get(SAUCE);
        Assert.assertTrue(driver.findElement(By.id("user-name")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.name("password")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.className("login_logo")).getText(), "Swag Labs");

        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        Assert.assertTrue(inputs.size() >= 3);
    }

    @Test
    public void cssSelectors() {
        driver.get(SAUCE);
        Assert.assertTrue(driver.findElement(By.cssSelector("#login-button")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[data-test='username']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input.submit-button")).isDisplayed());
    }

    @Test
    public void xpathLocators() {
        driver.get(SAUCE);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Username']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@type='password']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login_logo']")).isDisplayed());
    }

    @Test
    public void linkTextAndPartialLinkText() {
        driver.get(HEROKU);
        driver.findElement(By.linkText("Checkboxes")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkboxes"));

        driver.navigate().back();
        driver.findElement(By.partialLinkText("Dropd")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("dropdown"));
    }

    @Test
    public void relativeLocatorsSelenium4() {
        driver.get(SAUCE);
        WebElement passwordField = driver.findElement(with(By.tagName("input")).below(By.id("user-name")));
        Assert.assertEquals(passwordField.getAttribute("id"), "password");
    }
}