package com.classload.customLoad;

/**
 * 自定义类加载器
 * Date:{$Date}
 * Time:{TIME}
 */
public class MyClassLoad extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        return defineClass(name,new byte[10],0,100);
    }
}
