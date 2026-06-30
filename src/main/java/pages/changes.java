package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import base.waitutil;

public class changes extends Base  {
	public changes(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // 🔹 Locators
    private By clickChanges = By.xpath("//li[text()=' Changes ']");
    private By clickAllchanges = By.xpath("//a[text()=' All Changes ']");
    private By clickMasterchanges = By.xpath("//button[text()=\"New Request\"]");
    private By clickFilter = By.xpath("//a[text()=\" Active Request \"] ");
    private By clickdownloadexcel = By.xpath("//a[text()=\" Voided Request \"] ");
    private By clickNewrequest = By.xpath("//button[text()='New Request'] ");

    //private By signInButton = By.xpath("//button[text()='Sign In']");

    // 🔹 Actions

    public changes clickchanges () {
        try {
            waitutil.waitForVisibility(driver, clickChanges, 30).click();
            reportStep("Clicked Chnages", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Chnages" + e.getMessage(), "FAIL");
            throw e;
        }
		return this;
    }
    
    public changes clickAllchanges () {
        try {
            waitutil.waitForVisibility(driver, clickAllchanges, 30).click();
            reportStep("Clicked All Chnages", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click All changes " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
    
    public changes clickmasterchanges () {
        try {
            waitutil.waitForVisibility(driver, clickMasterchanges, 30).click();
            reportStep("Clicked Master Changes", "PASS");
        } catch (Exception e) {
            reportStep("Failed to Master changes" + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
    
    public changes clickFilter () {
        try {
            waitutil.waitForVisibility(driver, clickFilter, 30).click();
            reportStep("Clicked Filter", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click filter" + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
    
    public changes clickdownloadexcel () {
        try {
            waitutil.waitForVisibility(driver, clickdownloadexcel, 30).click();
            reportStep("Clicked Download excel", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click download excel " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
    
    public changes clicknewrequest () {
        try {
            waitutil.waitForVisibility(driver, clickNewrequest, 30).click();
            reportStep("Clicked New Request", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click New Request " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
    
}





