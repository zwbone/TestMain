package com.run;
/**
 * 注意：在Thread类中有两个方法可以判断线程是否通过interrupt方法被终止。
 * 一个是静态的方法interrupted（），一个是非静态的方法isInterrupted（），
 * 这两个方法的区别是interrupted用来判断当前线是否被中断，而isInterrupted可以用来判断其他线程是否被中断。
 * 因此，while （！isInterrupted（））也可以换成while （！Thread.interrupted（））。
 * @author zhangwenbao
 *
 */
public class TestThread {
	public static void main(String[] args) {
		Secondthread st = new Secondthread();
		st.exist = true;
		st.start();
		try {
			Thread.sleep(5000);
			st.exist = false;
			System.out.println("st.exist="+st.exist);
			
			Thread.sleep(10000);
			st.exist = true;
			System.out.println("st.exist="+st.exist);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		st.stop();
		st.destroy();
		st.interrupt();
		System.out.println("线程的当前状态是："+st.getState());
		//线程的run(){}方法执行完 线程就会进入TERMINATED状态，也就是线程执行结束，可以被回收了（如果没有被持续引用的话）
		//TERMINATED 这个状态下表示 该线程的run方法已经执行完毕了, 基本上就等于死亡了(当时如果线程被持久持有, 可能不会被回收)
		//结束线程
		//1.使用标志位退出
		//2.使用interrupt()方法退出
	}

}
class Secondthread extends Thread{
	public static volatile boolean exist = true;
	//volatile 多线程同步，同一时刻一次仅允许一个线程修改值
	public static int couts = 0;
	@Override
	public void run(){
//		while(true){
			while(exist){
				try {
					Thread.sleep(100);
					couts++;
					System.out.println("===>>in while: "+couts+" <<==");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("===>>outer while: "+couts+" <<==");
//		}
	}
}
