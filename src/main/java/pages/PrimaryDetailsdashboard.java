package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import base.waitutil;

public class PrimaryDetailsdashboard extends Base {

    public PrimaryDetailsdashboard(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // ===== Locators (Primary Details area) =====
    private By drawerContainer = By.cssSelector("div.drawer-content");

    private By responsibilitySelect = By.xpath("//label[normalize-space(text())='Responsibility']/following::ng-select[1]");
    private By responsibilityInput = By.xpath("//label[normalize-space(text())='Responsibility']/following::ng-select[1]//input");

    private By pmSelect = By.xpath("//label[normalize-space(text())='PM']/following::ng-select[1]");
    private By pmInput = By.xpath("//label[normalize-space(text())='PM']/following::ng-select[1]//input");

    private By mg2YesRadio = By.xpath("//label[normalize-space(text())='MG2 Cost Estimating Team']/following::label[normalize-space(text())='Yes'][1]");
    private By mg2NoRadio = By.xpath("//label[normalize-space(text())='MG2 Cost Estimating Team']/following::label[normalize-space(text())='No'][1]");

    private By prioritizationHigh = By.xpath("//label[normalize-space(text())='Prioritization']/following::label[normalize-space(text())='High'][1]");
    private By prioritizationLow = By.xpath("//label[normalize-space(text())='Prioritization']/following::label[normalize-space(text())='Low'][1]");

    private By countrySelect = By.xpath("//label[normalize-space(text())='Country']/following::ng-select[1]");
    private By countryInput = By.xpath("//label[normalize-space(text())='Country']/following::ng-select[1]//input");

    private By planIssuanceDateInput = By.xpath("//label[normalize-space(text())='Plan Issuance Date']/following::input[1]");

    private By tierTypeSelect = By.xpath("//label[normalize-space(text())='Tier & Type']/following::ng-select[1]");
    private By tierTypeInput = By.xpath("//label[normalize-space(text())='Tier & Type']/following::ng-select[1]//input");

    private By disciplinesSelect = By.xpath("//label[normalize-space(text())='Disciplines']/following::ng-select[1]");
    private By disciplinesInput = By.xpath("//label[normalize-space(text())='Disciplines']/following::ng-select[1]//input");

    // Validation messages
    private By responsibilityError = By.xpath("//label[normalize-space(text())='Responsibility']/following::div[contains(text(),'Responsibility is required')]");
    private By pmError = By.xpath("//label[normalize-space(text())='PM']/following::div[contains(text(),'PM is required')]");
    private By countryError = By.xpath("//label[normalize-space(text())='Country']/following::div[contains(text(),'Country is required')]");
    private By tierTypeError = By.xpath("//label[normalize-space(text())='Tier & Type']/following::div[contains(text(),'Tier & Type is required')]");
    private By disciplinesError = By.xpath("//label[normalize-space(text())='Disciplines']/following::div[contains(text(),'Disciplines is required')]");

    // Consultant area
    private By addConsultantBtn = By.xpath("//button[normalize-space(text())='Add Consultant']");
    private By consultantSearchInput = By.xpath("//input[@placeholder='Search']"); // top search
    private By consultantBusinessDropdownInRow = By.xpath("//table//select[contains(@class,'business') or contains(@aria-label,'Select Business')]");
    private By consultantContactDropdownInRow = By.xpath("//table//select[contains(@class,'contact') or contains(@aria-label,'Select Contact')]");

    // Footer buttons
    private By cancelBtn = By.xpath("//div[contains(@class,'confirm-btn')]//button[normalize-space(text())='Cancel']");
    private By saveBtn = By.xpath("//div[contains(@class,'confirm-btn')]//button[normalize-space(text())='Save' or @type='submit']");

    // ===== Actions =====

    // Wait for drawer to be visible
    public PrimaryDetailsdashboard waitForDrawer() {
        try {
            waitutil.waitForVisibility(driver, drawerContainer, 30);
            reportStep("Drawer visible", "PASS");
        } catch (Exception e) {
            reportStep("Drawer not visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Select Responsibility (type text then Enter)
    public PrimaryDetailsdashboard selectResponsibility(String responsibility) {
        try {
            selectFromNgSelect(responsibilitySelect, responsibilityInput, responsibility);
            reportStep("Selected Responsibility: " + responsibility, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select Responsibility: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }


    // Select PM
    public PrimaryDetailsdashboard selectPM(String pmName) {
        try {
            waitutil.waitForClick(driver, pmSelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, pmInput, 20);
            input.clear();
            input.sendKeys(pmName);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected PM: " + pmName, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select PM: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Set MG2 Cost Estimating Team radio
    public PrimaryDetailsdashboard setMG2CostEstimating(boolean yes) {
        try {
            if (yes) {
                waitutil.waitForClick(driver, mg2YesRadio, 20).click();
                reportStep("Set MG2 Cost Estimating = Yes", "PASS");
            } else {
                waitutil.waitForClick(driver, mg2NoRadio, 20).click();
                reportStep("Set MG2 Cost Estimating = No", "PASS");
            }
        } catch (Exception e) {
            reportStep("Failed to set MG2 Cost Estimating: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Set Prioritization
    public PrimaryDetailsdashboard setPrioritization(String level) {
        try {
            if ("High".equalsIgnoreCase(level)) {
                waitutil.waitForClick(driver, prioritizationHigh, 20).click();
            } else {
                waitutil.waitForClick(driver, prioritizationLow, 20).click();
            }
            reportStep("Set Prioritization: " + level, "PASS");
        } catch (Exception e) {
            reportStep("Failed to set Prioritization: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Select Country
    public PrimaryDetailsdashboard selectCountry(String countryName) {
        try {
            waitutil.waitForClick(driver, countrySelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, countryInput, 20);
            input.clear();
            input.sendKeys(countryName);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected Country: " + countryName, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select Country: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Set Plan Issuance Date (enter as text or open calendar)
    public PrimaryDetailsdashboard setPlanIssuanceDate(String mmddyyyy) {
        try {
            WebElement input = waitutil.waitForVisibility(driver, planIssuanceDateInput, 20);
            input.clear();
            input.sendKeys(mmddyyyy);
            input.sendKeys(Keys.TAB);
            reportStep("Set Plan Issuance Date: " + mmddyyyy, "PASS");
        } catch (Exception e) {
            reportStep("Failed to set Plan Issuance Date: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Select Tier & Type
    public PrimaryDetailsdashboard selectTierAndType(String tierType) {
        try {
            waitutil.waitForClick(driver, tierTypeSelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, tierTypeInput, 20);
            input.clear();
            input.sendKeys(tierType);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected Tier & Type: " + tierType, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select Tier & Type: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Select Disciplines
    public PrimaryDetailsdashboard selectDisciplines(String discipline) {
        try {
            waitutil.waitForClick(driver, disciplinesSelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, disciplinesInput, 20);
            input.clear();
            input.sendKeys(discipline);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected Discipline: " + discipline, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select Discipline: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // ===== Consultant / table interactions =====

    // Click Add Consultant button
    public PrimaryDetailsdashboard clickAddConsultant() {
        try {
            waitutil.waitForClick(driver, addConsultantBtn, 20).click();
            reportStep("Clicked Add Consultant", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Add Consultant: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Search consultant in top search box
    public PrimaryDetailsdashboard searchConsultant(String text) {
        try {
            WebElement input = waitutil.waitForVisibility(driver, consultantSearchInput, 20);
            input.clear();
            input.sendKeys(text);
            input.sendKeys(Keys.ENTER);
            reportStep("Searched consultant: " + text, "PASS");
        } catch (Exception e) {
            reportStep("Failed to search consultant: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Select business in consultant table row (first row)
    public PrimaryDetailsdashboard selectBusinessInConsultantRow(String businessName) {
        try {
            // open the select then type and choose
            WebElement selectEl = waitutil.waitForVisibility(driver, consultantBusinessDropdownInRow, 20);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectEl);
            selectEl.click();
            selectEl.sendKeys(businessName);
            selectEl.sendKeys(Keys.ENTER);
            reportStep("Selected business in consultant row: " + businessName, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select business in consultant row: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Select contact in consultant table row (first row)
    public PrimaryDetailsdashboard selectContactInConsultantRow(String contactName) {
        try {
            WebElement selectEl = waitutil.waitForVisibility(driver, consultantContactDropdownInRow, 20);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectEl);
            selectEl.click();
            selectEl.sendKeys(contactName);
            selectEl.sendKeys(Keys.ENTER);
            reportStep("Selected contact in consultant row: " + contactName, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select contact in consultant row: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Scroll drawer to bottom (useful before clicking Save)
    public PrimaryDetailsdashboard scrollDrawerToBottom() {
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
    public PrimaryDetailsdashboard clickSave() {
        try {
            WebElement save = waitutil.waitForClick(driver, saveBtn, 30);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", save);
            save.click();
            reportStep("Clicked Save", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Save: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Click Cancel
    public PrimaryDetailsdashboard clickCancel() {
        try {
            waitutil.waitForClick(driver, cancelBtn, 20).click();
            reportStep("Clicked Cancel", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Cancel: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // ===== Validations =====

    public PrimaryDetailsdashboard verifyResponsibilityErrorVisible() {
        try {
            waitutil.waitForVisibility(driver, responsibilityError, 10);
            reportStep("Responsibility validation visible", "PASS");
        } catch (Exception e) {
            reportStep("Responsibility validation not visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public PrimaryDetailsdashboard verifyPMErrorVisible() {
        try {
            waitutil.waitForVisibility(driver, pmError, 10);
            reportStep("PM validation visible", "PASS");
        } catch (Exception e) {
            reportStep("PM validation not visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public PrimaryDetailsdashboard verifyCountryErrorVisible() {
        try {
            waitutil.waitForVisibility(driver, countryError, 10);
            reportStep("Country validation visible", "PASS");
        } catch (Exception e) {
            reportStep("Country validation not visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public PrimaryDetailsdashboard verifyTierTypeErrorVisible() {
        try {
            waitutil.waitForVisibility(driver, tierTypeError, 10);
            reportStep("Tier & Type validation visible", "PASS");
        } catch (Exception e) {
            reportStep("Tier & Type validation not visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public PrimaryDetailsdashboard verifyDisciplinesErrorVisible() {
        try {
            waitutil.waitForVisibility(driver, disciplinesError, 10);
            reportStep("Disciplines validation visible", "PASS");
        } catch (Exception e) {
            reportStep("Disciplines validation not visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
    
    private void selectFromNgSelect(By selectLocator, By inputLocator, String value) {

        // Click ng-select
        WebElement select = waitutil.waitForClick(driver, selectLocator, 20);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", select);

        // Type value
        WebElement input = waitutil.waitForVisibility(driver, inputLocator, 20);
        input.clear();
        input.sendKeys(value);

        // Wait for dropdown option and click it
        By option =
            By.xpath("//div[contains(@class,'ng-dropdown-panel')]//span[normalize-space()='" + value + "']");

        WebElement opt = waitutil.waitForClick(driver, option, 20);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt);
    }

}


