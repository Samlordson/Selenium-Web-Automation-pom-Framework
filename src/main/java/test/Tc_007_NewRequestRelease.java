package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.Base;
import pages.Login;
import pages.Newrequestrelase;

import pages.config;
import pages.hamburgermenunavigationpage;

public class Tc_007_NewRequestRelease extends Base {

    @BeforeTest
    public void setValues() {
        testName = "Verify New Request Release";
        testdesc = "Verify that new change release can be created using TestNG parameters";
        testAuthor = "Samlordson";
        testcategory = "Sanity";
    }

    @Test
    @Parameters({"email", "password", "country", "prototype", "releaseDate"})
    public void performLogin(String email, String password, String country, String prototype, String releaseDate) {

        new Login(driver)
            .enterEmail(email)
            .enterPassword(password)
            .clickSignIn();

       // new SelectRolespages(driver)
         //   .clickroledropdown()
         //   .selectAdmin();

        new hamburgermenunavigationpage(driver)
        //  .clickHamburgermenu()
          .clickchangemanagement()
          .clickconfig();

        new config(driver)
            .clicknewchangerelease();

        new Newrequestrelase(driver)
            .selectCountry(country)
            .enterPrototype(prototype)
            .setChangeReleaseDate(releaseDate)
            .clickCreate();
    }
}
