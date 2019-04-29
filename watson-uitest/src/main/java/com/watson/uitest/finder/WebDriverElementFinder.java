package com.watson.uitest.finder;

import com.watson.ConfigConstValue;
import com.watson.uitest.WatsonWindow;
import com.watson.uitest.struct.element.Element;
import com.watson.uitest.struct.element.locator.Locator;
import com.watson.uitest.window.Allocator;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/286:20 PM
 */
public class WebDriverElementFinder {

    private Element element;

    private Allocator allocator= WatsonWindow.getAllocator();

    public WebDriverElementFinder(Element element) {
        this.element = element;
    }

    public boolean isElementExist() {
        int waitTime = ConfigConstValue.waitTime;
        for (int i=0;i<waitTime*10;i++){
            WebElement webElement = findElementOnly();
            if(webElement!=null){
                element.setWebElement(webElement);
                return true;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    private WebElement findElementOnly(){
        Locator locator=element.getLocator();
        WebElement webElement=findElementById(locator.getLocatorById());
        if(null==webElement)
            webElement=findElementByName(locator.getLocatorByName());
        if(null==webElement)
            webElement=findElementByClass(locator.getLocatorByClass());
        if(null==webElement)
            webElement=findElementByXpath(locator.getLocatorByXpath());
        if(null==webElement)
            webElement=findElementByCss(locator.getLocatorByCSS());
        if(null==webElement)
            webElement=findElementByLinkText(locator.getLocatorByLinkText());
        if(null==webElement)
            webElement=findElementByPartLinkText(locator.getLocatorByPartialLinkText());
        if(null==webElement)
            webElement=findElementByTagName(locator.getLocatorByTagName());
        return webElement;
    }

    private WebElement findElementByTagName(String locatorByTagName) {
        if (StringUtils.isNotBlank(locatorByTagName)){
            return allocator.currentWindow.getWebDriver().findElement(By.tagName(locatorByTagName));
        }
        return null;
    }

    private WebElement findElementByPartLinkText(String locatorByPartialLinkText) {
        if (StringUtils.isNotBlank(locatorByPartialLinkText)){
            return allocator.currentWindow.getWebDriver().findElement(By.partialLinkText(locatorByPartialLinkText));
        }
        return null;
    }

    private WebElement findElementByLinkText(String locatorByLinkText) {
        if (StringUtils.isNotBlank(locatorByLinkText)){
            return allocator.currentWindow.getWebDriver().findElement(By.linkText(locatorByLinkText));
        }
        return null;
    }

    private WebElement findElementByCss(String locatorByCSS) {
        if (StringUtils.isNotBlank(locatorByCSS)){
            return allocator.currentWindow.getWebDriver().findElement(By.cssSelector(locatorByCSS));
        }
        return null;
    }

    private WebElement findElementByXpath(String locatorByXpath) {
        if (StringUtils.isNotBlank(locatorByXpath)){
            return allocator.currentWindow.getWebDriver().findElement(By.xpath(locatorByXpath));
        }
        return null;
    }


    private WebElement findElementByClass(String locatorByClass) {
        if (StringUtils.isNotBlank(locatorByClass)){
            return allocator.currentWindow.getWebDriver().findElement(By.className(locatorByClass));
        }
        return null;
    }

    private WebElement findElementByName(String locatorByName) {
        if (StringUtils.isNotBlank(locatorByName)){
            return allocator.currentWindow.getWebDriver().findElement(By.name(locatorByName));
        }
        return null;
    }

    private WebElement findElementById(String locatorById) {
        if (StringUtils.isNotBlank(locatorById)){
            return allocator.currentWindow.getWebDriver().findElement(By.id(locatorById));
        }
        return null;
    }
}
