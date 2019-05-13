package com.base.classload;

public class PersionObject {

	public String name="广大人民";
	public int age=15;
	public PersionObject(){
		
	}
	public PersionObject(String name,int age){
		this.name=name;
		this.age=age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
