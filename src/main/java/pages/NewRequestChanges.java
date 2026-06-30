package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import base.waitutil;

public class NewRequestChanges extends Base{
	 public NewRequestChanges(RemoteWebDriver driver) {
	        this.driver = driver;
	    }

	    // ===== Locators =====
	    // root drawer / popup
	    private By drawerContainer = By.cssSelector("div.drawer-content");

	    // Date picker area: label "Date" then input (robust relative xpath)
	    private By dateInput = By.xpath("//label[normalize-space(text())='Date']/following::input[1]");
	    private By datePickerDay = By.xpath("//div[contains(@class,'cdk-overlay-pane')]//button[not(contains(@class,'disabled')) and normalize-space(text())='14']"); // example to click day 14
	    private By datePickerMonthDropdown = By.xpath("//div[contains(@class,'cdk-overlay-pane')]//select"); // generic

	    // Business and Originating Party selects (ng-select style on UI)
	    private By businessSelectContainer = By.xpath("//label[normalize-space(text())='Business']/following::ng-select[1]");
	    private By businessSearchInput = By.xpath("//label[normalize-space(text())='Business']/following::ng-select[1]//input");
	    private By originatingPartySelectContainer = By.xpath("//label[normalize-space(text())='Originating Party']/following::ng-select[1]");
	    private By originatingPartySearchInput = By.xpath("//label[normalize-space(text())='Originating Party']/following::ng-select[1]//input");

	    // Requested By input
	    private By requestedByInput = By.xpath("//label[normalize-space(text())='Requested By']/following::input[1]");

	    // Project Area select
	    private By projectAreaSelect = By.xpath("//label[normalize-space(text())='Project Area']/following::ng-select[1]");
	    private By projectAreaSearchInput = By.xpath("//label[normalize-space(text())='Project Area']/following::ng-select[1]//input");

	    // Other Project Area input
	    private By otherProjectAreaInput = By.xpath("//label[normalize-space(text())='Other Project Area']/following::input[1]");

	    // Change Description & Change Reason textareas
	    private By changeDescription = By.xpath("//label[normalize-space(text())='Change Description']/following::textarea[1]");
	    private By changeReason = By.xpath("//label[normalize-space(text())='Change Reason']/following::textarea[1]");

	    // Save / Cancel buttons (in drawer footer)
	    private By cancelBtn = By.xpath("//div[contains(@class,'confirm-btn')]//button[normalize-space(text())='Cancel']");
	    private By saveBtn = By.xpath("//div[contains(@class,'confirm-btn')]//button[normalize-space(text())='Save' or @type='submit']");

	    // Validation messages (example)
	    private By businessRequiredError = By.xpath("//label[normalize-space(text())='Business']/following::div[contains(@class,'ng-invalid') or contains(text(),'Business is required')][1]");
	    private By originatingPartyRequiredError = By.xpath("//label[normalize-space(text())='Originating Party']/following::div[contains(text(),'Originating Party is required')][1]");

	    // ===== Actions =====

	    // Wait for popup to be visible
	    public NewRequestChanges waitForPopup() {
	        try {
	            waitutil.waitForVisibility(driver, drawerContainer, 30);
	            reportStep("Create New Request drawer is visible", "PASS");
	        } catch (Exception e) {
	            reportStep("Create New Request drawer did not appear: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Open date picker (click input) and optionally select a date by sending keys
	    public NewRequestChanges openDatePicker() {
	        try {
	            waitutil.waitForClick(driver, dateInput, 20).click();
	            reportStep("Opened date picker", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to open date picker: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Set date by direct input (format as UI expects, e.g., MM/dd/yyyy)
	    public NewRequestChanges setDate(String dateValue) {
	        try {
	            WebElement input = waitutil.waitForVisibility(driver, dateInput, 20);
	            input.clear();
	            input.sendKeys(dateValue);
	            input.sendKeys(Keys.TAB); // close picker
	            reportStep("Set date to: " + dateValue, "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to set date: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Select date from picker overlay by day number (safer to use setDate if available)
	    public NewRequestChanges pickDayFromCalendar(String day) {
	        try {
	            openDatePicker();
	            By dayLocator = By.xpath("//div[contains(@class,'cdk-overlay-pane')]//button[normalize-space(text())='" + day + "']");
	            waitutil.waitForClick(driver, dayLocator, 20).click();
	            reportStep("Picked day " + day + " from calendar", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to pick day from calendar: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Select Business by typing into the ng-select search input and pressing Enter
	    public NewRequestChanges selectBusiness(String businessName) {
	        try {
	            waitutil.waitForClick(driver, businessSelectContainer, 20).click();
	            WebElement input = waitutil.waitForVisibility(driver, businessSearchInput, 20);
	            input.clear();
	            input.sendKeys(businessName);
	            input.sendKeys(Keys.ENTER);
	            reportStep("Selected business: " + businessName, "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to select business: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Select Originating Party similarly
	    public NewRequestChanges selectOriginatingParty(String partyName) {
	        try {
	            waitutil.waitForClick(driver, originatingPartySelectContainer, 20).click();
	            WebElement input = waitutil.waitForVisibility(driver, originatingPartySearchInput, 20);
	            input.clear();
	            input.sendKeys(partyName);
	            input.sendKeys(Keys.ENTER);
	            reportStep("Selected originating party: " + partyName, "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to select originating party: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Enter Requested By text
	    public NewRequestChanges enterRequestedBy(String requestedBy) {
	        try {
	            WebElement el = waitutil.waitForVisibility(driver, requestedByInput, 20);
	            el.clear();
	            el.sendKeys(requestedBy);
	            reportStep("Entered Requested By: " + requestedBy, "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to enter Requested By: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Select Project Area
	    public NewRequestChanges selectProjectArea(String projectArea) {
	        try {
	            waitutil.waitForClick(driver, projectAreaSelect, 20).click();
	            WebElement input = waitutil.waitForVisibility(driver, projectAreaSearchInput, 20);
	            input.clear();
	            input.sendKeys(projectArea);
	            input.sendKeys(Keys.ENTER);
	            reportStep("Selected Project Area: " + projectArea, "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to select Project Area: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Enter other project area text
	    public NewRequestChanges enterOtherProjectArea(String other) {
	        try {
	            WebElement el = waitutil.waitForVisibility(driver, otherProjectAreaInput, 20);
	            el.clear();
	            el.sendKeys(other);
	            reportStep("Entered Other Project Area: " + other, "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to enter Other Project Area: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Enter change description
	    public NewRequestChanges enterChangeDescription(String desc) {
	        try {
	            WebElement el = waitutil.waitForVisibility(driver, changeDescription, 20);
	            el.clear();
	            el.sendKeys(desc);
	            reportStep("Entered Change Description", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to enter Change Description: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Enter change reason
	    public NewRequestChanges enterChangeReason(String reason) {
	        try {
	            WebElement el = waitutil.waitForVisibility(driver, changeReason, 20);
	            el.clear();
	            el.sendKeys(reason);
	            reportStep("Entered Change Reason", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to enter Change Reason: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Scroll inside drawer to save button (if required)
	    public NewRequestChanges scrollToSaveInDrawer() {
	        try {
	            WebElement drawer = waitutil.waitForVisibility(driver, drawerContainer, 20);
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", drawer);
	            reportStep("Scrolled drawer to bottom", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to scroll drawer: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Click Save (scroll into view then click)
	    public NewRequestChanges clickSave() {
	        try {
	            WebElement save = waitutil.waitForClick(driver, saveBtn, 30);
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", save);
	            save.click();
	            reportStep("Clicked Save button", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to click Save: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // Click Cancel
	    public NewRequestChanges clickCancel() {
	        try {
	            waitutil.waitForClick(driver, cancelBtn, 20).click();
	            reportStep("Clicked Cancel", "PASS");
	        } catch (Exception e) {
	            reportStep("Failed to click Cancel: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    // ===== Validation checks =====
	    public NewRequestChanges verifyBusinessRequiredErrorVisible() {
	        try {
	            waitutil.waitForVisibility(driver, businessRequiredError, 10);
	            reportStep("Business required validation visible", "PASS");
	        } catch (Exception e) {
	            reportStep("Business required validation not visible: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }

	    public NewRequestChanges verifyOriginatingPartyRequiredErrorVisible() {
	        try {
	            waitutil.waitForVisibility(driver, originatingPartyRequiredError, 10);
	            reportStep("Originating Party required validation visible", "PASS");
	        } catch (Exception e) {
	            reportStep("Originating Party validation not visible: " + e.getMessage(), "FAIL");
	            throw e;
	        }
	        return this;
	    }
	}



