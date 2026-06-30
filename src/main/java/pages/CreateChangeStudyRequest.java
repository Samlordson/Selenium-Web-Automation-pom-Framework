package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import base.waitutil;

public class CreateChangeStudyRequest extends Base {

    public CreateChangeStudyRequest(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // 🔹 Locators
    private By newrequest = By.xpath("//button[normalize-space(text())='New Request']");
    private By dateReceivedInput = By.xpath("//label[normalize-space(text())='Date Received']/following::input[1]");
    private By businessDropdown = By.xpath("//label[normalize-space(text())='Business']/following::ng-select[1]");
    private By businessSearchInput = By.xpath("//label[normalize-space(text())='Business']/following::ng-select[1]//input");
    private By originatingPartyDropdown = By.xpath("//label[normalize-space(text())='Originating Party']/following::ng-select[1]");
    private By originatingPartySearchInput = By.xpath("//label[normalize-space(text())='Originating Party']/following::ng-select[1]//input");
    private By requestedByInput = By.xpath("//label[normalize-space(text())='Requested By']/following::input[1]");
    private By projectAreaDropdown = By.xpath("//label[normalize-space(text())='Project Area']/following::ng-select[1]");
    private By projectAreaSearchInput = By.xpath("//label[normalize-space(text())='Project Area']/following::ng-select[1]//input");
    private By otherProjectAreaInput = By.xpath("//label[normalize-space(text())='Other Project Area']/following::input[1]");
    private By changeDescriptionInput = By.xpath("//label[normalize-space(text())='Change Description']/following::textarea[1]");
    private By changeReasonInput = By.xpath("//label[normalize-space(text())='Change Reason']/following::textarea[1]");
    private By additionalInfoInput = By.xpath("//label[normalize-space(text())='Additional Info']/following::textarea[1]");
    private By submitButton = By.xpath("//button[normalize-space(text())='Save']");

    // 🔹 Actions
    
    public CreateChangeStudyRequest clickcreatenewrequest() {
        try {
             waitutil.waitForClick(driver,newrequest , 20).click();
            reportStep("Clicked New Request successfully", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click New Request button: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public CreateChangeStudyRequest setDateReceived(String date) {
        try {
            WebElement input = waitutil.waitForVisibility(driver, dateReceivedInput, 20);
            input.clear();
            input.sendKeys(date);
            input.sendKeys(Keys.TAB);
            reportStep("Entered Date Received: " + date, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Date Received: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public CreateChangeStudyRequest selectBusiness(String businessName) {
        try {
            waitutil.waitForClick(driver, businessDropdown, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, businessSearchInput, 20);
            input.sendKeys(businessName);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected Business: " + businessName, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select Business: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public CreateChangeStudyRequest selectOriginatingParty(String partyName) {
        try {
            waitutil.waitForClick(driver, originatingPartyDropdown, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, originatingPartySearchInput, 20);
            input.sendKeys(partyName);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected Originating Party: " + partyName, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select Originating Party: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public CreateChangeStudyRequest enterRequestedBy(String requester) {
        try {
            WebElement input = waitutil.waitForVisibility(driver, requestedByInput, 20);
            input.clear();
            input.sendKeys(requester);
            reportStep("Entered Requested By: " + requester, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Requested By: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public CreateChangeStudyRequest selectProjectArea(String area) {
        try {
            waitutil.waitForClick(driver, projectAreaDropdown, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, projectAreaSearchInput, 20);
            input.sendKeys(area);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected Project Area: " + area, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select Project Area: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public CreateChangeStudyRequest enterOtherProjectArea(String text) {
        try {
            WebElement input = waitutil.waitForVisibility(driver, otherProjectAreaInput, 20);
            input.clear();
            input.sendKeys(text);
            reportStep("Entered Other Project Area: " + text, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Other Project Area: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public CreateChangeStudyRequest enterChangeDescription(String desc) {
        try {
            WebElement input = waitutil.waitForVisibility(driver, changeDescriptionInput, 20);
            input.clear();
            input.sendKeys(desc);
            reportStep("Entered Change Description", "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Change Description: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public CreateChangeStudyRequest enterChangeReason(String reason) {
        try {
            WebElement input = waitutil.waitForVisibility(driver, changeReasonInput, 20);
            input.clear();
            input.sendKeys(reason);
            reportStep("Entered Change Reason", "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Change Reason: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public CreateChangeStudyRequest enterAdditionalInfo(String info) {
        try {
            WebElement input = waitutil.waitForVisibility(driver, additionalInfoInput, 20);
            input.clear();
            input.sendKeys(info);
            reportStep("Entered Additional Info", "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Additional Info: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public CreateChangeStudyRequest scrollToSubmit() {
        try {
            WebElement submitBtn = waitutil.waitForVisibility(driver, submitButton, 20);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
            reportStep("Scrolled to Submit button", "PASS");
        } catch (Exception e) {
            reportStep("Failed to scroll to Submit button: " + e.getMessage(), "FAIL");
        }
        return this;
    }

    public CreateChangeStudyRequest clickSubmit() {
        try {
            WebElement submitBtn = waitutil.waitForClick(driver, submitButton, 20);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
            reportStep("Clicked Submit button successfully", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Submit button: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
}
