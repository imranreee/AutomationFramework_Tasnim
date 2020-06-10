package MyPageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.CreateCollectionBottomSheetObjects;
import utils.MyActions;

import java.util.Random;

public class MyShopPageObjects {
    private AndroidDriver<WebElement> driver;
    private MyActions myActions;

    public MyShopPageObjects(AndroidDriver<WebElement> androidDriver){
        this.driver = androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver),this);
        myActions = new MyActions();
    }


    /*-------------Collections Tab Elements------------------*/

    // MyCollections Tab Item
    @FindBy(xpath = "//p[contains(text(),'My Collections')]")
    private WebElement myCollectionTabItem;

    // ExclusiveCollections Tab Item
    @FindBy(xpath = "//p[contains(text(),'Exclusive Collections')]")
    private WebElement exclusiveCollectionTabItem;


    public void clickOnMyCollectionTabItem(){
        myActions.action_click(myCollectionTabItem);
    }

    public void clickOnExclusiveCollectionTabItem(){
        myActions.action_click(exclusiveCollectionTabItem);
    }


    /*-------------MyCollections Tab Elements------------------*/

    // No-Of-Collection TextView
    @FindBy(xpath = "//span[@class='primary___3k9Ts weight-4___ZQvdQ text-16___-Np8V bold___3nNae text-flat___3AZ-6']")
    private WebElement noOfCollectionTextView;

    // Create New or add to existing Collections View
    @FindBy(xpath = "//p[contains(text(),'Create New or add to existing Collections')]")
    private WebElement createOrAddToNewCollectionText;

    // CreateNewCollection View
    @FindBy(xpath = "//p[contains(text(),'Create New Collection')]")
    private WebElement createNewCollectionButton;


    public String getNoOfCollectionText(){
        return myActions.action_getText(noOfCollectionTextView);
    }

    public String getCreateOrAddNewCollectionsText(){
        return myActions.action_getText(createOrAddToNewCollectionText);
    }

    public void clickOnCreateNewCollectionButton(){
        myActions.action_click(createNewCollectionButton);
    }



    /*-------------ExclusiveCollections Tab Elements------------------*/





    /*----------------Functions-------------------*/

    public String createNewCollection(){
        clickOnCreateNewCollectionButton();
        String collectionName = "TestingCollection : "+ new Random().nextInt(5000);
        new CreateCollectionBottomSheetObjects(driver).
                performAddCollection(collectionName);
        return collectionName;
    }

    public void enterIntoCollectionFromMyCollections(String collectionName){

    }

}