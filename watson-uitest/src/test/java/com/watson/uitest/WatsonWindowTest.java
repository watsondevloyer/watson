package com.watson.uitest;

import com.watson.uitest.window.EngineType;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;


/**
 * BrowserWindow Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 25, 2019</pre>
 */
@Slf4j
public class WatsonWindowTest {

    private  BaiduPage baiduPage=new BaiduPage();

    @Test
    public void testOpenNewWindow() {
        WatsonWindow.openNewWindow(EngineType.WebDriverChrome,"http:www.baidu.com");
        baiduPage.search("百度输入框测试");
        log.info("{}",Thread.currentThread().getId());
        WatsonWindow.closeAllWindow();
    }


    @Test
    public void testRunJqueryScript(){
        log.info("{}",Thread.currentThread().getId());
        WatsonWindow.openNewWindow(EngineType.WebDriverChrome,"http:www.baidu.com");
        WatsonWindow.runJqueryScript("jQuery('#kw').val('jqueryTest');");
    }




}
