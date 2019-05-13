package com.design.proxy;

public class TestJDKProxy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			UserDaoI target = new UserDaoImpl();
			System.out.println(target.getClass());
			UserDaoI proxy = (UserDaoI) new ProxyFactory(target).getProxy();
			System.out.println(proxy.getClass());
			proxy.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("dddd"+e.getMessage());
		}
		
	}

}
