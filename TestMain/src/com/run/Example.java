package com.run;

public class Example {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		/*Persion:zhangsan_20
		User init:null_0
		User:lisi_21*/
	}
	
	static class Persion{
		public String name ="zhangsan";
		public int age =20;
		public Persion(){
//			System.out.println("Persion():"+name+"_"+age);
			init();
		}
		protected void init(){
			System.out.println("Persion.init():"+name+"_"+age);
		}
	}
	static class User extends Persion{
		public String name ="lisi";
		public int age =21;
		public User(){
			super();
			System.out.println("User():"+name+"_"+age);
		}
		protected void init(){
			super.init();
			System.out.println("User.init():"+name+"_"+age);
		}
	}

}
