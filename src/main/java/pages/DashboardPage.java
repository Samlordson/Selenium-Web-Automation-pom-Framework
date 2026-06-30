package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import base.waitutil;

public class DashboardPage extends Base{

	public DashboardPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // 🔹 Locators
    private By clickMasterchangeDashboard = By.xpath("//a[text()=\" Master Change Dashboard \"]");
    private By clickChangeRequestDashboard = By.xpath("//a[text()=\" Change Request Dashboard \"]");
    private By clickNewRequest = By.xpath("//button[text()=\"New Request\"]");
    private By clickActiveRequest = By.xpath("//a[text()=\" Active Request \"] ");
    private By clickVoidedRequest = By.xpath("//a[text()=\" Voided Request \"] ");

    //private By signInButton = By.xpath("//button[text()='Sign In']");

    // 🔹 Actions

    public DashboardPage clickMasterchangeDashboard () {
        try {
            waitutil.waitForVisibility(driver, clickMasterchangeDashboard, 30).click();
            reportStep("Clicked Masterchange Dashboard", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Masterchange dashboard" + e.getMessage(), "FAIL");
            throw e;
        }
		return this;
    }
    
    public DashboardPage clickchangeRequestdashboard () {
        try {
            waitutil.waitForVisibility(driver, clickChangeRequestDashboard, 30).click();
            reportStep("Clicked Change", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click ChangeRequest " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
    
    public DashboardPage clickNewRequest () {
        try {
            waitutil.waitForVisibility(driver, clickNewRequest, 30).click();
            reportStep("Clicked New Request", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click New Request " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
    
    public DashboardPage clickActiveRequest () {
        try {
            waitutil.waitForVisibility(driver, clickActiveRequest, 30).click();
            reportStep("Clicked Active Request", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Active Request" + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
    
    public DashboardPage clickVoidedrequest () {
        try {
            waitutil.waitForVisibility(driver, clickVoidedRequest, 30).click();
            reportStep("Clicked Changes", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Changes " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
    
}


