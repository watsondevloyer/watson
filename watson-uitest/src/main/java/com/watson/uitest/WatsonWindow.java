package com.watson.uitest;

import com.watson.uitest.jquery.JqueryManager;
import com.watson.uitest.window.Allocator;
import com.watson.uitest.window.BrowserWindow;
import com.watson.uitest.window.EngineType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/283:57 PM
 */
@Slf4j
public class WatsonWindow {

    private static ThreadLocal<Allocator> allocatorThreadLocal = new ThreadLocal<Allocator>();

    public static Allocator getAllocator() {
        return allocatorThreadLocal.get();
    }


    public static void openNewWindow(EngineType engineType, String url) {
        allocatorThreadLocal.set(new Allocator());
        getAllocator().addAndSetBrowserWindow(engineType, url);

    }

    public static void openNewWindow(String url) {
        allocatorThreadLocal.set(new Allocator());
        getAllocator().addAndSetBrowserWindow(EngineType.WebDriverChrome, url);

    }

    /**
     * 不开新窗口，在当前窗口打开链接
     * @param url
     */
    public static void open(String url){
        getAllocator().currentWindow.open(url);
    }


    public static void openNewRemoteWindow(EngineType engineType, String url) {
        allocatorThreadLocal.set(new Allocator());
        getAllocator().addAndSetRemoteBrowserWindow(engineType, url);

    }
    public static void openNewRemoteWindow( String url) {
        allocatorThreadLocal.set(new Allocator());
        getAllocator().addAndSetRemoteBrowserWindow(EngineType.WebDriverChrome, url);

    }
    public static void closeAllWindowsByUrl(String url) {
        getAllocator().closeAllWindowsByUrl(url);
    }

    public static void closeAllWindow() {
        getAllocator().closeAllWindow();
    }


    public static void runJavaScript(String javascript){
        BrowserWindow currentWindow=getAllocator().currentWindow;
        try{
            JavascriptExecutor executor=(JavascriptExecutor)currentWindow.getWebDriver();
            executor.executeScript(javascript+";return false;");
            log.info(javascript+"执行成功!");
        }catch (Exception e) {
            log.error(javascript+"执行失败!");
        }
    }

    public static void runJavaScript(String javascript,Object...paras){
        BrowserWindow currentWindow=getAllocator().currentWindow;
        try{
            JavascriptExecutor executor=(JavascriptExecutor)currentWindow.getWebDriver();
            executor.executeScript(javascript,paras);
            log.info(javascript+"执行成功!");
        }catch (Exception e) {
            log.error(javascript+"执行失败!");
        }
    }

    public static Object runJqueryScript(String script, Object... args){
        BrowserWindow currentWindow=getAllocator().currentWindow;
        Object o = JqueryManager.getInstance().runJquery(currentWindow.getWebDriver(), script, args);
        return o;
    }

    public static String getUrl(){
        return getAllocator().currentWindow.getUrl();
    }

    public static String getTitle(){
        return getAllocator().currentWindow.getTitle();
    }

    public static void refresh(){
        getAllocator().currentWindow.refresh();
    }


    /**对当前窗口模拟浏览器的前进按钮<br>*/
    public static void goForward(){
        getAllocator().currentWindow.goForward();
    }

    /**对当前窗口模拟浏览器的后退按钮<br>*/
    public static void goBack(){
        getAllocator().currentWindow.goBack();
    }




}
