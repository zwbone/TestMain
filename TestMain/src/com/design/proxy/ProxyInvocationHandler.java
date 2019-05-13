package com.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 创建一个继承InvocationHandler类，负责将被代理对象的方法，与代理类关联
 */
public class ProxyInvocationHandler implements InvocationHandler {

	private Object target;
	public ProxyInvocationHandler(Object target){
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		//加缓存，打日志 该例是记录被代理对象执行时间
		long begintime = System.currentTimeMillis();
		System.out.println("模拟事务开始时间："+begintime);
		method.invoke(target, args);
		Thread.sleep(3000);
		
		long endtime = System.currentTimeMillis();
		System.out.println("模拟事务结束时间："+endtime);
		System.out.println("方法用时："+(endtime-begintime)+"毫秒！");
		return null;
	}
}
