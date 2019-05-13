package com.design.observer;

public interface ObservableInterface {

	public void addObserver(ObserverInterface observer);
	public void removeObserver(ObserverInterface observer);
	public void notifyAllObserver(String context);
	
}
