package com.watson.dbtest.annotation;

import java.lang.annotation.*;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/2311:01 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Inherited
public @interface Mongo {
    String id();
    String address() ;
    int port();
    String userName() default "";
    String passWord() default "";
    String dataBaseName() default "";

}
