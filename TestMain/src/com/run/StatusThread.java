package com.run;

public class StatusThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println("isActiveDone2:"+IsDone.isDone.isActiveDone());
		System.out.println("isActiveDone2:"+IsDone.isDone.isSigninDone());
	}

}
