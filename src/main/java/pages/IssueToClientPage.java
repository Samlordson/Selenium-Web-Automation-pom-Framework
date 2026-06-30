package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

import java.time.Duration;

public class IssueToClientPage extends Base {

    WebDriver driver;
    WebDriverWait wait;

    // Locators — MG2 Design & Arch Entry
    private By sheetsAffectedInput = By.xpath("//input[@placeholder='Enter Sheets Affected' or @placeholder='Sheets Affected']");
    private By detailsAffectedInput = By.xpath("//input[@placeholder='Enter Details Affected']");
    private By specSectionAffectedInput = By.xpath("//input[@placeholder='Enter Spec Section Affected']");
    private By changeNarrativeTextarea = By.xpath("//textarea[@placeholder='Enter Change Narrative']");

    // MG2 Specifications Entry
    private By yesNewSpec = By.xpath("//label[contains(text(),'Yes')]/preceding-sibling::input[@type='radio']");
    private By noNewSpec = By.xpath("//label[contains(text(),'No')]/preceding-sibling::input[@type='radio']");
    private By newSpecificationInput = By.xpath("//input[@placeholder='Enter New Specification']");

    // MG2 Cost Estimating Entry
    private By estimatedCostInput = By.xpath("//input[@placeholder='Enter Estimated Cost']");
    private By costAnalysisSummaryTextarea = By.xpath("//textarea[@placeholder='Enter Analysis Summary']");

    // Buttons
    private By submitBtn = By.xpath("//button[contains(text(),'Submit')]");
    private By sendToClientBtn = By.xpath("//button[contains(text(),'Send To Client')]");
    private By callBackBtn = By.xpath("//button[contains(text(),'Call Back')]");

    // Constructor
    public IssueToClientPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Actions
    public IssueToClientPage enterSheetsAffected(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sheetsAffectedInput)).sendKeys(text);
        return this;
    }

    public IssueToClientPage enterDetailsAffected(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(detailsAffectedInput)).sendKeys(text);
        return this;
    }

    public IssueToClientPage enterSpecSectionAffected(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(specSectionAffectedInput)).sendKeys(text);
        return this;
    }

    public IssueToClientPage enterChangeNarrative(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(changeNarrativeTextarea)).sendKeys(text);
        return this;
    }

    public IssueToClientPage selectNewSpecificationRequired(boolean required) {
        if (required)
            wait.until(ExpectedConditions.elementToBeClickable(yesNewSpec)).click();
        else
            wait.until(ExpectedConditions.elementToBeClickable(noNewSpec)).click();
        return this;
    }

    public IssueToClientPage enterNewSpecification(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(newSpecificationInput)).sendKeys(text);
        return this;
    }

    public IssueToClientPage enterEstimatedCost(String cost) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(estimatedCostInput)).sendKeys(cost);
        return this;
    }

    public IssueToClientPage enterCostAnalysisSummary(String summary) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(costAnalysisSummaryTextarea)).sendKeys(summary);
        return this;
    }

    public IssueToClientPage clickSendToClient() {
        wait.until(ExpectedConditions.elementToBeClickable(sendToClientBtn)).click();
        return this;
    }

    public IssueToClientPage clickCallBack() {
        wait.until(ExpectedConditions.elementToBeClickable(callBackBtn)).click();
        return this;
    }

    public IssueToClientPage clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        return this;
    }
}
