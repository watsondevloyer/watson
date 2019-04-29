package com.watson.dbtest.utils;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/233:16 PM
 */
public class ClassPool {
    /**
     * @return 返回项目目录下的class的集合。
     */
    public static Set<Class<?>> getClassPool() {
        ClassFinder classPathScanHandler = new ClassFinder(false, true, null);
        Set<Class<?>> classPool = classPathScanHandler.getPackageAllClasses("", true);
        return classPool;

    }

    /**
     * @return 返回类集合的map形式，key值为类名，不包含包名
     */
    public static Map<String, Class<?>> getClasses() {
        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
        ClassFinder finder = new ClassFinder(false, true, null);
        for (Class<?> clazz : getClassPool()) {
            classMap.put(clazz.getSimpleName(), clazz);
        }
        return classMap;

    }

    public static Class<?> getClass(String name) {
        return getClasses().get(name);
    }

    /**
     * @return 返回项目目录下的基础包下的class的集合。
     */
    public static Set<Class<?>> getClassPool(String baseName) {

        ClassFinder classPathScanHandler = new ClassFinder(false, true, null);
        Set<Class<?>> classPool = classPathScanHandler.getPackageAllClasses(baseName, true);
        return classPool;

    }

    /**
     * 获取项目中某一个类的所有子类
     */
    public static Set<Class<?>> getChildClasses(Class<?> clazz) {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        for (Class<?> childclass : getClassPool()) {
            if (clazz.isAssignableFrom(childclass)) {
                classes.add(childclass);
            }
        }
        return classes;
    }

    /**
     * 返回包含某个注解的集合
     * @return
     */
    public static Set<Class<?>> getClassesContainAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classes = new HashSet<Class<?>>();
        Set<Class<?>> classPool = getClassPool();
        for(Class<?> clazz:classPool){
            if(clazz.isAnnotationPresent(annotationClass)){
                classes.add(clazz);
            }
        }
        return classes;
    }

    public static <T> T getAnnotation(Class<? extends Annotation> annotationClass){
        Class<?> classes =null;
        Set<Class<?>> classPool = getClassPool();
        for(Class<?> clazz:classPool){
            if(clazz.isAnnotationPresent(annotationClass)){
                classes=clazz;
                break;

            }
        }
        if(classes!=null){
            return (T)classes.getAnnotation(annotationClass);
        }
        return null;
    }

    public static void main(String[] args) {
        Set<Class<?>> classes = ClassPool.getClassPool("org.junit");
        for (Class clazz : classes) {
            System.out.println(clazz.getName());
        }
    }
}
