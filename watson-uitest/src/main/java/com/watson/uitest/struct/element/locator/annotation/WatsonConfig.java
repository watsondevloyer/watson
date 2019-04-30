package com.watson.uitest.struct.element.locator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WatsonConfig {

	int waitTime() default 1; //智能等待元素出现的时长单位秒

	String seleniumServerStandaloneHubUrl() default "";

    String screenShotDir() default "/opt";

}
