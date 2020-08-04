package com.shopf.tests.PickOrders;

import coreUtils.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;
import pageObjects.*;
import utils.*;

public class AddingPickList extends WmsBaseClass {

    private WebDriver driver;
    private HomePageObject homePageObject;
    private PickOrdersPageObjects pickOrdersPageObjects;
    private PickOrdersPageObjects.DemandLessPickListTab demandLessPickListTab;
    private PickOrdersPageObjects.AllPickListsTab allPickListsTab;
    private Assertion assertion;

    @BeforeClass(alwaysRun = true)
    public void addingPickListBeforeClass() throws Exception {
        System.out.println("Adding Pick List Before Class is called");
        driver = getBaseDriver();
        homePageObject = new HomePageObject(driver);
        pickOrdersPageObjects = new PickOrdersPageObjects(driver);
        demandLessPickListTab = new PickOrdersPageObjects(driver).new DemandLessPickListTab(driver);
        allPickListsTab = new PickOrdersPageObjects(driver).new AllPickListsTab(driver);
        assertion = new Assertion();
    }

    @Test(groups = (CoreConstants.GROUP_SMOKE),
            dependsOnGroups = ("Login.verifyAuthenticationWithValidCredentials"),
            description = "Adds Pick List")
    public void verifyPickListAddition() {
        System.out.println("Pick List Addition Verification is called");
        homePageObject.clickPickOrders();
        sleep(2000);
        pickOrdersPageObjects.clickDemandLessPickListTab();
        homePageObject.selectWarehouse("Shopup Dhaka");
        demandLessPickListTab.enterWarehouseDetails();
        demandLessPickListTab.createPickListOrder();
        String message = homePageObject.getPopUpMessage();
        System.out.println(message);
        assertion.assertEquals(message,"PickList Created");
        pickOrdersPageObjects.clickAllPickListsTab();
        String pickListId = allPickListsTab.getPickListID(1);
        System.out.println("The last added Pick List: " + pickListId);
    }

    @AfterClass(alwaysRun = true)
    public void addingPickListAfterClass() {
        System.out.println("Adding Pick List After Class is called");
    }
}
