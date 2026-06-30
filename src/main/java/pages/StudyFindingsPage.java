package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import base.waitutil;

public class StudyFindingsPage extends Base {

    public StudyFindingsPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // ===== Locators (robust label-relative XPaths) =====
    private By rootDrawer = By.cssSelector("div.drawer-content");

    // MG2 Design & Arch Entry
    private By dateCompleteInput = By.xpath("//label[normalize-space(text())='Date Complete']/following::input[1]");
    private By sheetsAffectedInput = By.xpath("//label[normalize-space(text())='Sheets Affected']/following::input[1]");
    private By detailsAffectedInput = By.xpath("//label[normalize-space(text())='Details Affected']/following::input[1]");
    private By specSectionAffectedInput = By.xpath("//label[normalize-space(text())='Spec Section Affected']/following::input[1]");
    private By changeNarrativeTextarea = By.xpath("//label[normalize-space(text())='Change Narrative']/following::textarea[1]");

    // MG2 Specifications Entry
    private By specDateCompleteInput = By.xpath("//label[normalize-space(text())='Date Complete' and contains(.,'MG2 Specifications Entry')]/following::input[1]");
    private By newSpecRequiredSelect = By.xpath("//label[contains(normalize-space(.),'New Specifications Required')]/following::ng-select[1]");
    private By newSpecRequiredSearchInput = By.xpath("//label[contains(normalize-space(.),'New Specifications Required')]/following::ng-select[1]//input");
    private By newSpecificationInput = By.xpath("//label[normalize-space(text())='New Specification']/following::input[1]");

    // MG2 Cost Estimating Entry
    private By costDateCompleteInput = By.xpath("//label[normalize-space(text())='Date Complete' and contains(.,'MG2 Cost Estimating Entry')]/following::input[1]");
    private By estimatedCostInput = By.xpath("//label[normalize-space(text())='Estimated Cost']/following::input[1]");
    private By costAnalysisSummaryTextarea = By.xpath("//label[normalize-space(text())='Cost Analysis Summary']/following::textarea[1]");

    // QA
    private By qaDateCompleteInput = By.xpath("//label[normalize-space(text())='Date Complete' and contains(.,'QA')]/following::input[1]");
    private By qaDueDateInput = By.xpath("//label[normalize-space(text())='Due Date' and contains(.,'QA')]/following::input[1]");

    // Assign To (example select)
    private By assignToSelect = By.xpath("//label[normalize-space(text())='Assign To']/following::ng-select[1]");
    private By assignToSearchInput = By.xpath("//label[normalize-space(text())='Assign To']/following::ng-select[1]//input");

    // Navigation / Save
    private By nextButton = By.xpath("//button[normalize-space(text())='Next' or normalize-space(text())='Save' or normalize-space(text())='Submit']");
    private By cancelButton = By.xpath("//button[normalize-space(text())='Cancel']");

    // ===== Actions =====

    public StudyFindingsPage waitForPage() {
        try {
            waitutil.waitForVisibility(driver, rootDrawer, 30);
            reportStep("Study Findings page visible", "PASS");
        } catch (Exception e) {
            reportStep("Study Findings page did not appear: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Generic helper to set date in an input (clear + send + tab)
    private void setDateInput(By locator, String dateValue, String fieldName) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, locator, 20);
            el.clear();
            el.sendKeys(dateValue);
            el.sendKeys(Keys.TAB);
            reportStep("Set " + fieldName + " to: " + dateValue, "PASS");
        } catch (Exception e) {
            reportStep("Failed to set " + fieldName + ": " + e.getMessage(), "FAIL");
            throw e;
        }
    }

    public StudyFindingsPage setDateComplete(String dateValue) {
        setDateInput(dateCompleteInput, dateValue, "MG2 Design Date Complete");
        return this;
    }

    public StudyFindingsPage enterSheetsAffected(String text) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, sheetsAffectedInput, 20);
            el.clear();
            el.sendKeys(text);
            reportStep("Entered Sheets Affected: " + text, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Sheets Affected: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public StudyFindingsPage enterDetailsAffected(String text) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, detailsAffectedInput, 20);
            el.clear();
            el.sendKeys(text);
            reportStep("Entered Details Affected: " + text, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Details Affected: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public StudyFindingsPage enterSpecSectionAffected(String text) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, specSectionAffectedInput, 20);
            el.clear();
            el.sendKeys(text);
            reportStep("Entered Spec Section Affected: " + text, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Spec Section Affected: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public StudyFindingsPage enterChangeNarrative(String narrative) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, changeNarrativeTextarea, 20);
            el.clear();
            el.sendKeys(narrative);
            reportStep("Entered Change Narrative", "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Change Narrative: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // MG2 Specifications Entry
    public StudyFindingsPage setSpecDateComplete(String dateValue) {
        setDateInput(specDateCompleteInput, dateValue, "MG2 Specifications Date Complete");
        return this;
    }

    public StudyFindingsPage selectNewSpecRequired(String option) {
        try {
            waitutil.waitForClick(driver, newSpecRequiredSelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, newSpecRequiredSearchInput, 20);
            input.clear();
            input.sendKeys(option);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected New Specifications Required: " + option, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select New Specifications Required: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public StudyFindingsPage enterNewSpecification(String text) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, newSpecificationInput, 20);
            el.clear();
            el.sendKeys(text);
            reportStep("Entered New Specification: " + text, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter New Specification: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // MG2 Cost Estimating Entry
    public StudyFindingsPage setCostDateComplete(String dateValue) {
        setDateInput(costDateCompleteInput, dateValue, "Cost Date Complete");
        return this;
    }

    public StudyFindingsPage enterEstimatedCost(String cost) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, estimatedCostInput, 20);
            el.clear();
            el.sendKeys(cost);
            reportStep("Entered Estimated Cost: " + cost, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Estimated Cost: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public StudyFindingsPage enterCostAnalysisSummary(String summary) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, costAnalysisSummaryTextarea, 20);
            el.clear();
            el.sendKeys(summary);
            reportStep("Entered Cost Analysis Summary", "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Cost Analysis Summary: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // QA
    public StudyFindingsPage setQADateComplete(String dateValue) {
        setDateInput(qaDateCompleteInput, dateValue, "QA Date Complete");
        return this;
    }

    public StudyFindingsPage setQADueDate(String dateValue) {
        setDateInput(qaDueDateInput, dateValue, "QA Due Date");
        return this;
    }

    // Assign To
    public StudyFindingsPage selectAssignTo(String name) {
        try {
            waitutil.waitForClick(driver, assignToSelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, assignToSearchInput, 20);
            input.clear();
            input.sendKeys(name);
            input.sendKeys(Keys.ENTER);
            reportStep("Assigned to: " + name, "PASS");
        } catch (Exception e) {
            reportStep("Failed to assign to: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Navigation
    public StudyFindingsPage clickNextOrSave() {
        try {
            waitutil.waitForClick(driver, nextButton, 20).click();
            reportStep("Clicked Next/Save", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Next/Save: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public StudyFindingsPage clickCancel() {
        try {
            waitutil.waitForClick(driver, cancelButton, 20).click();
            reportStep("Clicked Cancel", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Cancel: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
}
