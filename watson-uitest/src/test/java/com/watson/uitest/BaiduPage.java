package com.watson.uitest;
import com.watson.uitest.struct.element.Element;
import com.watson.uitest.struct.element.locator.annotation.*;
import com.watson.uitest.struct.Page;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/269:36 AM
 */

public class BaiduPage extends Page {

  @ID("kw")
  public Element input;

  public void search(String value){
    input.sendKeys(value);
  }
}

