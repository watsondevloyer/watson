package com.watson.uitest.window;

import com.watson.ConfigConstValue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.DesiredCapabilities.*;

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

    public static WebDriver createRemoteWebDriverEngine(EngineType enginetype){
        switch (enginetype) {
            case WebDriverIE:
                return createRemoteWebDriverIEEngine();
            case WebDriverFirefox:
                return createRemoteWebDriverFireFoxEngine();
            case WebDriverChrome:
                return createRemoteWebDriverChromeEngine();
            case WebDriverSafari:
                return createRemoteWebDriverSafariEngine();
            case HtmlUnit:
                return createRemoteWebDriverHtmlUnitEngine();
            default:
                return null;
        }
    }

    private static WebDriver createRemoteWebDriverHtmlUnitEngine() {
        WebDriver driver=null;
        try {
            driver= new RemoteWebDriver(new URL(ConfigConstValue.seleniumServerStandaloneHubUrl), htmlUnit());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static WebDriver createRemoteWebDriverSafariEngine() {
        WebDriver driver=null;
        try {
            driver= new RemoteWebDriver(new URL(ConfigConstValue.seleniumServerStandaloneHubUrl), safari());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static WebDriver createRemoteWebDriverChromeEngine() {
        WebDriver driver=null;
        try {
            driver= new RemoteWebDriver(new URL(ConfigConstValue.seleniumServerStandaloneHubUrl), chrome());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static WebDriver createRemoteWebDriverFireFoxEngine() {
        WebDriver driver=null;
        try {
            driver= new RemoteWebDriver(new URL(ConfigConstValue.seleniumServerStandaloneHubUrl), firefox());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static WebDriver createRemoteWebDriverIEEngine() {
         WebDriver driver=null;
        try {
            driver= new RemoteWebDriver(new URL(ConfigConstValue.seleniumServerStandaloneHubUrl), internetExplorer());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }



    private static WebDriver createWebDriverHtmlUnitEngine() {
        return new HtmlUnitDriver();
    }

    private static WebDriver createWebDriverSafariEngine() {
        return new SafariDriver();
    }

    private static WebDriver createWebDriverChromeEngine() {
        System.setProperty("webdriver.chrome.driver",ConfigConstValue.chromeDriverPath);
        return new ChromeDriver();
    }

    private static WebDriver createWebDriverFireFoxEngine() {
        return new FirefoxDriver();
    }

    private static WebDriver createWebDriverIEEngine() { return new InternetExplorerDriver();

    }



}
