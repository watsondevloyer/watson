package com.watson.uitest.struct.element;

import com.watson.ConfigConstValue;
import com.watson.uitest.WatsonWindow;
import com.watson.uitest.finder.WebDriverElementFinder;
import com.watson.uitest.struct.element.locator.Locator;
import com.watson.uitest.window.BrowserWindow;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.Set;

import static com.watson.uitest.WatsonWindow.getAllocator;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/262:28 PM
 */
@Setter
@Getter
@Slf4j
public class Element {

    protected WebElement webElement;
    private String comment;
    private Locator locator = new Locator();

    public boolean isElementExist() {
        WebDriverElementFinder webDriverElementFinder = new WebDriverElementFinder(this);
        return webDriverElementFinder.isElementExist();
    }

    public void click() {

        StringBuilder message = new StringBuilder(comment).append(":");
        if (isElementExist()) {
            webElement.click();
            message.append("click 成功！");
        } else {
            message.append("click 失败！找不到元素！");
        }
        log.info(message.toString());

    }

    public void sendKeys(String value) {
        StringBuilder message = new StringBuilder(comment).append(":");
        if (isElementExist()) {
            webElement.sendKeys(value);
            message.append("sendKeys 成功！");
        } else {
            message.append("sendKeys 失败！找不到元素！");
        }
        log.info(message.toString());
    }

    /**
     * 清空输入框里面的内容
     * */
    public void clear(){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        if(isElementExist()){
            message.append(this.comment+":");
            webElement.clear();
            message.append("输入框的值清空成功!");
            log.info(message.toString());
        }else{
            message.append(this.comment+":");
            message.append("输入框的值清空失败!找不到元素");
            log.error(message.toString());
        }
    }



    /**
     * 获取文本框中的值
     * */
    public String getValue(){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        String value = null;
        if(isElementExist()){
            message.append(this.comment+":");
            value = webElement.getAttribute("value");
            message.append("输入框的值获取成功!");
            log.info(message.toString());
        }else{
            message.append(this.comment+":");
            message.append("输入框的值清空失败!找不到元素");
            log.error(message.toString());
        }
        return value;
    }

    public void clickAndWaitForNewWindow() {
        StringBuilder message = new StringBuilder();
        BrowserWindow currentWindow = getAllocator().currentWindow;
        if (isElementExist()) {
            message.append(comment + ":");

            Set<String> handlesPrior = currentWindow.getWebDriver().getWindowHandles();
            webElement.click();
            try {
                Set<String> handlesThen = currentWindow.getWebDriver().getWindowHandles();
                int waitCount = 0;
                while (isHandlesEqual(handlesPrior, handlesThen) && waitCount < ConfigConstValue.waitTime * 10) {
                    handlesThen = currentWindow.getWebDriver().getWindowHandles();
                    Thread.sleep(100);
                    waitCount++;
                }
                getAllocator().updateWindows();
            } catch (Exception e) {
                //do nothing
                /*当点击按钮弹出的是alert等消息框的时候,js会被挂起，这个时候getUrl会失败*/

            }
            message.append("click鼠标左键单击成功!");
            log.info(message.toString());
        } else {
            message.append(comment + ":");
            message.append("click鼠标左键单击失败!找不到元素！");
            log.error(message.toString());
        }
    }

    private boolean isHandlesEqual(Set<String> left, Set<String> right) {
        if (null == left || null == right)
            return false;
        if (left.size() != right.size())
            return false;
        Iterator<String> leftValue = left.iterator();
        Iterator<String> rightValue = right.iterator();
        while (leftValue.hasNext()) {
            if (!(leftValue.next().equalsIgnoreCase(rightValue.next()))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 如果发生跳转，等待second s时间，如果页面没有加载完成，继续走，不等待
     */
    public void clickAndWaitForSecond(int second) {
        this.click();
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 鼠标悬浮在元素上
     */
    public void mouseOver() {
        StringBuilder message = new StringBuilder();
        BrowserWindow currentWindow = getAllocator().currentWindow;
        if (isElementExist()) {
            message.append(this.comment + ":");

            Actions action = new Actions((WebDriver) currentWindow.getWebDriver());
            action.moveToElement(webElement).perform();

            message.append("悬停成功!");
            log.info(message.toString());
        } else {
            message.append(this.comment + ":");
            message.append("悬停失败！找不到元素！");
            log.error(message.toString());
        }
    }

    /**
     * 获得这个元素的文本，在textField和richTextField里面对这个方法进行了重写<br>
     * 这个获得了标签<biaoqian>text</biaoqian>之间的text
     *
     * @return 两标签之间的文本值
     */
    public String getText() {
        StringBuilder message = new StringBuilder();
        String text = null;
        if (isElementExist()) {
            message.append(this.comment + ":");
            text = webElement.getText();
            message.append("文本获得成功，为:" + text);
            log.info(message.toString());
        } else {
            message.append(this.comment + ":");
            message.append("文本获得失败，元素找不到!");
            log.error(message.toString());
        }
        return text;
    }


    /**
     * 让当前组件获得焦点
     */
    public void focus() {
        StringBuilder message = new StringBuilder();
        if (isElementExist()) {
            message.append(this.comment + ":");
            webElement.sendKeys("");
            message.append("焦点获得成功!");
            log.info(message.toString());
        } else {
            message.append(this.comment + ":");
            message.append("焦点获得失败，找不到元素!");
            log.error(message.toString());
        }
    }

    /**
     * 模拟元素鼠标左键双击操作
     */
    public void doubleClick() {
        StringBuilder message = new StringBuilder();
        BrowserWindow currentWindow = getAllocator().currentWindow;
        if (isElementExist()) {
            message.append(this.comment + ":");
            Actions action = new Actions(currentWindow.getWebDriver());
            action.doubleClick(webElement).perform();
            message.append("双击操作成功!");
            log.info(message.toString());
        } else {
            message.append(this.comment + ":");
            message.append("双击操作失败！找不到元素!");
            log.error(message.toString());
        }
    }

    /**
     * 模拟在这个元素上按下一个键盘按钮，即以此元素为焦点
     */
    public void keyPress(Keys keyValue) {
        StringBuilder message = new StringBuilder();
        BrowserWindow currentWindow = getAllocator().currentWindow;
        if (isElementExist()) {
            Actions action = new Actions(currentWindow.getWebDriver());
            action.keyDown(webElement, keyValue).perform();
            message.append("按钮" + keyValue + "已按下!");
            log.info(message.toString());
        } else {
            message.append(this.comment + ":");
            message.append("按钮按下失败，找不到元素！");
            log.error(message.toString());
        }
    }

    /**
     * 模拟松开键盘的操作，以此元素为焦点
     */
    public void keyUp(Keys keyValue) {
        StringBuilder message = new StringBuilder();
        BrowserWindow currentWindow = getAllocator().currentWindow;
        if (isElementExist()) {
            message.append(this.comment + ":");
            Actions action = new Actions(currentWindow.getWebDriver());
            action.keyUp(webElement, keyValue).perform();
            message.append("按钮" + keyValue + "已松开!");
            log.info(message.toString());
        } else {
            message.append(this.comment + ":");
            message.append("按钮松开失败，找不到元素！");
            log.error(message.toString());
        }
    }

    /**
     * 模拟在此元素上面按下鼠标左键操作
     */
    public void leftMouseDown() {
        StringBuilder message = new StringBuilder();
        BrowserWindow currentWindow = getAllocator().currentWindow;
        if (isElementExist()) {
            message.append(this.comment + ":");
            Actions action = new Actions(currentWindow.getWebDriver());
            action.clickAndHold(webElement).perform();
            message.append("鼠标左键在" + this.comment + "已按下!");
            log.info(message.toString());
        } else {
            message.append(this.comment + ":");
            message.append("鼠标左键按下失败!找不到元素!");
            log.error(message.toString());
        }
    }

    /**
     * 模拟在这个元素上面松开鼠标左键
     */
    public void leftMouseUp() {
        StringBuilder message = new StringBuilder();
        BrowserWindow currentWindow = getAllocator().currentWindow;
        if (isElementExist()) {
            message.append(this.comment + ":");
            Actions action = new Actions(currentWindow.getWebDriver());
            action.release(webElement).perform();
            message.append("鼠标左键在" + this.comment + "已松开!");
            log.info(message.toString());
        } else {
            message.append(this.comment + ":");
            message.append("鼠标左键松开失败!找不到元素!");
            log.error(message.toString());
        }
    }


    /**
     * 模拟在该元素上单击鼠标右键
     */
    public void rightMouseClick() {
        StringBuilder message = new StringBuilder();
        BrowserWindow currentWindow = getAllocator().currentWindow;
        if (isElementExist()) {
            message.append(this.comment + ":");
            Actions action = new Actions(currentWindow.getWebDriver());
            action.contextClick(webElement).perform();
            message.append("右键按下成功!");
            log.info(message.toString());
        } else {
            message.append(this.comment + ":");
            message.append("右键按下失败!找不到元素！");
            log.error(message.toString());
        }
    }


    /**
     * 判断这个元素是否可编辑<br>
     *
     * @return 可编辑 ：true ；不可编辑  false
     */
    public boolean isEditable() {
        StringBuilder message = new StringBuilder();
        boolean editable = false;
        if (isElementExist()) {
            message.append(this.comment + ":");
            editable = webElement.isEnabled();
            message.append("可编辑属性获取成功!");
            log.info(message.toString());
        } else {
            message.append(this.comment + ":");
            message.append("可编辑属性获取失败!找不到元素!");
            log.error(message.toString());
        }
        return editable;
    }

    /**
     * 判断这个元素是否可见<br>
     *
     * @return 可见 ：true ；不可见  false
     */
    public boolean isVisable() {
        StringBuilder message = new StringBuilder();
        boolean visible = false;
        message.append(this.comment + ":");
        if (isElementExist()) {
            visible = webElement.isDisplayed();
            message.append("可见属性获取成功!");
            log.info(message.toString());
        } else {
            message.append(this.comment + ":");
            message.append("可见属性获取失败!找不到元素!");
            log.error(message.toString());
        }
        return visible;
    }


    /**
     * 获取一个元素的一个属性<br>
     *
     * @param attributeName 属性的名字，例如href
     * @return 获取的属性信息，字符窜
     */
    public String getAttribute(String attributeName) {
        StringBuilder message = new StringBuilder();
        String attribute = null;
        if (isElementExist()) {
            message.append(this.comment + ":");

            try {
                attribute = webElement.getAttribute(attributeName);
                message.append("属性获取成功!");
                log.info(message.toString());
            } catch (Exception e) {
                message.append("属性获取失败，此元素没有" + attributeName + "属性!");
                log.error(message.toString());
            }

        } else {
            message.append(this.comment + ":");
            message.append("属性获取失败,找不到元素!");
            log.error(message.toString());
        }
        return attribute;
    }


    /**
     * 获得元素css属性值<br>
     *
     * @param cssName css属性的名字
     * @return css属性的值
     */
    public String getCSSValue(String cssName) {
        StringBuilder message = new StringBuilder();
        if (isElementExist()) {
            message.append(this.comment + ":");
            return webElement.getCssValue(cssName);
        }
        return null;
    }

    /**
     * 获取此元素的大小
     */
    public Dimension getSize() {
        StringBuilder message = new StringBuilder();
        if (isElementExist()) {
            message.append(this.comment + ":");
            return webElement.getSize();
        }
        return null;
    }

    /**
     * 获取此元素的位置,左上角的位置
     */
    public Point getLocation() {
        StringBuilder message = new StringBuilder(this.comment + ":");
        if (isElementExist()) {
            message.append(this.comment + ":");
            return webElement.getLocation();
        }
        return null;
    }

    /**
     * 高亮显示此Element
     */
    public void highLight() {
        if (isElementExist()) {
            WatsonWindow.runJavaScript("arguments[0].style.border = \"5px solid yellow\"", this);
        }
    }

    /**
     * 提交Element里面输入的信息
     */
    public void submit() {
        StringBuilder message = new StringBuilder(this.comment + ":");
        if (isElementExist()) {
            message.append(this.comment + ":");
            webElement.submit();
            log.info("submit操作执行成功");
        }
    }


    /**检验这个CheckBox多选框是否被选中<br>
     * @return true 被选中<br>
     * 		   false 没有被选中*/
    public boolean isChecked(){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        boolean checked=false;
        if(isElementExist()){
            message.append(this.comment+":");

            checked=webElement.isSelected();

        }else{
            message.append(this.comment+":");
            message.append("检验选中失败！找不到元素！");
            log.error(message.toString());
        }
        return checked;
    }
    /**根据value的值来设定多选框的状态<br>
     * @param value true 设置为选中   <br>false  取消选中
     * */
    public void select(boolean value){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        if(isElementExist()){
            message.append(this.comment+":");

            if(webElement.isSelected()!=value){
                webElement.click();

            }
        }else{
            message.append(this.comment+":");
            message.append("设定值失败！找不到元素！");
            log.error(message.toString());
        }
    }




}


