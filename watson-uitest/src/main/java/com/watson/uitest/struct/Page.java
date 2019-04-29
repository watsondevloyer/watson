package com.watson.uitest.struct;
import com.watson.uitest.struct.element.Element;
import com.watson.uitest.struct.element.locator.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/255:23 PM
 */
public class Page {

    public Page() {
        init();
    }

    public void init(){
        int waitTime=0;
        if(this.getClass().getAnnotation(WaitTime.class)!=null){
            WaitTime annotation = this.getClass().getAnnotation(WaitTime.class);
            waitTime=annotation.value();
        }
        Field[] fields=this.getClass().getDeclaredFields();
        for(Field field:fields){
            if(Element.class.isAssignableFrom(field.getType())){
                initLocator(field,waitTime);
            }
        }

    }

    public void initLocator(Field field,int waitTime){
        Annotation[] annotations = field.getAnnotations();
        if (annotations.length>0){
            try {
                Constructor<?> constructor = field.getType().getConstructor();
                Element element = (Element) constructor.newInstance();
                field.setAccessible(true);
                field.set(this,element);
                element.setComment(field.getName());
                element.setWaitTime(waitTime);
                for(Annotation annotation:annotations){
                    if (ID.class.isAssignableFrom(annotation.annotationType())){
                        element.getLocator().addIdLocator(((ID)annotation).value());
                    }
                    if (Name.class.isAssignableFrom(annotation.annotationType())){
                        element.getLocator().addNameLocator(((Name)annotation).value());
                    }
                    if (Xpath.class.isAssignableFrom(annotation.annotationType())){
                        element.getLocator().addXpathLocator(((Xpath)annotation).value());
                    }
                    if (ClassName.class.isAssignableFrom(annotation.annotationType())){
                        element.getLocator().addClassLocator(((ClassName)annotation).value());
                    }
                    if (LinkText.class.isAssignableFrom(annotation.annotationType())){
                        element.getLocator().addLinkTextLocator(((LinkText)annotation).value());
                    }
                    if (CSS.class.isAssignableFrom(annotation.annotationType())){
                        element.getLocator().addCSSLocator(((CSS)annotation).value());
                    }
                    if (TagName.class.isAssignableFrom(annotation.annotationType())){
                        element.getLocator().addTagNameLocator(((TagName)annotation).value());
                    }
                    if (PartialLinkText.class.isAssignableFrom(annotation.annotationType())){
                        element.getLocator().addPartialLinkTextLocator(((PartialLinkText)annotation).value());
                    }
                    if(ElementWaitTime.class.isAssignableFrom(annotation.annotationType())){
                        element.setElementWaitTime(((ElementWaitTime)annotation).value());
                    }

                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        }
    }
}
