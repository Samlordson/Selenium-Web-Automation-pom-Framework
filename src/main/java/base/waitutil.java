package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class waitutil {
	

	    public static WebElement waitForVisibility(WebDriver driver, By locator, int timeOutInSeconds) {
	        return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))
	                .until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    public static WebElement waitForClick(WebDriver driver, By locator, int timeOutInSeconds) {
	        return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))
	                .until(ExpectedConditions.elementToBeClickable(locator));
	    }

	    public static boolean waitForInvisibility(WebDriver driver, By locator, int timeOutInSeconds) {
	        return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))
	                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
	    }
	}
