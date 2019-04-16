package com.watson.apitest.hessian;

import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/1610:25 AM
 */
public class HessianTest {

    private HessianProxyFactory factory;
    private HessianTest(){
        factory = new HessianProxyFactory();
        factory.setOverloadEnabled(true);
    }

    public static  HessianTest getInstance(){
        return HessianInner.hessianTest;
    }

    private static class HessianInner{
        public static HessianTest hessianTest=new HessianTest();
    }

    public  Object handler(Class<?> api, String url) {
        Object obj = null;
        try {
            obj = factory.create(api, url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
