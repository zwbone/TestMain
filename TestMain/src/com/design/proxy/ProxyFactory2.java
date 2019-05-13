package com.design.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
/**
 * 创建一个代理工厂，生产代理类
 */
public class ProxyFactory2 {

	private Object target;
	private InvocationHandler invocationHandler;
	public ProxyFactory2(Object target,InvocationHandler invocationHandler){
		this.target=target;
		this.invocationHandler=invocationHandler;
	}
	
	public Object getProxyInstance(){
		
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),invocationHandler);
	}
}
