package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.MyActions;

public class LoginPageObject {
    private final WebDriver driver;
    private final MyActions myActions;
    private ActionBarObjects actionBarObjects;
    //Login icon
    @FindBy(xpath = "//p[text()='Login']")
    private WebElement loginIconButton;
    //Mobile number entry text
    @FindBy(xpath = "//input[@id='newPhone']")
    private WebElement mobileNumberTextView;
    //Skip button
    @FindBy(xpath = "//a[text()='SKIP']")
    private WebElement getSkipLoginButton;
    //continue button
    @FindBy(xpath = "//button[text()='CONTINUE']")
    private WebElement continueButton;
    //otp entry Text box
    @FindBy(xpath = "//input[@id='newOtp']")
    private WebElement otpTextView;
    //otp submit button
    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement otpSubmitButton;
    //Resent otp button
    @FindBy(xpath = "//button[text()='Resend']")
    private WebElement resentOtpButton;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements((driver), this);
        myActions = new MyActions();
    }

    /*--------------Actions-------------------*/
    private void enterMobileNumber(String mobileNumber) {
        myActions.action_sendKeys(mobileNumberTextView, mobileNumber);
    }

    private void clickContinueButton() {
        myActions.action_click(continueButton);
    }

    private void enterOtp(String otp) {
        myActions.action_sendKeys(otpTextView, otp);
    }

    private void clickSubmitButton() {
        myActions.action_click(otpSubmitButton);
    }

    private void clickOnResentOtpButton() {
        myActions.action_click(resentOtpButton);
    }

    public String getLoginLabelText() {
        return myActions.action_getText(loginIconButton);
    }



    /*FUNCTIONS*/

    //1.Normal Authentication Function
    public void performAuthentication(String mobileNumber, String otp) {
        ActionBarObjects actionBarObjects = new ActionBarObjects(driver);
        actionBarObjects.clickOnLoginButton();
        enterMobileNumber(mobileNumber);
        clickContinueButton();
        enterOtp(otp);
        clickSubmitButton();
    }


}
