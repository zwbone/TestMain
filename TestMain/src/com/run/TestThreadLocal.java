package com.run;
/**
 * 一般线程会随着线程结束生命周期自动结束
 * 线程池的话是复用线程，需要清掉 threadlocal.set(null);防止内存泄露
 * 一个ThreadLocal对象可以保存一个变量，创建多个ThreadLocal可以保存多个变量
 * @author zhangwenbao
 */
public class TestThreadLocal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Context.setSessionId("thread 1");
				System.out.println(Context.getSessionId());
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Context.setSessionId("thread 2");
				System.out.println(Context.getSessionId());
				Context.myThreadLocal.set(null);//清空，防止内存泄露
			}
		});
		
		final TestThreadLocal.Context2 ct2 = (new TestThreadLocal()).new Context2();
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ct2.setSessionId("thread 3");
				System.out.println(ct2.getSessionId());
			}
		});
		
		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ct2.setSessionId("thread 4");
				System.out.println(ct2.getSessionId());
			}
		});
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}
	
	
	
	static class Context {
		/**
		 * 静态的 与类同存，在内存中只一份 如果需要new出多个实例 实现不同，这种就不合适了
		 */
		private static final ThreadLocal<String> myThreadLocal= new ThreadLocal<String>();

		public static String getSessionId() {
			return myThreadLocal.get();
		}
		
		public static void setSessionId(String sessionId) {
			myThreadLocal.set(sessionId);
		}
		
		/*private static String sessionId="2222"; 不满足多线程
		public static String getSessionId() {
			return sessionId;
		}
		public static void setSessionId(String psessionId) {
			sessionId=psessionId;
		}*/
	}
	
	class Context2 {
		
		private ThreadLocal<String> myThreadLocal= new ThreadLocal<String>();

		public String getSessionId() {
			return myThreadLocal.get();
		}
		
		public void setSessionId(String sessionId) {
			myThreadLocal.set(sessionId);
		}
		
		/*private String sessionId="2222"; 不满足多线程
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String psessionId) {
			sessionId=psessionId;
		}*/
	}

}
