package utils;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class MyActions extends RedXWebBaseClass {

    public void action_click(WebElement element) {
        sleep(1000);
        try{
            element.click();
        }catch (ElementClickInterceptedException e){
            sleep(2000);
            action_click(element);
        }catch (StaleElementReferenceException e){
            sleep(2000);
            action_click(element);
        }
    }

    public void action_sendKeys(WebElement element, String data) {
        sleep(1000);
        element.sendKeys(data);
    }

    public String action_getText(WebElement element) {
        sleep(1000);
        return element.getText();
    }

    public String action_getTagName(WebElement element) {
        sleep(1000);
        return element.getTagName();
    }
}
