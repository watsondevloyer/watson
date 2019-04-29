package com.watson.uitest;

import com.watson.uitest.window.EngineType;
import org.junit.Test;


/**
 * BrowserWindow Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 25, 2019</pre>
 */

public class WatsonWindowTest {

    private  BaiduPage baiduPage=new BaiduPage();

    @Test
    public void testOpenNewWindow() {
        WatsonWindow.openNewWindow(EngineType.WebDriverSafari,"http:www.baidu.com");
        baiduPage.search("百度输入框测试");
        WatsonWindow.closeAllWindow();
    }


    @Test
    public void testRunJqueryScript(){
        WatsonWindow.openNewWindow(EngineType.WebDriverSafari,"http:www.baidu.com");
        WatsonWindow.runJqueryScript("jQuery('#kw').val('jqueryTest');");
    }




}
