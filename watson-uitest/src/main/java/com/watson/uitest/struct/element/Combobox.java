package com.watson.uitest.struct.element;

import com.watson.uitest.window.BrowserWindow;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.watson.uitest.WatsonWindow.getAllocator;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/309:04 PM
 */
@Slf4j
@Getter
@Setter
public class Combobox extends Element {


    private Select select;

    private String comment;
    /**检查combobox元素是否存在*/
    @Override
    public boolean isElementExist(){
        if(super.isElementExist()){
            select=new Select(webElement);
            return true;
        }return false;
    }

    /**根据combobox中项所在索引进行选择<br>
     * @param index 要选择选项的索引号*/
    public void selectByIndex(int index){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        if(isElementExist()){
            message.append(this.comment+":");

            select.selectByIndex(index);

            message.append("选择第"+index+"个选项成功！");
            log.info(message.toString());
        }else{
            message.append(this.comment+":");
            message.append("根据索引选择失败!找不到元素");
            log.error(message.toString());
        }
    }

    /**根据combobox中的内容进行选择<br>
     * @param value 要选择项的内容*/
    public void selectByValue(String value){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        if(isElementExist()){
            message.append(this.comment+":");

            select.selectByValue(value);

            message.append("选择"+value+"所在选项成功！");
            log.info(message.toString());
        }else{
            message.append(this.comment+":");
            message.append("根据内容选择失败!找不到元素");
            log.error(message.toString());;
        }
    }

    /**根据combobox标签之间的内容进行选择<br>
     * 例如：<option value="foo">Bar</option> 那么参数就是Bar<br>
     * @param value 要选择项的内容*/
    public void selectByVisiableValue(String value){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        if(isElementExist()){
            message.append(this.comment+":");

            select.selectByVisibleText(value);

            message.append("选择"+value+"所在选项成功！");
            log.info(message.toString());
        }else{
            message.append(this.comment+":");
            message.append("根据可见内容选择失败!找不到元素");
            log.error(message.toString());;
        }
    }

    /**获得下拉框的选中的index ，从0开始<br>
     * @return 被选中的索引号*/
    public int getSelectedIndex(){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        int selectedIndex=-1;
        if(isElementExist()){
            message.append(this.comment+":");

            List<WebElement> allItems=select.getOptions();
            WebElement selectedItem=select.getFirstSelectedOption();
            for(int i=0;i<allItems.size();i++)
                if(allItems.get(i).equals(selectedItem)){
                    selectedIndex=i;break;
                }

            message.append("获得选中项的索引成功！");
            log.info(message.toString());
        }else{
            message.append(this.comment+":");
            message.append("获得选中项的索引失败!找不到元素");
            log.error(message.toString());;
        }
        return selectedIndex;
    }

    /**获得选中项的内容<br>
     * @return 选中项的内容，字符窜*/
    public String getSelectedValue(){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        String selectedValue=null;
        if(isElementExist()){
            message.append(this.comment+":");

            selectedValue=select.getFirstSelectedOption().getText();

        }else{
            message.append(this.comment+":");
            message.append("获得选中项的内容失败!找不到元素");
            log.error(message.toString());;
        }
        return selectedValue;
    }

    /**如果选择了多个选项，那么这个时候就用此方法，获得多个选项<br>
     * @return 被选中的所有选项的内容*/
    public String[] getSelectedValues(){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        String[] selectedValue=null;
        if(isElementExist()){
            message.append(this.comment+":");

            List<WebElement>selectedItems=select.getAllSelectedOptions();
            selectedValue=new String[selectedItems.size()];
            for(int i=0;i<selectedItems.size();i++){
                selectedValue[i]=selectedItems.get(i).getText();

            }
            message.append("获得选中项的内容成功!");
            log.error(message.toString());;
        }else{
            message.append(this.comment+":");
            message.append("获得选中项的内容失败!找不到元素");
            log.error(message.toString());;
        }
        return selectedValue;
    }

    /**如果选择了多个选项，那么这个时候就用此方法，获得多个选项<br>
     * @return 被选中的所有选项的索引*/
    @SuppressWarnings("null")
    public int[] getSelectedIndexes(){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        int[] selectedIndexes=null;
        if(isElementExist()){
            message.append(this.comment+":");

            List<WebElement>selectedItems=select.getAllSelectedOptions();
            List<WebElement>allItems=select.getOptions();
            selectedIndexes=new int[selectedItems.size()];
            for(int i=0;i<selectedItems.size();i++){
                for(int j=0;j<selectedItems.size();j++){
                    if(selectedItems.get(i).equals(allItems.get(j))){
                        selectedIndexes[i]=j;break;
                    }
                }
            }
            message.append("获得选中项的内容成功!");
            log.error(message.toString());;
        }else{
            message.append(this.comment+":");
            message.append("获得选中项的内容失败!找不到元素");
            log.error(message.toString());;
        }
        return selectedIndexes;
    }

    /**获得所有的选项内容
     * @return 所有选项的内容，字符窜形式*/
    public String[]getItems(){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        String[] allItems=null;
        if(isElementExist()){
            message.append(this.comment+":");

            List<WebElement>allItemsTemp=select.getOptions();
            allItems=new String[allItemsTemp.size()];
            for(int i=0;i<allItems.length;i++){
                allItems[i]=allItemsTemp.get(i).getText();

            }
            message.append("获取所有选项值成功！");
            log.info(message.toString());
        }else{
            message.append(this.comment+":");
            message.append("获取所有选项值失败，找不到元素！");
            log.error(message.toString());;
        }
        return allItems;
    }

    /**获得combobox的大小<br>
     * @return combobox的大小*/
    public int getComboboxSize(){
        StringBuilder message=new StringBuilder();
        BrowserWindow currentWindow=getAllocator().currentWindow;
        int size=-1;
        if(isElementExist()){
            message.append(this.comment+":");
            size=select.getOptions().size();
            message.append("获取combobox的大小成功！");
            log.info(message.toString());
        }else{
            message.append(this.comment+":");
            message.append("获取combobox的大小失败，找不到元素！");
            log.error(message.toString());;
        }
        return size;
    }


}
