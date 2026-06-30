package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.Base;
import pages.Login;

import pages.hamburgermenunavigationpage;

public class Tc_004_Navigationtoconfig extends Base {
	@BeforeTest
    public void setValues() {
        testName = "Verify navigation to config";
        testdesc = "Test the navigation to config";
        testAuthor = "Samlordson";
        testcategory = "Sanity";
    }

    @Test
    public void performLogin() {
       
       // new SelectRolespages(driver) 
       // .clickroledropdown()
      //  .selectAdmin();
        new hamburgermenunavigationpage(driver).//clickHamburgermenu()
        clickchangemanagement()
        .clickconfig();
        
    }


}
