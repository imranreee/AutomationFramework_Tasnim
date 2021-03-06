package com.shopf.tests.Returns;

import coreUtils.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.asserts.*;
import pageObjects.*;
import utils.*;

import java.util.*;

public class PickListReturn extends WmsBaseClass {

    private WebDriver driver;
    private HomePageObject homePageObject;
    private ReturnsPageObjects returnsPageObjects;
    private ReturnsPageObjects.ReturnInScanTab returnInScanTab;
    private PickOrdersPageObjects pickOrdersPageObjects;
    private PickOrdersPageObjects.AllPickListsTab allPickListsTab;
    private Assertion assertion;

    @BeforeClass(alwaysRun = true)
    public void pickListReturnBeforeClass() throws Exception {
        System.out.println("Pick List Return Before Class is called");
        driver = getBaseDriver();
        homePageObject = new HomePageObject(driver);
        returnsPageObjects = new ReturnsPageObjects(driver);
        returnInScanTab = returnsPageObjects.new ReturnInScanTab(driver);
        pickOrdersPageObjects = new PickOrdersPageObjects(driver);
        allPickListsTab = pickOrdersPageObjects.new AllPickListsTab(driver);
        assertion = new Assertion();
//        homePageObject.selectWarehouse("Shopup Dhaka");
    }

    @Test(groups = CoreConstants.GROUP_SMOKE,
            dependsOnGroups = "Login.verifyAuthenticationWithValidCredentials",
            description = "Pick List Return Verification")
    public void pickListReturnVerification() {
        System.out.println("Pick List Return Verification is called");


        /*--------------Get Random Pick List Id And Status-------------------*/
        homePageObject.clickPickOrders();
        pickOrdersPageObjects.clickAllPickListsTab();
        int i = 0, total = allPickListsTab.getTotalPickLists();
        Random random = new Random();
        int n = random.nextInt(total)+1;
        String pickListId = allPickListsTab.getPickListID(n);
        String status = allPickListsTab.getStatus(n);
        String reason = allPickListsTab.getPickListReason(n);
        if (status.equalsIgnoreCase("out_for_pickup")
                || status.equalsIgnoreCase("created")
                || reason.equalsIgnoreCase("SINGLE_ORDER")
                || reason.equalsIgnoreCase("MULTIPLE_ORDERS"))
            i = 1;
        System.out.println(pickListId + " " + status);


        /*--------------Input PickList / Return PickList-------------------*/
        homePageObject.clickReturns();
        returnsPageObjects.clickReturnInScanTab();
        returnInScanTab.enterPickListId(pickListId);
        if (i == 1) {
            String message = homePageObject.getPopUpMessage();
            System.out.println(message);
        } else {
            int s, j, t = returnInScanTab.getTotalProducts();
            for (j = 1; j <= t; j++) {
                int q = returnInScanTab.getOrderedQuantity(j);
                int rq = returnInScanTab.getReturnedQuantity(j);
                s = q - rq;
                System.out.println(s);
                if (s != 0) {
                    System.out.println(s);
                    returnInScanTab.returnQuantityInput(j, Integer.toString(s));
                }
            }
            returnInScanTab.clickPickListReturnSubmitButton();
            String message = homePageObject.getPopUpMessage();
            System.out.println(message);
            assertion.assertEquals(message,"Packages are Inwarded");
        }
    }

    @AfterClass(alwaysRun = true)
    public void pickListReturnAfterClass() {
        System.out.println("Pick List Return After Class is called");
    }
}
