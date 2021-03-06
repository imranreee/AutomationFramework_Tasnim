package com.redx.tests;

import coreUtils.*;
import io.appium.java_client.android.*;
import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;
import utils.*;
import java.util.*;

public class CreateParcel extends RedXBaseClass
{
    private AndroidDriver<WebElement> androidDriver;
    private HomePageObjects homePageObjects;
    private AddParcelPageObjects addParcelPageObjects;
    private SettingsPageObjects settingsPageObjects;
    private Authentication authentication;
    private ChangeLanguage changeLanguage;
    private HomePageObjects.ChooseShopModule chooseShopModule;

    public void pageInitializer()
    {
        homePageObjects = new HomePageObjects();
        addParcelPageObjects = new AddParcelPageObjects();
        settingsPageObjects = new SettingsPageObjects();
        authentication = new Authentication();
        changeLanguage = new ChangeLanguage();
        chooseShopModule = homePageObjects. new ChooseShopModule();
    }

    @BeforeSuite(alwaysRun = true)
    public void redXAndroidBeforeSuite()
    {
        System.out.println("redXAndroidBeforeSuite is called");
        androidDriver = getBaseDriver();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeCreateParcelClass() throws Exception
    {
        System.out.println("Before Create Parcel Class");
        androidDriver = getBaseDriver();
        pageInitializer();
    }

    @DataProvider(name = "getUserAuthenticationData")
    public Object[][] getUserAuthenticationData(){
        return new Object[][]{
                {"01401122188","6666"}
        };
    }

    @Test(  groups = {"Authentication.verifyAuthenticationWithWrongOTP",
            CoreConstants.GROUP_SANITY},
            description = "Verifies Authentication With Wrong OTP",
            priority = 1,
            dataProvider = "getUserAuthenticationData")
    public void verifyAuthenticationWithWrongOTP(String mobileNumber,String otp) throws Exception {
        System.out.println("Verify authentication with Wrong OTP was called");
        authentication.authenticationSetUp();
        authentication.verifyAuthenticationWithValidCredentials(mobileNumber,otp);}

    @Test(  groups = {"Settings.verifyChangeLanguageToENGLISH",
            CoreConstants.GROUP_SANITY},
            description = "Change Language To English",
            priority = 2)
    public void changeToEnglishLanguage() throws Exception {
       changeLanguage.beforeChangeLanguageClass();
        changeLanguage.changeToEnglishLanguage();
    }

    @Test(  groups = {CoreConstants.GROUP_SMOKE, CoreConstants.GROUP_REGRESSION},
            description = "Create New Parcel",
            dependsOnGroups = {"Authentication.verifyAuthenticationWithValidCredentials"})
    public void createNewParcel()
    {
        System.out.println("Creating New Parcel");
        String assertVariable = null;
//        homePageObjects.clickChooseShopModule();
//        chooseShopModule.selectShopByText("ashok shops");
        homePageObjects.clickDeliverYourParcelModule();
        sleep(500);
        Assert.assertEquals(addParcelPageObjects.getPageTitle(),"Add parcel");
        addParcelPageObjects.addParcel();
        try {
            assertVariable = PropertyReader.getValueOfKey(PropertyReader.Keys.SHOP_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Shop Name cannot be read from Properties");
        }
        Assert.assertEquals(homePageObjects.getCurrentShopName(),assertVariable);
//        Assert.assertEquals(homePageObjects.getToastMessage(),"1 parcels added");
    }

    @Test(  groups = {CoreConstants.GROUP_SANITY},
            priority = 3,
            description = "Verification of mandatory filed popUp on create parcel page")
    public void verifyMandatoryFieldCautionMessagePopup()
    {System.out.println("verification of mandatory field caution message popUp was called");
        homePageObjects.clickChooseShopModule();
        chooseShopModule.selectShopByText("ashok shops");
        homePageObjects.clickSkipIntroButton();
    homePageObjects.clickDeliverYourParcelModule();
    addParcelPageObjects.clickConfirmButton();
        List<String> cautionMessages = new ArrayList<>();
        cautionMessages.add("Name is required");
        cautionMessages.add("Phone is required");
        cautionMessages.add("Address is required");
//        cautionMessages.add("Area is required");
//        cautionMessages.add("Selling price is required");
        Assert.assertEquals(addParcelPageObjects.createParcelCautionMessages(),cautionMessages);
    }

    @AfterClass(alwaysRun = true)
    public void afterCreateParcelClass()
    {
        System.out.println("After Create Parcel Class");
    }

    @AfterSuite(alwaysRun = true)
    public void redXAndroidAfterSuite(){

        System.out.println("redXAndroidAfterSuite Is Called");
        quitBaseDriver();

    }



}
