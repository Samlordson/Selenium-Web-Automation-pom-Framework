package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.waitutil;
import base.Base;

public class Login extends Base {

    public Login(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // Login fields
    private By emailField = By.xpath("//input[@type='email']");
    private By passwordField = By.xpath("//input[@type='password']");
    private By signInButton = By.xpath("//button[normalize-space(text())='Sign In']");

    // Error validations
    private By invalidLoginError = By.xpath("//div[contains(@class,'toast') or contains(@class,'subtitle') and contains(.,'Invalid') or contains(.,'Invalid Login') or contains(.,'Invalid Login Credentials')]");
    private By emailError = By.xpath("//div[text()='Email is required.']");
    private By passwordError = By.xpath("//div[text()='Password is required.']");

    // Direct text "KS" (profile initials) — exact text match in span
    private By landingRoleHeader = By.xpath(
            "//app-profile-menu//span[normalize-space(text())='MG2 PORTAL ADMIN' or normalize-space(text())='ADMIN']"
        );
    public Login enterEmail(String email) {
        try {
            waitutil.waitForVisibility(driver, emailField, 20).clear();
            waitutil.waitForVisibility(driver, emailField, 20).sendKeys(email);
            reportStep("Entered email: " + email, "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter email: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public Login enterPassword(String password) {
        try {
            waitutil.waitForVisibility(driver, passwordField, 20).clear();
            waitutil.waitForVisibility(driver, passwordField, 20).sendKeys(password);
            reportStep("Entered password", "PASS");
        } catch (Exception e) {
            reportStep("Failed to enter password: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public Login clickSignIn() {
        try {
            waitutil.waitForClick(driver, signInButton, 20).click();
            reportStep("Clicked Sign In", "PASS");
        } catch (Exception e) {
            reportStep("Failed to click Sign In: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    /** Capture transient toaster invalid message (short wait). Returns message or empty string */
    public String getInvalidLoginMessageText() {
        try {
            WebElement el = waitutil.waitForVisibility(driver, invalidLoginError, 6);
            String txt = el.getText().trim();
            reportStep("Captured invalid-login toaster: " + txt, "PASS");
            return txt;
        } catch (Exception e) {
            reportStep("Invalid-login toaster not found within short timeout: " + e.getMessage(), "INFO");
            return "";
        }
    }

    public Login verifyEmailRequiredVisible() {
        try {
            waitutil.waitForVisibility(driver, emailError, 6);
            reportStep("Email required validation visible", "PASS");
        } catch (Exception e) {
            reportStep("Email required validation NOT visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    public Login verifyPasswordRequiredVisible() {
        try {
            waitutil.waitForVisibility(driver, passwordError, 6);
            reportStep("Password required validation visible", "PASS");
        } catch (Exception e) {
            reportStep("Password required validation NOT visible: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }

    /** Verify KS profile initial text exists after login */
    public Login verifyLandingPageVisibleByRole() {
        try {
            waitutil.waitForVisibility(driver, landingRoleHeader, 30);
            reportStep("Landing page visible — Role header found (MG2 PORTAL ADMIN / ADMIN)", "PASS");
        } catch (Exception e) {
            reportStep("Landing page NOT visible — Role header missing: " + e.getMessage(), "FAIL");
            throw e;
        }
        return this;
    }
}
