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
public @interface Redis {

    String id();
    String masterName();
    String passwd();
    //redis集群ip地址
    String sentinelNodes();
    //多个ip分割符号
    String separator() default ";";

}
