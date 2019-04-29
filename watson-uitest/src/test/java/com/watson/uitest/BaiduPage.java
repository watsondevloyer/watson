package com.watson.uitest;
import com.watson.uitest.struct.element.Element;
import com.watson.uitest.struct.element.locator.annotation.*;
import com.watson.uitest.struct.Page;
import lombok.Getter;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/269:36 AM
 */
@Getter
public class BaiduPage extends Page {

  @ID("kw")
  private Element input;

  public void search(String value){
    input.sendKeys(value);
  }
}
