package com.classload.HotSwapClassLoad;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 一个简单的hot swap 类加载器实现
 *
 * 可以重新载入同名类的类加载器实现
 *
 * 放弃了双亲委派的加载链模式.
 * 需要外部维护重载后的类的成员变量状态.
 *
 */
public class HotSwapClassLoader extends URLClassLoader {

    public HotSwapClassLoader(URL[] urls) {
        super (urls);
    }

    public HotSwapClassLoader(URL[] urls, ClassLoader parent) {
        super (urls, parent);
    }

    public Class load(String name)
            throws ClassNotFoundException {
        return load(name, false );
    }

    public Class load(String name, boolean resolve)
            throws ClassNotFoundException {
        if ( null != super .findLoadedClass(name))
            return reload(name, resolve);

        Class clazz = super .findClass(name);

        if (resolve)
            super .resolveClass(clazz);

        return clazz;
    }

    public Class reload(String name, boolean resolve)throws ClassNotFoundException {
        return new HotSwapClassLoader( super .getURLs(), super .getParent()).load(name, resolve);
    }
}