package com.watson.uitest;

import com.watson.uitest.jquery.JqueryManager;
import com.watson.uitest.window.Allocator;
import com.watson.uitest.window.BrowserWindow;
import com.watson.uitest.window.EngineType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


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

    /**
     * 获得当前窗口的驱动引擎
     */
    public static WebDriver getDriver() {
        return getAllocator().currentWindow.getWebDriver();
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
     *
     * @param url
     */
    public static void open(String url) {
        getAllocator().currentWindow.open(url);
    }


    public static void openNewRemoteWindow(EngineType engineType, String url) {
        allocatorThreadLocal.set(new Allocator());
        getAllocator().addAndSetRemoteBrowserWindow(engineType, url);

    }

    public static void openNewRemoteWindow(String url) {
        allocatorThreadLocal.set(new Allocator());
        getAllocator().addAndSetRemoteBrowserWindow(EngineType.WebDriverChrome, url);

    }

    public static void closeAllWindowsByUrl(String url) {
        getAllocator().closeAllWindowsByUrl(url);
    }

    public static void closeAllWindow() {
        getAllocator().closeAllWindow();
    }


    public static void runJavaScript(String javascript) {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        try {
            JavascriptExecutor executor = (JavascriptExecutor) currentWindow.getWebDriver();
            executor.executeScript(javascript + ";return false;");
            log.info(javascript + "执行成功!");
        } catch (Exception e) {
            log.error(javascript + "执行失败!");
        }
    }

    public static void runJavaScript(String javascript, Object... paras) {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        try {
            JavascriptExecutor executor = (JavascriptExecutor) currentWindow.getWebDriver();
            executor.executeScript(javascript, paras);
            log.info(javascript + "执行成功!");
        } catch (Exception e) {
            log.error(javascript + "执行失败!");
        }
    }

    public static Object runJqueryScript(String script, Object... args) {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        Object o = JqueryManager.getInstance().runJquery(currentWindow.getWebDriver(), script, args);
        return o;
    }

    public static String getUrl() {
        return getAllocator().currentWindow.getUrl();
    }

    public static String getTitle() {
        return getAllocator().currentWindow.getTitle();
    }

    public static void refresh() {
        getAllocator().currentWindow.refresh();
    }


    /**
     * 对当前窗口模拟浏览器的前进按钮<br>
     */
    public static void goForward() {
        getAllocator().currentWindow.goForward();
    }

    /**
     * 对当前窗口模拟浏览器的后退按钮<br>
     */
    public static void goBack() {
        getAllocator().currentWindow.goBack();
    }

    /**
     * 按下Shift键,在松开之前一直处于按下状态
     */
    public static void shiftKeyDown() {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        Actions action = new Actions(currentWindow.getWebDriver());
        action.build();
        action.keyDown(Keys.SHIFT).perform();
    }

    /**
     * 按下Ctrl键,在松开之前一直处于按下状态
     */
    public static void ctrlKeyDown() {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        Actions action = new Actions(currentWindow.getWebDriver());
        action.build();
        action.sendKeys(Keys.CONTROL).perform();
    }

    /**
     * 按下Meta键,在松开之前一直处于按下状态
     */
    public static void metaKeyDown() {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        Actions action = new Actions(currentWindow.getWebDriver());
        action.build();
        action.sendKeys(Keys.META).perform();
    }

    /**
     * 按下Alt键,在松开之前一直处于按下状态
     */
    public static void altKeyDown() {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        Actions action = new Actions(currentWindow.getWebDriver());
        action.build();
        action.keyDown(Keys.ALT).perform();
        ;
    }

    /**
     * 松开Shift键,若之前处于松开状态，则没有影响
     */
    public static void shiftKeyUp() {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        Actions action = new Actions(currentWindow.getWebDriver());
        action.build();
        action.keyUp(Keys.SHIFT).perform();
    }

    /**
     * 松开Ctrl键,若之前处于松开状态，则没有影响
     */
    public static void ctrlKeyUp() {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        Actions action = new Actions(currentWindow.getWebDriver());
        action.build();
        action.keyUp(Keys.CONTROL).perform();
    }

    /**
     * 松开Meta键,若之前处于松开状态，则没有影响
     */
    public static void metaKeyUp() {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        Actions action = new Actions(currentWindow.getWebDriver());
        action.build();
        action.keyUp(Keys.META).perform();
    }

    /**
     * 松开Alt键,若之前处于松开状态，则没有影响
     */
    public static void altKeyUp() {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        Actions action = new Actions(currentWindow.getWebDriver());
        action.build();
        action.keyUp(Keys.ALT).perform();
    }

    /**
     * 按下某个modifer key键
     */
    public static void KeyDown(Keys key) {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        Actions action = new Actions(currentWindow.getWebDriver());
        action.build();
        action.sendKeys(key).perform();
    }

    /**
     * 松开某个modifier key键
     */
    public static void KeyUp(Keys key) {
        BrowserWindow currentWindow = getAllocator().currentWindow;
        Actions action = new Actions(currentWindow.getWebDriver());
        action.build();
        action.sendKeys(key).perform();
    }

    /**
     * 滚动条滚动到顶端
     */
    public static void scrollToTop() {
        runJavaScript("window.scrollTo(0,0)");
        log.info("滚动至页面顶端成功!");
    }

    /**
     * 滚动条滚动到底端
     */
    public static void scrollToBottom() {
        runJavaScript("window.scrollTo(0,document.body.scrollHeight)");
        log.info("滚动至页面底部成功");
    }

    /**
     * 滚动条滚动到特定位置
     */
    public static void scrollTo(int xLocation, int yLocation) {
        runJavaScript("window.scrollTo(" + xLocation + "," + yLocation + ");");
    }

    /**
     * 处理当前页面Alert弹出框，并返回Alert窗口的内容信息<br>
     *
     * @return Alert的信息
     */
    public static String dealAlert() {
        return getAllocator().currentWindow.dealAlert();
    }

    /**
     * 处理当前页面Alert弹出框，并返回Alert窗口的内容信息<br>
     *
     * @return Alert的信息
     */
    public static String dealAlert(int retryTimes) {
        return getAllocator().currentWindow.dealAlert(retryTimes);
    }

    /**
     * 处理当前页面的prompt窗口<br>
     *
     * @param input prompt 窗口的输入信息
     * @return prompt窗口上的内容
     */
    public static String dealPrompt(String input, boolean isYes) {
        return getAllocator().currentWindow.dealPrompt(input, isYes);
    }

    /**
     * 处理当前页面的confirm窗口<br>
     *
     * @param isYes true 点击确认
     *              false 点击取消
     * @return confirm窗口上的内容
     */
    public static String dealConfirm(boolean isYes) {
        return getAllocator().currentWindow.dealConfirm(isYes);
    }

    /**
     * 处理当前页面的confirm窗口<br>
     *
     * @param isYes true 点击确认
     *              false 点击取消
     * @return confirm窗口上的内容
     */
    public static String dealConfirm(boolean isYes, int retryTimes) {
        return getAllocator().currentWindow.dealConfirm(isYes, retryTimes);
    }

    /**
     * 截图，是当前获得焦点页面的图
     */
    public static void takeScreenshot(String fileName) {
        getAllocator().currentWindow.TakeScreenshot(fileName + ".png");
    }

    /**
     * 截图，是当前获得焦点页面的图，图的名字为当前时间
     */
    public static void takeScreenshot() {
        getAllocator().currentWindow.TakeScreenshot(System.currentTimeMillis() + ".png");
    }


}
