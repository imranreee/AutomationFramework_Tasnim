package utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MyActions extends WmsBaseClass {

    public void action_click(WebElement element){
        sleep(1000);
        element.click();
    }

    public void action_sendKeys(WebElement element, String data){
        sleep(1000);
        element.sendKeys(data);
    }

    public String action_getText(WebElement element){
        sleep(1000);
        return element.getText();
    }

    public String action_getTagName(WebElement element){
        sleep(1000);
        return element.getTagName();
    }

    public void action_select(WebElement element, String data){
        sleep(1000);
        Select select = new Select(element);
        select.selectByVisibleText(data);
    }

    public void action_enter(WebElement element){
        sleep(1000);
        element.sendKeys(Keys.ENTER);
    }

    public void action_select_index(WebElement element, int index){
        sleep(1000);
        Select select = new Select(element);
        select.selectByIndex(index);
    }
}
