package com.watson;

import com.watson.dbtest.utils.ClassPool;
import com.watson.uitest.struct.element.locator.annotation.WatsonConfig;



/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/291:44 PM
 */
public class ConfigConstValue {

    public static int waitTime=0;
    public static String chromeDriverPath="";
    public static String seleniumServerStandaloneHubUrl="http://127.0.0.1:4444/wd/hub"; //远程hub地址

    static {
        WatsonConfig annotation = ClassPool.getAnnotation(WatsonConfig.class);
        if (annotation!=null){
            waitTime=annotation.waitTime();
            seleniumServerStandaloneHubUrl=annotation.seleniumServerStandaloneHubUrl();

        }
    }
}
