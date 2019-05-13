package com.base.innerclass;
public class TestInnerClass {
	private InnerClassA innerAxx = this.new InnerClassA("我是外部类的成员变量——内部类实例！");
	public TestInnerClass(){
		System.out.println("我是外部类构建函数TestInnerClass");
	}
	public void printOutString(){
		System.out.println("我是外部类方法！");
	}
	public static void main(String[] args) {
		//1.创建内部类
		TestInnerClass.InnerClassA innerA = (new TestInnerClass()).new InnerClassA("我是内部类");//需要先创建外部类实例才能创建内部类，且内部类不能含静态的
		innerA.printSting();
		//2.外部类调用成员内部类
		TestInnerClass outer = new TestInnerClass();
		outer.innerAxx.printSting();
		//3.内部类调用外部类
		TestInnerClass outer2 = new TestInnerClass();
		TestInnerClass.InnerClassB innerB = outer2.new InnerClassB("我是内部类B");
		innerB.printSting();
	}
	class InnerClassA {
		private String name1;
		public InnerClassA(String name){
			this.name1=name;
			System.out.println("我是内部类构建函数InnerClassA");
		}
		public void printSting(){
			System.out.println("内部类输出！！！！"+name1);
		}
	}
	class InnerClassB {
		private String name2;
		public InnerClassB(String name){
			this.name2=name;
			System.out.println("我是内部类构建函数InnerClassB");
		}
		public void printSting(){
			System.out.println("内部类输出printSting！！！！"+name2);
			TestInnerClass.this.printOutString();
			//printOutString();
			System.out.println("内部类输出printSting！！！！"+name2);
		}
	}

}
