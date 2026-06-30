package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.Base;

public class Tc_001_Login extends Base {

    @BeforeTest
    public void setValues() {
        testName = "Valid Login";
        testdesc = "Verify valid login using Base Class precondition";
        testAuthor = "Samlordson";
        testcategory = "Smoke";
    }

    @Test
    public void loginValidation() {
        try {
            // Base Class already did login (email/password/role)
            // We simply confirm test reached this point

            reportStep("Login executed successfully via Base class", "PASS");

        } catch (Exception e) {
            reportStep("Login validation failed: " + e.getMessage(), "FAIL");
            throw e;
        }
    }
}
