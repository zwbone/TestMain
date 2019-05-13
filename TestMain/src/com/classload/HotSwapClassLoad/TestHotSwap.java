package com.classload.HotSwapClassLoad;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Create with IntelliJ IDEA
 * Date:{$Date}
 * Time:{TIME}
 */
public class TestHotSwap {

    public static void main(String args[]) {
        A a = new A();
        B b = new B();
        a.setB(b);

        System.out.printf("A classLoader is %s n",a.getClass().getClassLoader());
        System.out.println();
        System.out.printf("B classLoader is %s n",b.getClass().getClassLoader());
        System.out.println();
        System.out.printf("A.b classLoader is %s n",a.getB().getClass().getClassLoader());
        System.out.println();

        /*Class clazz = null;
        try {
            clazz = Class.forName("com.classload.HotSwapClassLoad.TestHotSwap");
            Object TestHotSwap = clazz.newInstance();
            System.out.println();
            System.out.printf(" reloaded TestHotSwap classLoader is %s n", TestHotSwap.getClass().getClassLoader());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }*/


        URL[] urls = new URL[1];
        //file:\\E:\\IntellJ_Works\\TestMain\\src\\com\\classload\\HotSwapClassLoad\\"
        String urlpath = "file:"+ File.separator+"E:"+File.separator+"IntellJ_Works"+File.separator+"TestMain"+File.separator+"src"+File.separator+"com"+File.separator+"classload"+File.separator+"HotSwapClassLoad"+File.separator;
        //urlpath.replaceAll("#",File.separator);
        System.out.println("urlpath==="+urlpath);

        try {
            URL url= new URL(urlpath);
            System.out.println("++++++===="+url.getProtocol());
            System.out.println("++++++===="+url.getPath());
            urls[0]=url;
            HotSwapClassLoader c1 = new HotSwapClassLoader(urls,a.getClass().getClassLoader());
            //HotSwapClassLoader c1 = new HotSwapClassLoader( new URL[]{ new URL( "file:\e:\test\")} , a.getClass().getClassLoader());
            Class clazz = c1.load("com.classload.HotSwapClassLoad.TestHotSwap");
            //Class clazz = Class.forName("com.classload.HotSwapClassLoad.TestHotSwap");
            Object aInstance = clazz.newInstance();
            Method method1 = clazz.getMethod(" setB ", B.class);
            method1.invoke(aInstance, b);
            Method method2 = clazz.getMethod(" getB ", null);

            Object bInstance = method2.invoke(aInstance, null);
            System.out.println();
            System.out.printf(" reloaded A.b classLoader is %s n", bInstance.getClass().getClassLoader());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}