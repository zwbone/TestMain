package com.run;

public class TestThreadJoin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread t1 = new MyThread(); 
		System.out.println("in mian: begin");
        t1.start();
        System.out.println("in mian: end");
	}

}

class MyThread extends Thread {
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("线程1第" + i + "次执行！");
			
			Thread t2 = new Thread(new MYRunnable());
			try {
				t2.join();
				t2.start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
} 

class MYRunnable implements Runnable {
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("线程2第" + i + "次执行！");
		}
	}
}
