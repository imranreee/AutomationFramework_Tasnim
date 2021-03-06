package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.MyActions;
import utils.WebAppBaseClass;

public class CreateCollectionBottomSheetObjects extends WebAppBaseClass {

    private final AndroidDriver<WebElement> driver;
    private final MyActions myActions;

    public CreateCollectionBottomSheetObjects(AndroidDriver<WebElement> androidDriver) throws Exception {
        this.driver = androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver),this);
        myActions = new MyActions();
    }




    // CreateNewCollection Text
    @FindBy(xpath = "//div[@class='gridItems___2yFJ9 items___vci1r']")
    private WebElement createNewCollectionText;

    // EnterCollectionName EditText
    @FindBy(xpath = "//input[@class='input___tfjvP']")
    private WebElement enterYourCollectionNameEditText;

    // SaveCollection Button
    @FindBy(xpath = "//button[@class='rounded___vgOiV primary___OLr69 button___3btga ripple___1U_Uk contain___ux0BW save___tLdNx']")
    private WebElement saveCollectionButton;

    // Cancel Button
    @FindBy(xpath = "//div[@class='close___3yvOX']")
    private WebElement cancelCollectionButton;

    // Field Required Error Message
    @FindBy(xpath = "//p[@class='error___2Dq0y weight-4___ZQvdQ text-14___yZ_9T text-flat___3AZ-6 inputError inputError___1KOfy']")
    private WebElement fieldRequiredErrorMessage;


    private void CreateNewCollection(){
         myActions.action_click(createNewCollectionText);
    }

    private void enterCollectionName(String collectionName){
        myActions.action_sendKeys(enterYourCollectionNameEditText,collectionName);
    }

    private void clickOnSaveCollectionButton(){
        myActions.action_click(saveCollectionButton);
    }

    private void clickOnCancelCollectionButton(){
        myActions.action_click(cancelCollectionButton);
    }

    private void getTheErrorMessage(){
        myActions.action_getText(fieldRequiredErrorMessage);
    }


    /*-----------Functions---------------*/

    public void performAddCollection(String collectionName){
        CreateNewCollection();
        enterCollectionName(collectionName);
        clickOnSaveCollectionButton();
    }

    public void performCancelTheAddCollection(String collectionName){
        enterCollectionName(collectionName);
        clickOnCancelCollectionButton();
    }
}
