package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import base.waitutil;

public class Primarydetailspage extends Base {

    public Primarydetailspage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // ===== Locators (relative / robust) =====
    private By drawerContainer = By.cssSelector("div.drawer-content");

    private By responsibilitySelect = By.xpath("//label[normalize-space(text())='Responsibility']/following::ng-select[1]");
    private By responsibilityInput = By.xpath("//label[normalize-space(text())='Responsibility']/following::ng-select[1]//input");

    private By pmSelect = By.xpath("//label[normalize-space(text())='PM']/following::ng-select[1]");
    private By pmInput = By.xpath("//label[normalize-space(text())='PM']/following::ng-select[1]//input");

    private By mg2TeamYes = By.xpath("//label[normalize-space(text())='MG2 Cost Estimating Team']/following::label[contains(.,'Yes')][1]");

    private By prioritizationHigh = By.xpath("//label[normalize-space(text())='Prioritization']/following::label[contains(.,'High')][1]");

    private By countrySelect = By.xpath("//label[normalize-space(text())='Country']/following::ng-select[1]");
    private By countryInput = By.xpath("//label[normalize-space(text())='Country']/following::ng-select[1]//input");

    private By planIssuanceDateInput = By.xpath("//label[normalize-space(text())='Plan Issuance Date']/following::input[1]");

    private By tierTypeSelect = By.xpath("//label[normalize-space(text())='Tier & Type']/following::ng-select[1]");
    private By tierTypeInput = By.xpath("//label[normalize-space(text())='Tier & Type']/following::ng-select[1]//input");

    private By disciplinesSelect = By.xpath("//label[normalize-space(text())='Disciplines']/following::ng-select[1]");
    private By disciplinesInput = By.xpath("//label[normalize-space(text())='Disciplines']/following::ng-select[1]//input");

    private By addConsultantBtn = By.xpath("//button[normalize-space(text())='Add Consultant']");
    private By submitBtn = By.xpath("//button[normalize-space(text())='Submit' or @type='submit']");

    // ===== Actions =====

    public Primarydetailspage waitForDrawer() {
        try {
            waitutil.waitForVisibility(driver, drawerContainer, 20);
            reportStep("Create New Request drawer visible", "PASS");
        } catch (Exception e) {
            reportStep("Create New Request drawer NOT visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public Primarydetailspage selectResponsibility(String responsibility) {
        try {
            waitutil.waitForClick(driver, responsibilitySelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, responsibilityInput, 10);
            input.clear();
            input.sendKeys(responsibility);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected Responsibility: " + responsibility, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select Responsibility: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public Primarydetailspage selectPM(String pmName) {
        try {
            waitutil.waitForClick(driver, pmSelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, pmInput, 10);
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

    public Primarydetailspage selectMG2TeamYes() {
        try {
            waitutil.waitForClick(driver, mg2TeamYes, 10).click();
            reportStep("Selected MG2 Cost Estimating Team = Yes", "PASS");
        } catch (Exception e) {
            reportStep("Failed to select MG2 team Yes: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public Primarydetailspage selectPrioritizationHigh() {
        try {
            waitutil.waitForClick(driver, prioritizationHigh, 10).click();
            reportStep("Selected Prioritization = High", "PASS");
        } catch (Exception e) {
            reportStep("Failed to select Prioritization High: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public Primarydetailspage selectCountry(String countryName) {
        try {
            waitutil.waitForClick(driver, countrySelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, countryInput, 10);
            input.clear();
            input.sendKeys(countryName);
            input.sendKeys(Keys.ENTER);
            reportStep("Selected Country: " + countryName, "PASS");
        } catch (Exception e) {
            reportStep("Failed to select country: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Set date by direct input (MM/dd/yyyy or the format your UI expects)
    public Primarydetailspage setPlanIssuanceDate(String dateValue) {
        try {
            WebElement input = waitutil.waitForVisibility(driver, planIssuanceDateInput, 20);
            input.clear();
            input.sendKeys(dateValue);
            input.sendKeys(Keys.TAB);
            reportStep("Set Plan Issuance Date to: " + dateValue, "PASS");
        } catch (Exception e) {
            reportStep("Failed to set Plan Issuance Date: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public Primarydetailspage selectTierAndType(String tierType) {
        try {
            waitutil.waitForClick(driver, tierTypeSelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, tierTypeInput, 10);
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

    public Primarydetailspage selectDiscipline(String discipline) {
        try {
            waitutil.waitForClick(driver, disciplinesSelect, 20).click();
            WebElement input = waitutil.waitForVisibility(driver, disciplinesInput, 10);
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

    public Primarydetailspage clickAddConsultant() {
        try {
            waitutil.waitForClick(driver, addConsultantBtn, 10).click();
            reportStep("Clicked Add Consultant", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Add Consultant: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Scroll inside drawer to bottom to make Submit visible (good for popups)
    public Primarydetailspage scrollToSubmit() {
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

    public Primarydetailspage clickSubmit() {
        try {
            WebElement btn = waitutil.waitForClick(driver, submitBtn, 20);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            btn.click();
            reportStep("Clicked Submit", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Submit: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

}
