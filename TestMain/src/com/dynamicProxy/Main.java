package com.dynamicProxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
	public static void main(String[] args) {
		// create proxy instance
		TimingInvocationHandler timingInvocationHandler = new TimingInvocationHandler(new OperateImpl());
		//参数：1 类加载器 2 被代理类(委托类)实现接口的 class 3 InvocationHandler实例
		//newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
		Operate operate = (Operate) (Proxy.newProxyInstance(Operate.class.getClassLoader(), new Class[] { Operate.class }, timingInvocationHandler));

		// call method of proxy instance
		operate.operateMethod1();
		System.out.println();
		operate.operateMethod2();
		System.out.println();
		operate.operateMethod3();
	}
}