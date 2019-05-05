package com.watson.uitest.struct;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

import static com.watson.uitest.WatsonWindow.getAllocator;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/309:49 PM
 */
@Slf4j
public class Frame {


    public static void switchFrame(String idOrName) {

        getAllocator().currentWindow.getWebDriver().switchTo().frame(idOrName);

    }

    public static void switchFrame(int index) {

        getAllocator().currentWindow.getWebDriver().switchTo().frame(index);

    }

    public static void switchFrame(WebElement element) {

        getAllocator().currentWindow.getWebDriver().switchTo().frame(element);

    }


    /**
     * 选择最上层的Frame
     */
    public void switchToTop() {


        getAllocator().currentWindow.getWebDriver().switchTo().defaultContent();
        log.info("switchTo到顶级Frame成功!");

    }
}
