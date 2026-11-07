package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * DriverFactory - creates the browser used by every test.
 *
 * What you learn: ChromeOptions, headless mode, choosing a browser from a
 * command-line value.
 *
 * How to use:
 *   mvn test                                   -> Chrome with a window
 *   mvn test -Dheadless=true                   -> Chrome without a window (CI)
 *   mvn test -Dbrowser=firefox -Dheadless=true -> Firefox
 */
public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");
        boolean headless = "true".equals(System.getProperty("headless", "false"));

        if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless) {
                options.addArguments("-headless");
            }
            options.addArguments("--width=1920", "--height=1080");
            return new FirefoxDriver(options);
        }

        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }
}