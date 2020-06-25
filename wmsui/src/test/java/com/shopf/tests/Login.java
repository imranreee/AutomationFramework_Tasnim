package com.shopf.tests;

import coreUtils.CoreConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.WmsBaseClass;
import pageObjects.*;

public class Login extends WmsBaseClass {

    private WebDriver driver;
    private LoginPageObject loginPageObject;



    @BeforeSuite(alwaysRun = true)
    public void wmsBeforeSuite() throws Exception{
        System.out.println("WMSBeforeSuite is called");
        driver = getBaseDriver();
        setImplicitWait(10000);
        driver.get(CoreConstants.WMS_STAGE_BASE_URL);
    }




    @BeforeClass(alwaysRun = true)
    public void loginBeforeClass(){
        System.out.println("LoginBeforeClass is called");
        loginPageObject = new LoginPageObject(driver);
    }



    @DataProvider(name = "getUserAuthenticationData")
    public Object[][] getUserAuthenticationData(){
        return new Object[][]{
                {"operator1@voonik.com","password"}
        };
    }



    @Test
    public void verifyAuthenticationWithValidCredentials(String email,String password){
        System.out.println("verifyAuthentication is called");

    }


    @AfterClass(alwaysRun = true)
    public void loginAfterClass(){
        System.out.println("LoginAfterClass Is Called");

    }



    @AfterSuite(alwaysRun = true)
    public void wmsAfterSuite(){
        System.out.println("WMSAfterSuite Is Called");
        quitBaseDriver();
    }
}