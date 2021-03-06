package utils;

import coreUtils.BuildParameterKeys;
import io.appium.java_client.android.nativekey.*;
import org.openqa.selenium.*;
import pageObjects.NativePopUpProgressBarPO;

public class MyActions extends AndroidBaseClass{

    private String host = System.getProperty(BuildParameterKeys.KEY_HOST);
    private NativePopUpProgressBarPO nativePopUpProgressBarPO
            = new NativePopUpProgressBarPO(getBaseDriver());

    public void action_click(WebElement element){
        sleep(300);
        try{
            element.click();
        }catch (ElementClickInterceptedException e){
            sleep(2000);
            action_click(element);
        }catch (StaleElementReferenceException e){
            sleep(2000);
            action_click(element);
        }
        catch (Exception e){
            System.out.println(e);
        }

        if(nativePopUpProgressBarPO.isProgressBarCrossingThreshold()){
            System.out.println("Progress Bar Is Still Visible");
            // Take the snapshot and Quit the process
            System.exit(1);
        }else {
            // Do Nothing
        }
    }

    public void action_sendKeys(WebElement element, String data){
        sleep(300);
        try{
            element.sendKeys(data);
            if(host.equalsIgnoreCase("bs")){
                if (getBaseDriver().isKeyboardShown()){
                    getBaseDriver().hideKeyboard();
                }
            }
        }catch (StaleElementReferenceException e){
            sleep(2000);
            action_sendKeys(element,data);
        }catch (IllegalStateException e){
            sleep(2000);
            action_sendKeys(element,data);
        }
        catch (Exception e){
            System.out.println(e);
        }
        if(System.getProperty(BuildParameterKeys.KEY_HOST).equalsIgnoreCase("bs")){
            if (getBaseDriver().isKeyboardShown()){
                getBaseDriver().hideKeyboard();
            }
        }
    }

    public String action_getText(WebElement element){
        sleep(300);
        try{
           return element.getText();
        }catch (Exception e){
            System.out.println(e);
            return e.toString();
        }

    }

    public String action_getTagName(WebElement element){
        return element.getTagName();
    }

    public Boolean action_is_selected(WebElement element) {
        sleep(300);
        return element.isSelected();
    }

    public void action_clearText(WebElement element){
        element.clear();
        if(System.getProperty(BuildParameterKeys.KEY_HOST).equalsIgnoreCase("bs")){
            if (getBaseDriver().isKeyboardShown()){
                getBaseDriver().hideKeyboard();
            }
        }
//         if(host.equalsIgnoreCase("bs")){
//             if (getBaseDriver().isKeyboardShown()){
//             getBaseDriver().hideKeyboard();
//             }
//         }
              
    }

    public void clickOnHardKeyBack() throws Exception{
        try{
            getBaseDriver().pressKey(new KeyEvent(AndroidKey.BACK));
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("Pressed Device BackKey");
    }
}
