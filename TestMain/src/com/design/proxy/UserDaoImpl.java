package com.design.proxy;
/**
 * 创建被代理接口的实现
 */
public class UserDaoImpl implements UserDaoI {

	@Override
	public void save() {
		// TODO Auto-generated method stub
		System.out.println("已经保存！！");
	}
}
