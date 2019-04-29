package com.watson.uitest.struct;
import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.FieldAccess;
import com.watson.uitest.struct.element.Element;
import com.watson.uitest.struct.element.locator.annotation.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

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

        FieldAccess fieldAccess=FieldAccess.get(this.getClass());
        Field[] fields=fieldAccess.getFields();
        for(Field field:fields){
            if(Element.class.isAssignableFrom(field.getType())){
                initLocator(field);
            }
        }

    }

    public void initLocator(Field field){
        Annotation[] annotations = field.getAnnotations();
        if (annotations.length>0){
            try {
                ConstructorAccess constructorAccess=ConstructorAccess.get(field.getType());
                Element element = (Element) constructorAccess.newInstance();
                field.setAccessible(true);
                field.set(this,element);
                element.setComment(field.getName());
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

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
    }
}
