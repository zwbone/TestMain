package com.run;

public class TestThreadSleep {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread t1 = new MyThread1(); 
        Thread t2 = new Thread(new MyRunnable1());
//        Integer IntegerCatcher = Integer.valueOf(100);
        
        t1.start();
        try {
        	Thread.sleep(3000);
//			t1.interrupt();
			t1.sleep(3000);
		} catch (Exception e) {
			System.out.println("in mian: "+e.getMessage());
			e.printStackTrace();
		}
        t2.start();
	}

}

class MyThread1 extends Thread {
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("线程1第" + i + "次执行！");
			try {
				this.sleep(50000);
			} catch (InterruptedException e) {
				System.out.println("in MyThread1: "+e.getMessage());
				e.printStackTrace();
				break;
			}
		}
	}
} 

class MyRunnable1 implements Runnable {
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("线程2第" + i + "次执行！");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("in MyRunnable: "+e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
