package com.run;
/**
 * 验证给静态变量，在内存中有几个引用
 * 静态变量只有一个引用 在内存中的静态方法取，即使new出来多个对象实例，还是一个，它与类同周期
 * @author zhangwenbao
 *
 */
public class TestStatic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StaticClass ddd = new StaticClass("ddd");
		StaticClass ccc = new StaticClass("ccc");
		ddd.printStr();
		ccc.printStr();
		
		ClassStaticVariable v1 = new ClassStaticVariable("v1");
		ClassStaticVariable v2 = new ClassStaticVariable("v2");
		v1.printStr();
		v2.printStr();
	}
	
	
	static class StaticClass{
		
		public static String name="xxx";
		
		public StaticClass(String name){
			this.name=name;
		}
		public void printStr(){
			System.out.println(name);
		}
	}

}
