package com.base.classload;

import java.lang.reflect.Field;
/**
 * 自定义加载器可以实现加密 隔离 保护源代码
 * @author zhangwenbao
 *
 */
public class TestLoadTwice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 try{
			 	ClassLoader loader1 = TestLoadTwice.class.getClassLoader();
			 	System.out.println("loader--->" + loader1);
	            Class<?> c1 = loader1.loadClass("com.base.classload.PersionObject");
	            Object obj = c1.newInstance();
	            Field f = c1.getDeclaredField("age");
	            System.out.println(f.get(obj));//15
	            f.setInt(obj, 33);
	            System.out.println(f.get(obj));//33
	            
	            /**
	             * 不同的类加载器,该类加载器会加载java.lang.Object
	             * 这时候可以直接用默认加载器
	             */
	            ClassLoader loader3 = new MyClassLoader();
	            System.out.println("loader3--->" + loader3);
	            System.out.println("loader3 parent--->" + loader3.getParent());
	            Class<?> c3 = loader3.loadClass("com.base.classload.PersionObject");
	            Object obj3 = c3.newInstance();
	            f = c3.getDeclaredField("age");//i值并输出还是未改变的值
	            System.out.println(f.get(obj3));//15
	            
	            System.out.println(c3.equals(c1));//true
	            System.out.println(c3==c1);//true
	            System.out.println(obj==obj3);//true
	            System.out.println("c3:"+c3+"\nc1:"+c1); //c3和c1输出相同
	            
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
