package com.watson.uitest.window;

import com.watson.ConfigConstValue;
import com.watson.uitest.WatsonWindow;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/254:24 PM
 */
@Getter
@Setter
@Slf4j
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

    public String getTitle() {
        focus();
        return webDriver.getTitle();
    }

    public void refresh() {
        focus();
        webDriver.navigate().refresh();
    }

    public void goForward() {
        focus();
        webDriver.navigate().forward();
    }

    public void goBack() {
        focus();
        webDriver.navigate().back();
    }

    public void open(String url) {
        focus();
        webDriver.get(url);
    }

    public String dealAlert() {
        focus();
        Alert alert = webDriver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;

    }

    public String dealAlert(int retryTimes) {
        focus();
        for(int i=0;i<retryTimes;i++){

            try {
                Alert alert = webDriver.switchTo().alert();
                String text = alert.getText();
                alert.accept();
                return text;
            } catch (Exception e) {
             log.info("未找到alert窗口，正在进行第{}次尝试",i);
            }
        }
        return null;
    }

    public String dealPrompt(String input, boolean isYes) {
        focus();
        Alert alert = webDriver.switchTo().alert();
        String text = alert.getText();
        alert.sendKeys(input);
        if(isYes){
            alert.accept();
        }else {
            alert.dismiss();
        }
        return text;

    }

    public String dealConfirm(boolean isYes) {
        focus();
        Alert alert = webDriver.switchTo().alert();
        String text = alert.getText();
        if(isYes){
            alert.accept();
        }else {
            alert.dismiss();
        }
        return text;
    }

    public String dealConfirm(boolean isYes, int retryTimes) {
        focus();
        for(int i=0;i<retryTimes;i++){

            try {
                Alert alert = webDriver.switchTo().alert();
                String text = alert.getText();
                if(isYes){
                    alert.accept();
                }else {
                    alert.dismiss();
                }
                return text;
            } catch (Exception e) {
                log.info("未找到alert窗口，正在进行第{}次尝试",i);
            }
        }
        return null;
    }

    public void TakeScreenshot(String fileName) {
        if(null == webDriver) return;
        TakesScreenshot TCS=(TakesScreenshot)webDriver;
        File screenShot=TCS.getScreenshotAs(OutputType.FILE);
        try {
            String localFileName= ConfigConstValue.screenShotDir +fileName;
            FileUtils.copyFile(screenShot, new File(localFileName));
            log.info("截图成功!地址为:"+localFileName);
        } catch (IOException e) {
            log.error("截图失败!");
            e.printStackTrace();
        }
    }
}
