package com.design.proxy;

import java.lang.reflect.InvocationHandler;
/**
 * 测试JDK动态代理
 */
public class TestJDKProxy2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserDaoI target = new UserDaoImpl();
		InvocationHandler invocationHandler = new ProxyInvocationHandler(target);
		System.out.println("target"+target.getClass());
		UserDaoI proxy = (UserDaoI) new ProxyFactory2(target,invocationHandler).getProxyInstance();
		System.out.println("proxy"+proxy.getClass());
		proxy.save();
	}

}
