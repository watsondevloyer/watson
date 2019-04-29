package com.watson.uitest.window;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/265:31 PM
 */
public class WatsonDriver {


    /**根据类型进行添加WebDriver底层驱动引擎
     * @param enginetype 浏览器类型
     * @return 新建的WebDriver驱动引擎*/
    public static WebDriver createWebDriverEngine(EngineType enginetype){
        switch (enginetype) {
            case WebDriverIE:
                return createWebDriverIEEngine();
            case WebDriverFirefox:
                return createWebDriverFireFoxEngine();
            case WebDriverChrome:
                return createWebDriverChromeEngine();
            case WebDriverSafari:
                return createWebDriverSafariEngine();
            case HtmlUnit:
                return createWebDriverHtmlUnitEngine();
            default:
                return null;
        }
    }

    private static WebDriver createWebDriverHtmlUnitEngine() {
        return new HtmlUnitDriver();
    }

    private static WebDriver createWebDriverSafariEngine() {
        return new SafariDriver();
    }

    private static WebDriver createWebDriverChromeEngine() {
        return new ChromeDriver();
    }

    private static WebDriver createWebDriverFireFoxEngine() {
        return new FirefoxDriver();
    }

    private static WebDriver createWebDriverIEEngine() { return new InternetExplorerDriver();
    }



}
