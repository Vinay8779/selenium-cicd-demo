package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

/**
 * TOPIC 3 - WebElement commands
 *
 * What you learn: sendKeys(), clear(), click(), getText(), getAttribute(),
 * isDisplayed(), isEnabled(), isSelected(), getCssValue().
 *
 * Practice sites: saucedemo.com and the-internet.herokuapp.com/checkboxes
 */
public class WebElementCommandsTest extends BaseTest {

    @Test
    public void typeAndClear() {
        driver.get(SAUCE);
        WebElement user = driver.findElement(By.id("user-name"));

        user.sendKeys("standard_user");
        Assert.assertEquals(user.getAttribute("value"), "standard_user");

        user.clear();
        Assert.assertEquals(user.getAttribute("value"), "");
    }

    @Test
    public void clickAndGetText() {
        driver.get(SAUCE);
        driver.findElement(By.id("login-button")).click();
        String message = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        Assert.assertTrue(message.contains("Username is required"));
    }

    @Test
    public void displayedEnabledAttributes() {
        driver.get(SAUCE);
        WebElement button = driver.findElement(By.id("login-button"));
        Assert.assertTrue(button.isDisplayed());
        Assert.assertTrue(button.isEnabled());
        Assert.assertEquals(button.getAttribute("value"), "Login");
        Assert.assertNotNull(button.getCssValue("background-color"));
    }

    @Test
    public void isSelectedOnCheckboxes() {
        driver.get(HEROKU + "/checkboxes");
        WebElement first = driver.findElements(By.cssSelector("input[type='checkbox']")).get(0);
        WebElement second = driver.findElements(By.cssSelector("input[type='checkbox']")).get(1);
        Assert.assertFalse(first.isSelected());
        Assert.assertTrue(second.isSelected());
    }
}