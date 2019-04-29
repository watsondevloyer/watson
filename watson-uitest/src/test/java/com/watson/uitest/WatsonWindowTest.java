package com.watson.uitest;

import com.watson.uitest.window.EngineType;
import lombok.Setter;
import org.junit.Test;


/**
 * BrowserWindow Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 25, 2019</pre>
 */
@Setter
public class WatsonWindowTest {



    private  BaiduPage baiduPage=new BaiduPage();

    @Test
    public void testOpenNewWindow() throws Exception {
        WatsonWindow.openNewWindow(EngineType.WebDriverSafari,"http:www.baidu.com");
        baiduPage.search("百度输入框测试");
        WatsonWindow.closeAllWindow();
    }


}
