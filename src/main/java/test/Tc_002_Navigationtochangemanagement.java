package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.Base;
import pages.Login;

import pages.hamburgermenunavigationpage;

public class Tc_002_Navigationtochangemanagement extends Base{
	@BeforeTest
    public void setValues() {
        testName = "Verify navigation to changemanagenent";
        testdesc = "Test the navigation to changemeanagement";
        testAuthor = "Samlordson";
        testcategory = "Sanity";
    }

    @Test
    public void performLogin() {
       
        new hamburgermenunavigationpage(driver).//clickHamburgermenu()
        clickchangemanagement();
        
    }
}



