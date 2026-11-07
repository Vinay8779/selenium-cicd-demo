package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * ScreenshotUtil - saves screenshots into the "screenshots" folder.
 *
 * What you learn: TakesScreenshot, OutputType.FILE, saving a full-page or a
 * single-element screenshot. BaseTest calls this automatically when a test fails.
 */
public class ScreenshotUtil {

    public static String capture(WebDriver driver, String name) {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return save(source, name);
    }

    public static String captureElement(WebElement element, String name) {
        File source = element.getScreenshotAs(OutputType.FILE);
        return save(source, name);
    }

    private static String save(File source, String name) {
        try {
            Path folder = Paths.get("screenshots");
            Files.createDirectories(folder);
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Path target = folder.resolve(name + "_" + time + ".png");
            Files.copy(source.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            return target.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not save screenshot", e);
        }
    }
}