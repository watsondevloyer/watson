package com.watson.uitest.window;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/283:51 PM
 */
@Getter
public class Allocator {

    public BrowserWindow currentWindow;
    private List<BrowserWindow> windows=new ArrayList<BrowserWindow>();

    public void addAndSetBrowserWindow(EngineType engineType, String url) {
        BrowserWindow browserWindow =new BrowserWindow(WatsonDriver.createWebDriverEngine(engineType));
        windows.add(browserWindow);
        currentWindow= browserWindow;
        browserWindow.openWindow(url);

    }

    public void updateWindows(){
        int i=0;
        for(String handle:currentWindow.getWebDriver().getWindowHandles()){
            i=0;
            for(;i<windows.size();i++){
                if(handle.equalsIgnoreCase(windows.get(i).getWindowHandle())){
                    break;
                }
            }
            if(i>=windows.size()){
                BrowserWindow browserWindow =new BrowserWindow(currentWindow.getWebDriver());
                browserWindow.setWindowHandle(handle);
                windows.add(browserWindow);
            }
        }
    }

    public void closeAllWindow(){
        for (BrowserWindow browserWindow :windows){
            browserWindow.quit();
        }
    }

    public boolean closeAllWindowsByUrl(String url){
        boolean findWindow=false;
        for(BrowserWindow window:windows){
            if(window.getUrl().equalsIgnoreCase(url)){
                window.close();
                windows.remove(window);
                if(currentWindow.equals(window)){
                    if(windows.size()==0){
                        this.currentWindow=null;
                    }else{
                        this.currentWindow=this.windows.get(0);
                    }
                }
            }
            findWindow=true;
        }
        return findWindow;
    }
}
