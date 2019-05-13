package com.classload.order;
/**
 * 验证子类和父类初始化顺序
 */
public class TestClassLoadOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SonClazz son = new SonClazz();
		son.sonMethod();
		son.fatherMethod2();
		System.out.println("son.fatherStaticVariable"+son.fatherStaticVariable.toString());
		System.out.println("son.sonStaticVariable=="+son.sonStaticVariable.toString());
		//子类可以继承父类的非private成员变量和方法（静态的也可以）
	}

}
