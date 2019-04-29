package com.watson.uitest.window;

import com.watson.uitest.WatsonWindow;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/254:24 PM
 */
@Getter
@Setter
public class BrowserWindow {

    private WebDriver webDriver;

    private String windowHandle;

    Allocator allocator = WatsonWindow.getAllocator();
    public BrowserWindow(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void openWindow(String url) {
        webDriver.get(url);
        webDriver.manage().window().maximize();
        this.windowHandle=webDriver.getWindowHandle();
    }
    private void focus() {
        if(allocator.currentWindow.equals(this))
            return;
        webDriver.switchTo().window(windowHandle);
        allocator.updateWindows();
        allocator.currentWindow=this;
    }

    public String getUrl() {
        focus();
        return webDriver.getCurrentUrl();
    }

    public void quit(){
        webDriver.quit();
    }


    public void close() {
        focus();
        webDriver.close();
        allocator.getWindows().remove(this);
    }
}
