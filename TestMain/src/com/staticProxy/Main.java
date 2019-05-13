package com.staticProxy;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassA a = new ClassA();
		
		ClassB b = new ClassB(a);
		
		b.operateMethod1();
		b.operateMethod2();
	}

}
