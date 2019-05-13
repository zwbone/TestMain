package com.design.singleton;
/**
 * 静态内部类实现懒汉模式 实现延时加载 又能利用JVM对静态成员变量只初始化一次，实现线程安全的单例
 * @author zhangwenbao
 * 因为静态内部类 在getInstance()方法调用后才进行初始化
 * 而且通过反射，是不能从外部类获取内部类的属性的, 所以这种形式，很好的避免了反射入侵。
 */
public class StaticInnerClassSingleton {

	private StaticInnerClassSingleton() {
		if(null!=SingletonHolder.INSTANCE){
			throw new IllegalStateException();
		}
	}

	private static final class SingletonHolder {
		public static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
	}
	
	public static final StaticInnerClassSingleton getInstance(){
		
		return SingletonHolder.INSTANCE;
	}
}