package com.classload.order;

/**
 * 验证类的初始化顺序：
 * 父类静态块初始化！
 * 父类静态成员变量初始化！
 * 子类静态成员变量初始化！
 * 子类静态块初始化！
 * 父类普通成员变量初始化！
 * 父类非静态块初始化！
 * 父类构造函数初始化！
 * 子类普通成员变量初始化！
 * 子类非静态块初始化！
 * 子类构造函数初始化！
 * @author zhangwenbao
 * 类什么时候才被初始化 ？
 * 1）创建类的实例，也就是new一个对象 
 * 2）访问某个类或接口的静态变量，或者对该静态变量赋值
 * 3）调用类的静态方法
 * 4）反射（Class.forName("com.Class")）
 * 5）初始化一个类的子类（会首先初始化子类的父类）
 * 6）JVM启动时标明的启动类，即文件名和类名相同的那个类
 * 注：静态方法和非静态方法都不进行初始化，即方法不参与初始化，方法仅当调用是才执行！
 */
public class SonClazz extends FatherClazz {

	private OtherClazz sonVariable=new OtherClazz("子类普通成员变量初始化！");
	public static OtherClazz sonStaticVariable=new OtherClazz("子类静态成员变量初始化！");
	
	static{
		System.out.println("子类静态块初始化！");
	}
	{
		System.out.println("子类非静态块初始化！");
	}
	
	public SonClazz(){
		System.out.println("子类构造函数初始化！");
	}
	
	public void sonMethod(){
		System.out.println("子类普通方法执行！");
	}
	
	public static void sonMethod2(){
		System.out.println("子类静态方法执行！");
	}
	
	private static final class StaticInnerClass {
		//验证静态内部类是否在外部类初始化时进行初始化
		public static final OtherClazz innerClazz = new OtherClazz("静态内部类初始化！");
	}
}
