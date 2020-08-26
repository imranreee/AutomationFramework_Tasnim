
package pageObejcts;


import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.StoreWapActions;
import java.util.Random;


import static utils.StoreWapBaseClass.getBaseDriver;



public class CreateOrderPageObjects {



    private AndroidDriver<WebElement> androidDriver;
    private StoreWapActions storeWapActions;

    private Random random;


    @FindBy(xpath = "//div[@class='css-1dbjc4n r-13awgt0 r-1d5kdc7']//div[@class='css-1dbjc4n r-1p0dtai r-1d2f490 r-12vffkv r-u8s1d r-zchlnj r-ipm5af'][2]//div[contains(@data-testid,'navback')]")
    private WebElement backButton;

    @FindBy(xpath= "//div[@class='css-1dbjc4n r-13awgt0 r-1d5kdc7']//div[@class='css-1dbjc4n r-1p0dtai r-1d2f490 r-12vffkv r-u8s1d r-zchlnj r-ipm5af'][2]//div[contains(@data-testid,'headerTitle')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@class='css-1dbjc4n r-13awgt0 r-1d5kdc7']//div[@class='css-1dbjc4n r-1p0dtai r-1d2f490 r-12vffkv r-u8s1d r-zchlnj r-ipm5af'][2]//div[@class='css-1dbjc4n r-1awozwy r-13awgt0 r-18u37iz r-1wtj0ep']/div[2]")
    private WebElement sideNavButton;

    @FindBy(xpath = "//div[text()='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//div[text()='+ Add More']")
    private WebElement addMoreButton;

    @FindBy(xpath = "//span[@class='css-901oao css-16my406 r-jwli3a r-1fap712 r-ubezar r-vw2c0b']")
    private WebElement totalAmountText;

    @FindBy(xpath = "//div[@class='css-901oao r-jwli3a r-pz5f6p r-1b43r93'][2]")
    private WebElement totalNumberOFItems;

    public CreateOrderPageObjects(AndroidDriver<WebElement> androidDriver){
        this.androidDriver = androidDriver;
        PageFactory.initElements((androidDriver), this);
        storeWapActions = new StoreWapActions();
        random = new Random();
    }


    /*-----------------------------------------------Actions----------------------------------------------------*/


    public void clickBackButton(){ storeWapActions.action_click(backButton);}

    public void clickSideNavButton(){ storeWapActions.action_click(sideNavButton);}

    public void clickNextButton(){ storeWapActions.action_click(nextButton);}

    public void clickAddMoreButton(){ storeWapActions.action_click(addMoreButton);}


    /*----------------------------------------------Functions----------------------------------------------------*/


    public String getPageTitleText(){
        String pageTitleText = storeWapActions.action_getText(pageTitle);
        return pageTitleText;
    }


    public void enterValidRandomProductAmount(int productIndex){
        String baseXpath = "//div[@class='css-1dbjc4n'][" + productIndex + "]//input[contains(@placeholder,'Price')]";
        WebElement amountElement = androidDriver.findElement(By.xpath(baseXpath));
        String productAmount = String.valueOf(random.nextInt(2000)+1);
        storeWapActions.action_sendKeys(amountElement,productAmount);
    }

    public void editProductNameWithValidName(int productIndex){
        String baseXpath = "//div[text()='PRODUCT NAME']//parent::div//parent::div//parent::div/div[2]/div/div/div["+productIndex+"]/div[1]//input";
        WebElement productName = androidDriver.findElement(By.xpath(baseXpath));
        String newName = "name " + random.nextInt(100) + " " + RandomStringUtils.randomAlphabetic(5) ;
        new Actions(androidDriver).click(productName).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys(newName).perform();
    }

    public void editProductQuantityWithValidQuantity(int productIndex){
        String baseXpath = "//div[text()='PRODUCT NAME']//parent::div//parent::div//parent::div/div[2]/div/div/div[" + productIndex + "]/div/div[2]/div/div/input";
        WebElement productQuantity = androidDriver.findElement(By.xpath(baseXpath));
        String newQty = String.valueOf(random.nextInt(10)+1);
        new Actions(androidDriver).click(productQuantity).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys(newQty).perform();
    }

    public void editProductAmountWithValidAmount(int productIndex){
        String baseXpath = "//div[@class='css-1dbjc4n'][" + productIndex + "]//input[contains(@placeholder,'Price')]";
        WebElement productAmount = androidDriver.findElement(By.xpath(baseXpath));
        String newAmount = String.valueOf(random.nextInt(2000)+1);
        new Actions(androidDriver).click(productAmount).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys(newAmount).perform();
    }


    public void editProductNameWithInvalidName(int productIndex){
        String baseXpath = "//div[text()='PRODUCT NAME']//parent::div//parent::div//parent::div/div[2]/div/div/div["+productIndex+"]/div[1]//input";
        WebElement productName = androidDriver.findElement(By.xpath(baseXpath));
        new Actions(androidDriver).click(productName).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("  ").perform();
    }

    public void editProductQuantityWithInvalidQuantity(int productIndex){
        String baseXpath = "//div[text()='PRODUCT NAME']//parent::div//parent::div//parent::div/div[2]/div/div/div[" + productIndex + "]/div/div[2]/div/div/input";
        WebElement productQuantity = androidDriver.findElement(By.xpath(baseXpath));
        new Actions(androidDriver).click(productQuantity).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("0").perform();
    }

    public void editProductWithInvalidAmount(int productIndex){
        String baseXpath = "//div[@class='css-1dbjc4n'][" + productIndex +"]//input[contains(@placeholder,'Price')]";
        WebElement productAmount = androidDriver.findElement(By.xpath(baseXpath));
        new Actions(androidDriver).click(productAmount).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("0").perform();
    }


    public void deleteProduct(int productIndex){
        String baseXpath = "//div[@class='css-1dbjc4n'][" + productIndex + "]//div[contains(@role,'button')]";
        WebElement deleteButton = androidDriver.findElement(By.xpath(baseXpath));
        storeWapActions.action_click(deleteButton);
    }

    public String getTotalAmount(){
        String totalAmount = storeWapActions.action_getText(totalAmountText);
        return totalAmount;
    }

    public String getNumberOfProducts(){
        String numberOfProducts = storeWapActions.action_getText(totalNumberOFItems);
        return numberOfProducts;
    }

    public String getErrorMessage(){
        try {
            WebDriverWait wait = new WebDriverWait(getBaseDriver(), 30);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='css-1dbjc4n r-qur06v r-18u37iz r-1wtj0ep r-1e081e0 r-1f1sjgu']/div[1]"))).getText();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }



}
