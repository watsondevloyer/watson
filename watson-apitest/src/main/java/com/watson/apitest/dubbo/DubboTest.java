package com.watson.apitest.dubbo;


import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/1211:03 AM
 */
@Slf4j
public class DubboTest {

    public static Map<String,Object> services=new HashMap<String, Object>();
    private DubboTest() {
    }


    public static DubboTest getInstance(){
        return DubboConsumer.DUBBO_CONSUMER_TEST;
    }



  private static class DubboConsumer{
        private final static DubboTest DUBBO_CONSUMER_TEST =new DubboTest();
  }

    public  Object getService(Class clazz,String dubboAdress){
        String className=clazz.getName();
        if(null==services.get(className+"&"+dubboAdress)){
            Object rpcService = getRpcService(className,dubboAdress);
            services.put(className+"&"+dubboAdress,rpcService);
            return rpcService;
        }
        return services.get(className+"&"+dubboAdress);
    }

    public  Object getService(Class clazz,String dubboAdress,int timeOut){
        String className=clazz.getName();
        if(null==services.get(className+"&"+dubboAdress)){
            Object rpcService = getRpcService(className,dubboAdress,timeOut);
            services.put(className+"&"+dubboAdress,rpcService);
            return rpcService;
        }
        return services.get(className+"&"+dubboAdress);
    }

    private  Object getRpcService(String className,String dubboAdress) {

        return getRpcService(className,dubboAdress,0);
    }


    private  Object getRpcService(String className,String dubboAdress,int timeOut) {
        Class<?> interfaceClass=loadClass(className);
        ReferenceConfig reference = new ReferenceConfig();
        ApplicationConfig applicationConfig=new ApplicationConfig();
        applicationConfig.setName("Test");
        RegistryConfig registry=new RegistryConfig();
        registry.setAddress(dubboAdress);
        reference.setApplication(applicationConfig);
        reference.setInterface(interfaceClass);
        reference.setTimeout(timeOut);
        reference.setRegistry(registry);
        reference.setRetries(0);
        Object service = reference.get();
        return service;
    }

    private  Class loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }



}
