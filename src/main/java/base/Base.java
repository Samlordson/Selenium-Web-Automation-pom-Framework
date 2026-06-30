package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.MediaEntityBuilder;

import pages.Login;
import pages.SelectRoles;

public class Base {

    public RemoteWebDriver driver;
    public static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public String testName, testdesc, testcategory, testAuthor;

    private static final Logger log = LogManager.getLogger(Base.class);

    private static final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    private static final String reportPath = "./reports/results_" + timeStamp + ".html";
    private static final String screenshotFolder = "./reports/images_" + timeStamp;

    @BeforeSuite(alwaysRun = true)
    public void startReport() {
        try {
            new File(screenshotFolder).mkdirs();
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Environment", "DEV");
            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName("Extent Report");
            log.info("Extent Report initialized at: " + reportPath);
        } catch (Exception e) {
            log.error("Failed to initialize Extent Report", e);
        }
    }

    /**
     * Precondition: start browser and optionally login+select role if email provided in XML.
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "email", "password", "role"})
    public void precondition(@Optional("chrome") String browser,
                             @Optional("") String email,
                             @Optional("") String password,
                             @Optional("") String role,
                             Method method) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        log.info("Browser launched");

        driver.get("https://vsportal2-qa-dmz.mg2.com/");
        log.info("Navigated to application");

        Thread.sleep(1500);

        ExtentTest extentTest = extent.createTest(method.getName(), testdesc);
        if (testcategory != null) extentTest.assignCategory(testcategory);
        if (testAuthor != null) extentTest.assignAuthor(testAuthor);
        test.set(extentTest);

        // optional login flow only if email param is provided (non-empty)
        if (email != null && !email.trim().isEmpty()) {
            try {
                new Login(driver).enterEmail(email).enterPassword(password).clickSignIn();
                reportStep("Precondition: attempted login with XML credentials", "PASS");

                // short sleep to allow role dropdown to appear
                Thread.sleep(800);

                if (role != null && !role.trim().isEmpty()) {
                    try {
                        new SelectRoles(driver).selectAdminRole();
                        reportStep("Precondition: selected role -> " + role, "PASS");
                    } catch (Exception e) {
                        reportStep("Precondition: role selection failed -> " + e.getMessage(), "FAIL");
                        throw e;
                    }
                }
            } catch (Exception e) {
                reportStep("Precondition (login/role) failed: " + e.getMessage(), "FAIL");
                throw new RuntimeException(e);
            }
        } else {
            reportStep("Precondition: No credentials provided in XML — skipping auto-login", "INFO");
        }
    }

    public void reportStep(String message, String status) {
        try {
            String imagePath = takeSnap();
            if (status.equalsIgnoreCase("PASS")) {
                test.get().pass(message, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
                log.info("PASS: " + message);
            } else if (status.equalsIgnoreCase("FAIL")) {
                test.get().fail(message, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
                log.error("FAIL: " + message);
            } else if (status.equalsIgnoreCase("WARNING")) {
                test.get().warning(message, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
                log.warn("WARNING: " + message);
            } else {
                test.get().info(message);
                log.info("INFO: " + message);
            }
        } catch (IOException e) {
            log.error("Failed to attach screenshot: " + e.getMessage());
            test.get().info("Screenshot capture failed.");
        }
    }

    public String takeSnap() throws IOException {
        int random = (int) (Math.random() * 999999);
        File src = driver.getScreenshotAs(OutputType.FILE);
        String fileName = "img" + random + ".png";
        String fullPath = screenshotFolder + "/" + fileName;
        File dest = new File(fullPath);
        FileUtils.copyFile(src, dest);
        return "images_" + timeStamp + "/" + fileName;
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                test.get().fail(result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.get().pass("Test passed");
            } else {
                test.get().skip("Test skipped");
            }
        } catch (Exception e) {
            log.error("Error while logging result in ExtentReport", e);
        }

        if (driver != null) {
            driver.quit();
            log.info("Browser closed");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void stopReport() {
        extent.flush();
        log.info("Extent Report flushed to: " + reportPath);
    }
}
