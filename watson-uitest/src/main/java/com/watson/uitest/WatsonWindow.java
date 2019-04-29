package com.watson.uitest;

import com.watson.uitest.window.Allocator;
import com.watson.uitest.window.EngineType;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/283:57 PM
 */
public class WatsonWindow {

    private static ThreadLocal<Allocator> allocatorThreadLocal=new ThreadLocal<Allocator>();

    public static Allocator getAllocator(){
        return  allocatorThreadLocal.get();
    }


    public  static  void  openNewWindow(EngineType engineType, String url){
        allocatorThreadLocal.set(new Allocator());
        getAllocator().addAndSetBrowserWindow(engineType, url);

    }

    public  static  void  openNewWindow(String url){
        allocatorThreadLocal.set(new Allocator());
        getAllocator().addAndSetBrowserWindow(EngineType.WebDriverChrome, url);

    }

    public  static  void  closeWindow(String title){
        getAllocator().closeWindow(title);

    }
    public  static  void  closeAllWindow(){
        getAllocator().closeAllWindow();

    }





}
