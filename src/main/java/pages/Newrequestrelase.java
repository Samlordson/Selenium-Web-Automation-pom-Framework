package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import base.waitutil;

public class Newrequestrelase extends Base {

    public Newrequestrelase(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // ===== Locators =====
    private By drawerContainer = By.cssSelector("div.drawer-content");

    // Country (ng-select style near label "Country")
    private By countrySelect = By.xpath("(//label[normalize-space(text())='Country']/following::ng-select)[2]");
    private By countryInput = By.xpath("(//label[normalize-space(text())='Country']/following::ng-select[1]//input[1])[2]");

    // Prototype (simple input)
    private By prototypeInput = By.xpath("//label[contains(normalize-space(.),'Prototype')]/following::input[1]");

    // Change Release Date (date picker input)
    private By changeReleaseDateInput = By.xpath("//label[normalize-space(text())='Change Release Date']/following::input[1]");

    // Buttons
    private By cancelBtn = By.xpath("//div[contains(@class,'confirm-btn')]//button[normalize-space(text())='Cancel']");
    private By createBtn = By.xpath("//div[contains(@class,'confirm-btn')]//button[normalize-space(text())='Create' or @type='submit']");

    // Validation messages (examples)
    private By countryRequiredError = By.xpath("//label[normalize-space(text())='Country']/following::div[contains(text(),'Country is required') or contains(@class,'ng-invalid')][1]");
    private By prototypeRequiredError = By.xpath("//label[contains(normalize-space(.),'Prototype')]/following::div[contains(text(),'Prototype') or contains(@class,'ng-invalid')][1]");
    private By changeReleaseDateRequiredError = By.xpath("//label[normalize-space(text())='Change Release Date']/following::div[contains(text(),'required') or contains(@class,'ng-invalid')][1]");

    // ===== Actions =====

    // Wait for the drawer/popup to appear
    public Newrequestrelase waitForPopup() {
        try {
            waitutil.waitForVisibility(driver, drawerContainer, 30);
            reportStep("Create Change Release popup is visible", "PASS");
        } catch (Exception e) {
            reportStep("Create Change Release popup did not appear: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Select country by typing and pressing Enter (works with ng-select style)
    public Newrequestrelase selectCountry(String countryName) {
        try {
            waitutil.waitForClick(driver, countrySelect, 30).click();
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

    // Enter prototype text
    public Newrequestrelase enterPrototype(String prototype) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, prototypeInput, 30);
            el.clear();
            el.sendKeys(prototype);
            reportStep("Entered Prototype: " + prototype, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter Prototype: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Set change release date (type date string in expected format, or open calendar and pick)
    // Example date format expected by this UI: mm-dd-yyyy (visible placeholder). Use matching format.
    public Newrequestrelase setChangeReleaseDate(String dateValue) {
        try {
            WebElement el = waitutil.waitForVisibility(driver, changeReleaseDateInput, 30);
            el.clear();
            el.sendKeys(dateValue);
            el.sendKeys(Keys.TAB); // close the calendar picker if any
            reportStep("Set Change Release Date to: " + dateValue, "PASS");
        } catch (Exception e) {
            reportStep("Failed to set Change Release Date: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // If you prefer to pick day from the calendar overlay (optional)
    public Newrequestrelase pickChangeReleaseDay(String day) {
        try {
            // open calendar
            waitutil.waitForClick(driver, changeReleaseDateInput, 30).click();
            By dayLocator = By.xpath("//div[contains(@class,'cdk-overlay-pane')]//button[normalize-space(text())='" + day + "']");
            waitutil.waitForClick(driver, dayLocator, 20).click();
            reportStep("Picked day " + day + " from calendar", "PASS");
        } catch (Exception e) {
            reportStep("Failed to pick day: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Scroll drawer to bottom (useful before clicking Create)
    public Newrequestrelase scrollDrawerToBottom() {
        try {
            WebElement drawer = waitutil.waitForVisibility(driver, drawerContainer, 30);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", drawer);
            reportStep("Scrolled drawer to bottom", "PASS");
        } catch (Exception e) {
            reportStep("Failed to scroll drawer: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Click Create button (scroll into view first)
    public Newrequestrelase clickCreate() {
        try {
            WebElement btn = waitutil.waitForClick(driver, createBtn, 30);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            btn.click();
            reportStep("Clicked Create", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Create: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // Click Cancel
    public Newrequestrelase clickCancel() {
        try {
            waitutil.waitForClick(driver, cancelBtn, 30).click();
            reportStep("Clicked Cancel", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Cancel: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    // ===== Validations =====

    public Newrequestrelase verifyCountryRequiredVisible() {
        try {
            waitutil.waitForVisibility(driver, countryRequiredError, 30);
            reportStep("Country required validation visible", "PASS");
        } catch (Exception e) {
            reportStep("Country required validation not visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public Newrequestrelase verifyPrototypeRequiredVisible() {
        try {
            waitutil.waitForVisibility(driver, prototypeRequiredError, 30);
            reportStep("Prototype required validation visible", "PASS");
        } catch (Exception e) {
            reportStep("Prototype required validation not visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public Newrequestrelase verifyChangeReleaseDateRequiredVisible() {
        try {
            waitutil.waitForVisibility(driver, changeReleaseDateRequiredError, 30);
            reportStep("Change Release Date required validation visible", "PASS");
        } catch (Exception e) {
            reportStep("Change Release Date validation not visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
}
