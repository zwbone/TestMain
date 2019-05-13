package com.design.observer;

public class TestOberver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObservableImpl bi = new ObservableImpl();
		ObserverImpl ob1= new ObserverImpl();
		ob1.setName("ob1");
		ObserverImpl ob2= new ObserverImpl();
		ob2.setName("ob2");
		
		bi.addObserver(ob1);
		bi.addObserver(ob2);
		bi.setContext("请通知观察者，数据即将发生变化");
		
		ObserverImpl ob3= new ObserverImpl();
		ob3.setName("ob3");
		bi.addObserver(ob3);
		bi.setContext("数据已发生变化！");
		
		
		ObserverImpl ob5= new ObserverImpl();
		ob5.setName("ob5");
		bi.addObserver(ob5);
		bi.setContext("数据已发生变化！");
		
		
	}

}
