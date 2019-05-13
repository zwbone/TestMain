package com.run;
/**
 * Java中多个main函数的关系跟作用 
 * 不用把main函数想的那么特别和复杂，他和普通的静态函数没什么区别，，
同一个package中的类是可以有自己的main方法的，而且类与类之间的main方法也是可以相互调用的，只是一般没人这么做！
唯一的不同就是他被定义为程序的入口，
而且内部类也是可以有main方法的，但是前提是这个内部类必须是static修饰的 

你可以让你的代码有多个入口，你只需要明白你什么时候要用哪个类作为入口就可以了，
但是要注意的是，用不同入口启动的两个进程是毫不相关的!!!!!!!!!!!!是两个 不同的application！！！
Q:当一个类继承另一个类后,子代会继承双亲的main()函数吗?
    若会,那不是在一个编译单元有多个主函数了?而在一个编译单元应该只有一个main()函数啊?
A:main方法也是个方法，当然会被继承。
   不是只能有一个main方法，而是只能有一个有main方法的main-class，作为一个程序的入口。
   关于main-class可以看一下关于jar命令的用法。
 *
 */
public class TestEnum2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("isActiveDone2:"+IsDone.isDone.isActiveDone());
		System.out.println("isActiveDone2:"+IsDone.isDone.isSigninDone());
	}

}
