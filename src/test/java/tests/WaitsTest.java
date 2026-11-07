package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

/**
 * TOPIC 4 - Waits (the cure for flaky tests)
 *
 * What you learn: implicit wait, explicit wait (WebDriverWait +
 * ExpectedConditions), fluent wait. Never use Thread.sleep().
 *
 * Practice site: the-internet.herokuapp.com/dynamic_loading
 */
public class WaitsTest extends BaseTest {

    @Test
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(HEROKU + "/dynamic_loading/2");
        driver.findElement(By.cssSelector("#start button")).click();

        String text = driver.findElement(By.id("finish")).getText();
        Assert.assertEquals(text, "Hello World!");

        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
    }

    @Test
    public void explicitWait() {
        driver.get(HEROKU + "/dynamic_loading/1");
        driver.findElement(By.cssSelector("#start button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement finish = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        Assert.assertEquals(finish.getText(), "Hello World!");
    }

    @Test
    public void fluentWait() {
        driver.get(HEROKU + "/dynamic_loading/2");
        driver.findElement(By.cssSelector("#start button")).click();

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        WebElement finish = wait.until(d -> {
            WebElement e = d.findElement(By.id("finish"));
            return e.isDisplayed() ? e : null;
        });
        Assert.assertEquals(finish.getText(), "Hello World!");
    }

    @Test
    public void otherExpectedConditions() {
        driver.get(SAUCE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))).click();

        wait.until(ExpectedConditions.urlContains("inventory"));
        Assert.assertTrue(wait.until(ExpectedConditions.titleIs("Swag Labs")));
    }
}