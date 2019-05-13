package com.design.observer;

import java.util.ArrayList;
import java.util.List;

public class ObservableImpl implements ObservableInterface {
	private String context = "";
	private List<ObserverInterface> observerList = new ArrayList<ObserverInterface>();
	@Override
	public void addObserver(ObserverInterface observer) {
		// TODO Auto-generated method stub
		observerList.add(observer);
	}

	@Override
	public void removeObserver(ObserverInterface observer) {
		// TODO Auto-generated method stub
		observerList.remove(observer);
	}

	@Override
	public void notifyAllObserver(String context) {
		for (ObserverInterface ob : observerList) {
			ob.update(context);
		}
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		if(!context.equals(this.context)){
			notifyAllObserver(context);
		}
		this.context = context;
	}
	
}
