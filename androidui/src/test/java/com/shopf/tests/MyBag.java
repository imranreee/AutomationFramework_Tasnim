package com.shopf.tests;

import coreUtils.*;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import pageObjects.*;
import utils.*;
import java.util.*;


public class MyBag extends AndroidBaseClass {


    private AndroidDriver<WebElement> androidDriver;
    private MyBagPageObjects myBagPageObjects;
    private MyBagPageObjects.ItemContainer itemContainer;
    private ActionBarObjects actionBarObjects;
    private MyBagPageObjects.CreditsAndCoupons creditsAndCoupons;
    private CheckoutAddressPageObjects checkoutAddressPageObjects;
    private CheckoutAddressPageObjects.SelectAddress selectAddress;
    private PaymentModePageObjects paymentModePageObjects;
    private CheckoutAddressPageObjects.EstimatedDeliveryDates estimatedDeliveryDates;
    private CheckoutAddressPageObjects.EstimatedDeliveryDates.EstimatedDeliveryDatesItems estimatedDeliveryDatesItems;
    private TouchAction touch ;
    private MyActions myActions;
    private OrderSuccessFulPageObjects orderSuccessFulPageObjects;


    @BeforeClass(alwaysRun = true)
    public void myBagBeforeClass(){
        System.out.println("MyBagBeforeClass is called");
        androidDriver = getBaseDriver();
        actionBarObjects = new ActionBarObjects(androidDriver);
        //actionBarObjects.clickOnBagImageButton();
        myBagPageObjects = new MyBagPageObjects(androidDriver);
        //itemContainer = myBagPageObjects.new ItemContainer(androidDriver);
        actionBarObjects = new ActionBarObjects(androidDriver);
        itemContainer = myBagPageObjects.new ItemContainer(androidDriver);
        creditsAndCoupons = myBagPageObjects.new CreditsAndCoupons(androidDriver);
        checkoutAddressPageObjects = new CheckoutAddressPageObjects(androidDriver);
        selectAddress = checkoutAddressPageObjects.new SelectAddress(androidDriver);
        paymentModePageObjects = new PaymentModePageObjects(androidDriver);
        estimatedDeliveryDates = checkoutAddressPageObjects.new EstimatedDeliveryDates(androidDriver);
        estimatedDeliveryDatesItems = estimatedDeliveryDates.new EstimatedDeliveryDatesItems(androidDriver);
        myActions = new MyActions();
        // This Block is responsible to get the control from anywhere to MyBag
        //actionBarObjects.clickOnBagImageButton();
        sleep(3000);
        orderSuccessFulPageObjects = new OrderSuccessFulPageObjects(androidDriver);
        // This Block is responsible to get the control from anywhere to MyBag
        switchFromNativeToWeb(CoreConstants.SHOP_UP_RESELLER_WEB_VIEW);
        sleep(5000);
    }



    @Test(  groups = {"MyBag.verifyItemIncrementFunctionalityOnMyBag",
            CoreConstants.GROUP_SANITY,
            CoreConstants.GROUP_SMOKE,
            CoreConstants.GROUP_FUNCTIONAL,
            CoreConstants.GROUP_REGRESSION},
            enabled = true,
            description = "Verify ItemIncrement Functionality On MyBag",
            dependsOnGroups = "Authentication.verifyEditMobileNumber"  )
    public void verifyItemIncrementFunctionalityOnMyBag(){
        int containersSize = itemContainer.getListOfItemContainers();
        System.out.println("List Of Item Containers is : "+containersSize);
        if(containersSize>0){
            Random random = new Random();
            int count = random.nextInt(containersSize)+1;
            System.out.println("Count is : "+count);
            System.out.println("Container Selected is : "+count);
            System.out.println("List Of Add Qt Button is : "+
                    itemContainer.getListOfAddQuantityButton().size());
            itemContainer.clickOnAddQuantityButton(itemContainer.getListOfAddQuantityButton().get(count-1));
        }else {
            // Function call to create an item : Handle this at Before Class Only
        }

    }


    @Test(  groups = {"MyBag.verifyDeleteItemFromMyBag",
            CoreConstants.GROUP_SMOKE,
            CoreConstants.GROUP_FUNCTIONAL,
            CoreConstants.GROUP_REGRESSION},
            enabled = true,
            description = "Verify Delete Item From MyBag",
            dependsOnMethods = "verifyItemIncrementFunctionalityOnMyBag"  )
    public void verifyDeleteItemFromMyBag(){
        sleep(5000);
        int itemCounterSize = itemContainer.getListOfItemContainers();
        System.out.println("itemContainer Size is : "+itemCounterSize);
        if(itemCounterSize>1){
            Random random = new Random();
            int count = random.nextInt(itemCounterSize);
            System.out.println("Container Selected is : "+count);
            System.out.println("List Of Cancel Icons Before Cancel: "+
                    itemContainer.getListOfCancelIcons().size());
            itemContainer.clickOnCancelItem(itemContainer.getListOfCancelIcons().get(count));
        }else {
            // Function call to create an item : Handle this at Before Class Only
        }
    }


    @Test(   groups = {"MyBag.verifyMinAndMaxSalePrice",
             CoreConstants.GROUP_SANITY,
             CoreConstants.GROUP_FUNCTIONAL,
             CoreConstants.GROUP_REGRESSION},
            enabled = true,
             description = "verify Min and Max Sale Price",
            dependsOnMethods = "verifyDeleteItemFromMyBag")
    public void verifyMinAndMaxSalePrice(){
        itemContainer.checkingMaxPrice(2);
        itemContainer.checkingMinPrice(2);
    }


    @Test(  groups = {"MyBag.verifyApplyingShippingCharges",
            CoreConstants.GROUP_SMOKE,
            CoreConstants.GROUP_SANITY,
            CoreConstants.GROUP_FUNCTIONAL,
            CoreConstants.GROUP_REGRESSION},
            description = "Verify Applying Shipping Charges From MyBag",
            dependsOnMethods = "verifyMinAndMaxSalePrice"  )
    public void verifyApplyingShippingCharges(){
        sleep(5000);
        String shippingCharges = "70";
        int minShippingCharge = 49;
        creditsAndCoupons.applyShippingCharges(shippingCharges);
            sleep(5000);
    }



    @Test(  groups = {"MyBag.verifyPlaceOrderInMyBag",
            CoreConstants.GROUP_SMOKE,
            CoreConstants.GROUP_SANITY,
            CoreConstants.GROUP_FUNCTIONAL,
            CoreConstants.GROUP_REGRESSION},
            description = "Verify Place Order From MyBag",
            dependsOnMethods = "verifyApplyingShippingCharges"  )
    public void verifyPlaceOrderInMyBag(){
        myBagPageObjects.clickOnPlaceOrderButton();
    }


    @Test(  groups = {"MyBag.verifySelectAddressInMyBag",
            CoreConstants.GROUP_SMOKE,
            CoreConstants.GROUP_SANITY,
            CoreConstants.GROUP_FUNCTIONAL,
            CoreConstants.GROUP_REGRESSION},
            enabled = true,
            description = "Verify Select Address From MyBag",
            dependsOnMethods = "verifyPlaceOrderInMyBag"  )
    public void verifySelectAddressInMyBag(){
        System.out.println("Address List is : "+selectAddress.getListOfVisibleAddress().size());
        selectAddress.selectAnAddress(selectAddress.getListOfVisibleAddress().get(1));
//        if(selectAddress.getListOfVisibleAddress().size()>1){
//            selectAddress.clickOnShowMoreAddress();
//            selectAddress.selectAnAddress(selectAddress.getListOfVisibleAddress().get(1));
//        }else {
//            selectAddress.selectAnAddress(selectAddress.getListOfVisibleAddress().get(0));
//        }

    }


    @Test(  groups = {"MyBag.verifyDeleteProductInAddressPage",
            CoreConstants.GROUP_SMOKE,
            CoreConstants.GROUP_SANITY,
            CoreConstants.GROUP_FUNCTIONAL,
            CoreConstants.GROUP_REGRESSION},
            enabled = true,
            description = "verify Delete Product",
            dependsOnMethods = "verifyPlaceOrderInMyBag")
    public void verifyDeleteProductInEstimatedDeliveryPage(){
        sleep(5000);
        if(estimatedDeliveryDates.getListOfEstimatedDeliveryItems().size()>1){
            estimatedDeliveryDatesItems.clickOnEstimatedDeliveryItem(
                    estimatedDeliveryDatesItems.
                            getListOfEstimatedDeliveryItemDelete().get(0));
        }else {
            System.out.println("Only one element is present");
        }

    }


    @Test(  groups = {"MyBag.verifyCheckoutProceedInMyBag",
            CoreConstants.GROUP_SMOKE,
            CoreConstants.GROUP_SANITY,
            CoreConstants.GROUP_FUNCTIONAL,
            CoreConstants.GROUP_REGRESSION},
            description = "Verify Checkout Proceed From MyBag",
            dependsOnMethods = "verifyDeleteProductInEstimatedDeliveryPage"  )
    public void verifyCheckoutProceedInMyBag(){
        sleep(5000);
        checkoutAddressPageObjects.clickOnProceedToPaymentTopButton();
    }


    @Test(  groups = {"MyBag.verifyProceedPaymentWithoutChangeAddress",
            CoreConstants.GROUP_SMOKE,
            CoreConstants.GROUP_FUNCTIONAL,
            CoreConstants.GROUP_REGRESSION},
            description = "Verify Proceed Payment Without Change Address",
            dependsOnMethods = "verifyCheckoutProceedInMyBag"  )
    public void verifyProceedPaymentWithoutChangeAddress(){
        sleep(3000);
        paymentModePageObjects.proceedPaymentWithoutChangeAddressThroughTopButton();
        sleep(3000);
    }



    @Test(  groups = {"MyBag.verifyProceedPaymentWithChangeAddress",
            CoreConstants.GROUP_SANITY,
            CoreConstants.GROUP_FUNCTIONAL,
            CoreConstants.GROUP_REGRESSION},
            description = "Verify Proceed Payment With Change Address",
            dependsOnMethods = "verifyCheckoutProceedInMyBag"  )
    public void verifyProceedPaymentWithChangeAddress(){
        paymentModePageObjects.clickOnChangeAddress();
        sleep(3000);
        selectAddress.clickOnShowMoreAddress();
        selectAddress.selectAnAddress(selectAddress.getListOfVisibleAddress().get(4));
        sleep(3000);
        checkoutAddressPageObjects.clickOnProceedToPaymentBottomButton();
        paymentModePageObjects.proceedPaymentWithoutChangeAddressThroughTopButton();
        sleep(3000);
    }



    @AfterClass(alwaysRun = true)
    public void myBagAfterClass(){
        System.out.println("MyBagAfterClass is called");
        orderSuccessFulPageObjects.clickOnClickHereButton();
    }
}