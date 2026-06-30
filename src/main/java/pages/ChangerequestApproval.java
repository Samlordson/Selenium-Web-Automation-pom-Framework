package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import base.waitutil;

public class ChangerequestApproval extends Base {

    public ChangerequestApproval(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // ===== Locators =====
    // Client Approves Study for Project Implementation (ng-select / dropdown)
    private By clientApprovesDropdown = By.xpath("//label[normalize-space(text())='Client Approves Study for Project Implementation']/following::ng-select[1]");
    private By clientApprovesInput = By.xpath("//label[normalize-space(text())='Client Approves Study for Project Implementation']/following::ng-select[1]//input");

    // Client Implementation Notes (input)
    private By clientImplementationNotes = By.xpath("//label[normalize-space(text())='Client Implementation Notes']/following::input[1]");

    // Change Reviewer (input)
    private By changeReviewer = By.xpath("//label[normalize-space(text())='Change Reviewer']/following::input[1]");

    // Change Decision Date (date input)
    private By changeDecisionDate = By.xpath("//label[normalize-space(text())='Change Decision Date']/following::input[1]");

    // Change Release area - Country dropdown & Release Number select + Add button
    private By changeReleaseCountry = By.xpath("//label[normalize-space(text())='Country']/following::ng-select[1]");
    private By changeReleaseCountryInput = By.xpath("//label[normalize-space(text())='Country']/following::ng-select[1]//input");
    private By changeReleaseNumberSelect = By.xpath("//label[normalize-space(text())='Select Change Release Number']/following::ng-select[1]");
    private By changeReleaseNumberInput = By.xpath("//label[normalize-space(text())='Select Change Release Number']/following::ng-select[1]//input");
    private By addReleaseButton = By.xpath("//button[contains(normalize-space(.), 'Add') or contains(@class,'bn--primary') and contains(normalize-space(.),'Add')]");

    // Table and delete action in Release table
    private By releaseTableRows = By.xpath("//table//tbody//tr");
    private By releaseRowDeleteBtn = By.xpath("//table//tbody//tr[{index}]//button[contains(@class,'tb-bn') or contains(@class,'bn--tertiary') or contains(@aria-label,'delete')]");
    private By releaseTableCellByColumn = By.xpath("//table//tbody//tr[{row}]//td[{col}]");

    // Save/Next button if present on page (adjust if label differs)
    private By saveButton = By.xpath("//button[normalize-space(text())='Save' or normalize-space(text())='Submit' or normalize-space(text())='Next']");

    // ===== Actions =====

    public ChangerequestApproval waitForSection() {
        try {
            waitutil.waitForVisibility(driver, clientApprovesDropdown, 30);
            reportStep("Change Request Approval section is visible", "PASS");
        } catch (Exception e) {
            reportStep("Change Request Approval section not visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public ChangerequestApproval selectClientApproves(String optionText) {
        try {
            waitutil.waitForClick(driver, clientApprovesDropdown, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, clientApprovesInput, 20);
            input.clear();
            input.sendKeys(optionText);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected client approves option: " + optionText, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select client approves option: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public ChangerequestApproval enterClientImplementationNotes(String notes) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, clientImplementationNotes, 20);
            el.clear();
            el.sendKeys(notes);
            reportStep("Entered Client Implementation Notes: " + notes, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Client Implementation Notes: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public ChangerequestApproval enterChangeReviewer(String reviewer) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, changeReviewer, 20);
            el.clear();
            el.sendKeys(reviewer);
            reportStep("Entered Change Reviewer: " + reviewer, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Change Reviewer: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public ChangerequestApproval setChangeDecisionDate(String dateValue) {
        try {
            WebElement input = waitutil.waitForVisibility(driver, changeDecisionDate, 20);
            input.clear();
            input.sendKeys(dateValue); // format should match UI e.g. dd-MM-yyyy or MM/dd/yyyy
            input.sendKeys(Keys.TAB);
            reportStep("Set Change Decision Date: " + dateValue, "PASS");
        } catch (Exception e) {
            reportStep("Failed to set Change Decision Date: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public ChangerequestApproval selectChangeReleaseCountry(String country) {
        try {
            waitutil.waitForClick(driver, changeReleaseCountry, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, changeReleaseCountryInput, 20);
            input.clear();
            input.sendKeys(country);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected Change Release Country: " + country, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select Change Release Country: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public ChangerequestApproval selectChangeReleaseNumber(String releaseNumber) {
        try {
            waitutil.waitForClick(driver, changeReleaseNumberSelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, changeReleaseNumberInput, 20);
            input.clear();
            input.sendKeys(releaseNumber);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected Change Release Number: " + releaseNumber, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select Change Release Number: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public ChangerequestApproval clickAddRelease() {
        try {
            WebElement btn = waitutil.waitForClick(driver, addReleaseButton, 20);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            btn.click();
            reportStep("Clicked Add Release button", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Add Release button: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    /**
     * Get text of a given cell in release table (row index is 1-based)
     * @param row 1-based row number
     * @param col 1-based column number
     */
    public String getReleaseTableCell(int row, int col) {
        try {
            By locator = By.xpath(releaseTableCellByColumn.toString()
                    .replace("{row}", String.valueOf(row))
                    .replace("{col}", String.valueOf(col))
            );
            // The above replacement is only to show how the template could be used.
            // Simpler: build the xpath string directly:
            String xpath = "//table//tbody//tr[" + row + "]//td[" + col + "]";
            String text = waitutil.waitForVisibility(driver, By.xpath(xpath), 20).getText();
            reportStep("Read table cell [" + row + "," + col + "] : " + text, "PASS");
            return text;
        } catch (Exception e) {
            reportStep("Failed to read release table cell: " + e.getMessage(), "FAIL");
            throw e;
        }
    }

    /**
     * Delete a release row at given 1-based index (clicks the delete icon for that row)
     */
    public ChangerequestApproval deleteReleaseRow(int rowIndex) {
        try {
            // build xpath for delete button in that row
            String xpath = "//table//tbody//tr[" + rowIndex + "]//button[contains(@class,'tb-bn') or contains(@class,'bn--tertiary') or contains(@aria-label,'delete')]";
            WebElement del = waitutil.waitForClick(driver, By.xpath(xpath), 20);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", del);
            del.click();
            reportStep("Clicked delete on release row: " + rowIndex, "PASS");
        } catch (Exception e) {
            reportStep("Failed to delete release row " + rowIndex + ": " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public ChangerequestApproval clickSave() {
        try {
            WebElement btn = waitutil.waitForClick(driver, saveButton, 20);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            btn.click();
            reportStep("Clicked Save/Next button", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Save/Next button: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }


    // ===== Convenience combined flows =====

    public ChangerequestApproval completeApprovalAndAddRelease(String clientApproveOption,
                                                               String implementationNotes,
                                                               String reviewer,
                                                               String decisionDate,
                                                               String releaseCountry,
                                                               String releaseNumber) {
        return this.waitForSection()
                   .selectClientApproves(clientApproveOption)
                   .enterClientImplementationNotes(implementationNotes)
                   .enterChangeReviewer(reviewer)
                   .setChangeDecisionDate(decisionDate)
                   .selectChangeReleaseCountry(releaseCountry)
                   .selectChangeReleaseNumber(releaseNumber)
                   .clickAddRelease();
    }
}
