package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;

import base.Base;
import base.waitutil;

public class SelectRoles extends Base {

    public SelectRoles(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // Dropdown
    private By roleDropdown = By.xpath("//ng-select[contains(@class,'role')]");

    // Accepts BOTH:
    // - ADMIN
    // - MG2 PORTAL ADMIN
    private By adminRoleOption = By.xpath(
        "//span[normalize-space(text())='ADMIN' or normalize-space(text())='MG2 PORTAL ADMIN']"
    );

    // ----------------------------
    // ONE ACTION METHOD
    // ----------------------------
    public SelectRoles selectAdminRole() {
        try {
            // Open dropdown
            waitutil.waitForClick(driver, roleDropdown, 20).click();
            reportStep("Clicked Role dropdown", "PASS");

            // Click ADMIN or MG2 PORTAL ADMIN
            WebElement role = waitutil.waitForClick(driver, adminRoleOption, 20);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", role);
            role.click();

            reportStep("Selected role: ADMIN / MG2 PORTAL ADMIN", "PASS");

        } catch (Exception e) {
            reportStep("Failed to select Admin role: " + e.getMessage(), "FAIL");
            throw new RuntimeException(e);
        }

        return this;
    }
}
