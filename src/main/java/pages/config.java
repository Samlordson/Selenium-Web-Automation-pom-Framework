package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import base.waitutil;

public class config extends Base{
	public config(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // 🔹 Locators
    private By clickconfig = By.xpath("//li[text()=' Config ']");
    private By clicknewchangerelease = By.xpath("//button[text()='New Change Release']");
    private By clickperiodrelease = By.xpath("//button[text()=\"New Request\"]");
    private By clickFilter = By.xpath("//span[text()=' filter_alt ']");
    private By clicknewperiodamount = By.xpath("//button[text()='New Period Amount']");
    

    //private By signInButton = By.xpath("//button[text()='Sign In']");

    // 🔹 Actions

    public config clickconfig () {
        try {
            waitutil.waitForVisibility(driver, clickconfig, 30).click();
            reportStep("Clicked Config", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Config" + e.getMessage(), "FAIL");
            throw e;
        }
		return this;
    }
    
    public config clicknewchangerelease () {
        try {
            waitutil.waitForVisibility(driver, clicknewchangerelease, 30).click();
            reportStep("Clicked new change release", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click new change release" + e.getMessage(), "FAIL");
            throw e;
        }
		return this;
    }
    
    public config clickperiodrelease () {
        try {
            waitutil.waitForVisibility(driver, clickperiodrelease, 30).click();
            reportStep("Clicked period release", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click period release" + e.getMessage(), "FAIL");
            throw e;
        }
		return this;
    }
    
    public config clickfilter () {
        try {
            waitutil.waitForVisibility(driver, clickFilter, 30).click();
            reportStep("Clicked filter", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click filter" + e.getMessage(), "FAIL");
            throw e;
        }
		return this;
    }
    
    public config clicknewperiod () {
        try {
            waitutil.waitForVisibility(driver, clicknewperiodamount, 30).click();
            reportStep("Clicked new period amount", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click new period amount" + e.getMessage(), "FAIL");
            throw e;
        }
		return this;
    }

}
