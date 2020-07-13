package com.shopf.tests;

import coreUtils.CoreConstants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.PurchaseOrdersPageObjects;
import utils.WmsBaseClass;

public class GRNCreation extends WmsBaseClass {
    private WebDriver driver;
    private HomePageObject homePageObject;
    private PurchaseOrdersPageObjects purchaseOrdersPageObjects;
    private PurchaseOrdersPageObjects.PurchaseOrderList purchaseOrderList;
    private PurchaseOrdersPageObjects.CreateGRNTab createGRNTab;

    @BeforeClass(alwaysRun = true)
    public void createGRNBeforeClass() throws Exception{
        System.out.println("Create GRN Before Class is called");
        driver = getBaseDriver();
        homePageObject = new HomePageObject(driver);
        purchaseOrdersPageObjects = new PurchaseOrdersPageObjects(driver);
        purchaseOrderList = new PurchaseOrdersPageObjects(driver).new PurchaseOrderList(driver);
        createGRNTab = new PurchaseOrdersPageObjects(driver).new CreateGRNTab(driver);
    }

    @Test(groups = (CoreConstants.GROUP_SMOKE),
            dependsOnGroups = "Login.verifyAuthenticationWithValidCredentials",
            description = "Create GRN Verification")
    public void createGRNVerification(){
        System.out.println("Create GRN Verification is called");
        homePageObject.clickPurchaseOrders();
        purchaseOrdersPageObjects.clickPurchaseOrderListTab();
        sleep(1000);
        int i,total = purchaseOrderList.getTotalPurchaseOrders();
        for(i=1;i <= total;i++)
            if(purchaseOrderList.getStatus(i).equalsIgnoreCase("CREATED"))
                break;
        String poId = purchaseOrderList.getPOID(i);
        purchaseOrdersPageObjects.clickCreateGRNTab();
        sleep(2000);
        createGRNTab.poIDEntry(poId);
        sleep(2000);
        createGRNTab.clickGRNButton();
        setImplicitWait(3);
        String message = homePageObject.getPopUpMessage();
        System.out.println(message);
        purchaseOrdersPageObjects.clickPurchaseOrderListTab();
        sleep(2000);
    }

    @AfterClass(alwaysRun = true)
    public void createGRNAfterClass(){
        System.out.println("Create GRN After Class is called");
    }
}