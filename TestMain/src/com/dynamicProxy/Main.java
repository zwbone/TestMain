package com.dynamicProxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
	public static void main(String[] args) {
		// create proxy instance
		TimingInvocationHandler timingInvocationHandler = new TimingInvocationHandler(new OperateImpl());
		//������1 ������� 2 ��������(ί����)ʵ�ֽӿڵ� class 3 InvocationHandlerʵ��
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