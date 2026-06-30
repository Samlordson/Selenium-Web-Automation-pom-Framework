package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;

import base.Base;
import base.waitutil;

public class ChangeReleasePage extends Base {

    public ChangeReleasePage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // ===== Locators =====
    private By changeReleaseHeader = By.xpath("//h4[contains(text(),'Change Release') or contains(text(),'Change Release')]");

    // Country dropdown
    private By countryDropdown = By.xpath("//label[normalize-space(text())='Country']/following::ng-select[1]");
    private By countryInput = By.xpath("//label[normalize-space(text())='Country']/following::ng-select[1]//input");

    // Change Release Number dropdown
    private By releaseNumberDropdown = By.xpath("//label[contains(text(),'Select Change Release Number')]/following::ng-select[1]");
    private By releaseNumberInput = By.xpath("//label[contains(text(),'Select Change Release Number')]/following::ng-select[1]//input");

    // Add button
    private By addButton = By.xpath("//button[contains(normalize-space(.),'Add') or contains(@class,'bn--primary')]");
    
    // Save button
    private By saveButton = By.xpath("//button[normalize-space(text())='Save']");

    // Table rows
    private By releaseTableRows = By.xpath("//table//tbody//tr");
    private String tableCellXPath = "//table//tbody//tr[%s]//td[%s]";
    private String deleteIconXPath = "//table//tbody//tr[%s]//button[contains(@class,'bn') or contains(@aria-label,'delete')]";

    // ===== Actions =====

    /** Wait for Change Release section to load */
    public ChangeReleasePage waitForChangeReleasePage() {
        try {
            waitutil.waitForVisibility(driver, changeReleaseHeader, 30);
            reportStep("Change Release page loaded successfully", "PASS");
        } catch (Exception e) {
            reportStep("Change Release page not loaded: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    /** Select a country from dropdown */
    public ChangeReleasePage selectCountry(String country) {
        try {
            waitutil.waitForClick(driver, countryDropdown, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, countryInput, 20);
            input.clear();
            input.sendKeys(country);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected country: " + country, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select country: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    /** Select a Change Release Number */
    public ChangeReleasePage selectChangeReleaseNumber(String releaseNumber) {
        try {
            waitutil.waitForClick(driver, releaseNumberDropdown, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, releaseNumberInput, 20);
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

    /** Click Add button */
    public ChangeReleasePage clickAdd() {
        try {
            WebElement add = waitutil.waitForClick(driver, addButton, 20);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", add);
            add.click();
            reportStep("Clicked on Add button", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Add button: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    /** Click Save button */
    public ChangeReleasePage clickSave() {
        try {
            WebElement save = waitutil.waitForClick(driver, saveButton, 20);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", save);
            save.click();
            reportStep("Clicked on Save button", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Save button: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    /** Get total release records listed */
    public int getReleaseCount() {
        try {
            int count = driver.findElements(releaseTableRows).size();
            reportStep("Total Release rows found: " + count, "PASS");
            return count;
        } catch (Exception e) {
            reportStep("Failed to count Release rows: " + e.getMessage(), "FAIL");
            throw e;
        }
    }

    /** Read a cell value from table */
    public String getTableCellValue(int rowIndex, int columnIndex) {
        try {
            String xpath = String.format(tableCellXPath, rowIndex, columnIndex);
            String cellValue = waitutil.waitForVisibility(driver, By.xpath(xpath), 20).getText();
            reportStep("Cell[" + rowIndex + "," + columnIndex + "] value: " + cellValue, "PASS");
            return cellValue;
        } catch (Exception e) {
            reportStep("Failed to read table cell: " + e.getMessage(), "FAIL");
            throw e;
        }
    }

    /** Delete a release entry by row index */
    public ChangeReleasePage deleteReleaseByIndex(int rowIndex) {
        try {
            String xpath = String.format(deleteIconXPath, rowIndex);
            WebElement deleteBtn = waitutil.waitForClick(driver, By.xpath(xpath), 20);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteBtn);
            deleteBtn.click();
            reportStep("Deleted Release entry at row " + rowIndex, "PASS");
        } catch (Exception e) {
            reportStep("Failed to delete release at row " + rowIndex + ": " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Combined method to perform end-to-end Add + Save flow
    public ChangeReleasePage completeChangeReleaseFlow(String country, String releaseNumber) {
        return this.waitForChangeReleasePage()
                   .selectCountry(country)
                   .selectChangeReleaseNumber(releaseNumber)
                   .clickAdd()
                   .clickSave();
    }
}
