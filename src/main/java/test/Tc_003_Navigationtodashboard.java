package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.Base;
import pages.Login;
import pages.hamburgermenunavigationpage;

public class Tc_003_Navigationtodashboard extends Base{
	@BeforeTest
    public void setValues() {
        testName = "Verify navigation to changemanagenent";
        testdesc = "Test the navigation to changemeanagement";
        testAuthor = "Samlordson";
        testcategory = "Sanity";
    }

    @Test
    public void performLogin() {
        
        //new SelectRolespages(driver)
       // .clickroledropdown()
       // .selectAdmin();
        new hamburgermenunavigationpage(driver).//clickHamburgermenu()
        clickchangemanagement()
        .clickdashboard();
        
    }

}
