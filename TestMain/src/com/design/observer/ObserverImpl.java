package com.design.observer;

public class ObserverImpl implements ObserverInterface {

	private String name = "";
	@Override
	public void update(String context) {
		// TODO Auto-generated method stub
		System.out.println(name+"中："+context);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
