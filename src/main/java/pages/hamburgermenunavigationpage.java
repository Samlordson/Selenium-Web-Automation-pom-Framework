package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import base.waitutil;

public class hamburgermenunavigationpage extends Base{
	 public hamburgermenunavigationpage(RemoteWebDriver driver) {
	        this.driver = driver;
	    }

	    // 🔹 Locators
	    private By clickHamburgermenu = By.xpath("//button[@class='toggle-btn mt-3']");
	    private By clickchangementmanagement = By.xpath("//span[text()='Change Management']");
	    private By clickdashboard = By.xpath("//li[text()=' Dashboard ']");
	    private By clickchanges = By.xpath("//li[text()=' Changes ']");
	    private By clickconfig = By.xpath("//li[text()=' Configuration ']");

	    //private By signInButton = By.xpath("//button[text()='Sign In']");

	    // 🔹 Actions

	    public hamburgermenunavigationpage clickHamburgermenu () {
	        try {
	            waitutil.waitForVisibility(driver, clickHamburgermenu, 30).click();
	            reportStep("Clicked Hamburger Menu", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to click hamburger menu" + e.getMessage(), "FAIL");
	            throw e;
	        }
			return this;
	    }
	    
	    public hamburgermenunavigationpage clickchangemanagement () {
	        try {
	            waitutil.waitForVisibility(driver, clickchangementmanagement, 30).click();
	            reportStep("Clicked Changemanagement", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to click contact and business " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }
	    
	    public hamburgermenunavigationpage clickdashboard () {
	        try {
	            waitutil.waitForVisibility(driver, clickdashboard, 30).click();
	            reportStep("Clicked Dashboard", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to click dashboard " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }
	    
	    public hamburgermenunavigationpage clickchanges () {
	        try {
	            waitutil.waitForVisibility(driver, clickchanges, 30).click();
	            reportStep("Clicked Changes", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to click Changes " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }
	    
	    public hamburgermenunavigationpage clickconfig () {
	        try {
	            waitutil.waitForVisibility(driver, clickconfig, 30).click();
	            reportStep("Clicked Changes", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to click Changes " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }
	    
}
