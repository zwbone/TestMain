package com.base.innerclass;

import java.lang.reflect.Field;

public class TestInner {

	private String name;
	public static void main(String[] args) {
		TestInner t = new TestInner();
		t.setName("ddddddxx");
		t.test();
	}

	public void test(){
		PrintInterface pi = new PrintInterface(){
			@Override
			public void printStr() {
				// TODO Auto-generated method stub
				System.out.println(getName());
			}
			
		};
		pi.printStr();
		
		Class clazz = pi.getClass();
		try {
			Field[] fields = clazz.getDeclaredFields();
			
			for (Field field :fields) {
				System.out.println("第个是"+field.getName());
				
				try {
					//内部类有一个成员指向外部类，一般是this$0但可能重名，所以要结合getModifyder判断
					TestInner obj = (TestInner)field.get(pi);
					System.out.println(obj.getName());
					
					//Class.forName(obj.toString())
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
