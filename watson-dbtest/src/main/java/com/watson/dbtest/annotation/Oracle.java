package com.watson.dbtest.annotation;

import java.lang.annotation.*;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/2311:00 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Inherited
public @interface Oracle {
    String id();
    String url();
    String username();
    String password();

}
