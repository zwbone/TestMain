package com.run;
/**
 * √∂æŸ¿‡
 */
public enum IsDone{
	isDone;
	private boolean isActiveDone=false;
	private boolean isSigninDone=false;
	
	public boolean isActiveDone() {
		return isActiveDone;
	}
	public void setActiveDone(boolean isActiveDone) {
		this.isActiveDone = isActiveDone;
	}
	public boolean isSigninDone() {
		return isSigninDone;
	}
	public void setSigninDone(boolean isSigninDone) {
		this.isSigninDone = isSigninDone;
	}
}
