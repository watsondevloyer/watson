package com.watson.uitest.jquery;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by guochang.xie on 15-5-4.
 */
public class JqueryManager {

    private static JqueryManager jqueryManager=null;

    private JqueryManager(){}

    public Boolean isJQueryLoaded(JavascriptExecutor driver) {
        Boolean loaded;
        try {
            loaded = (Boolean) driver
                    .executeScript("if(typeof jQuery==\"undefined\"){return false;}else{return true;}");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    public static JqueryManager getInstance(){

        return  JqueryManagerInner.jqueryManager;
    }

    private static class JqueryManagerInner{
        public static JqueryManager jqueryManager=new JqueryManager();
    }

    public Object runJquery(WebDriver driver, String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if (!isJQueryLoaded(js)){
            js.executeScript(loadJquery());
            js.executeScript("window.jQuery=jQuery.noConflict();");
        }
        return js.executeScript(script, args);
    }
    public String loadJquery(){
        InputStream inStream = this.getClass().getResourceAsStream("jquery.min.js");
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1000];
        int rc = 0;
        String jquery=null;
        try {
            while ((rc = inStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            jquery=   new String(swapStream.toByteArray(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jquery;
    }

}
