package com.run;

import java.util.ArrayList;
import java.util.List;

public class TestListRetainAll {

	public static void main(String[] args) {
		List<Person> list1 = new ArrayList<Person>();
		List<Person> list2 = new ArrayList<Person>();
		Person p1 = new Person("A",20); 
		Person p2 = new Person("B",21); 
		Person p3 = new Person("C",22);
		Person p4 = new Person("D",23); 
		Person p5 = new Person("C",24);
		Person p6 = new Person("F",25);
		list1.add(p1);
		list1.add(p2);
		list1.add(p3);
		list2.add(p4);
		list2.add(p5);
		list2.add(p6);
		/*boolean has = list1.retainAll(list2);
		System.out.println("list1 是否发送了变化===>>>>>>>>>"+has);
		System.out.println("list1的大小===>>>>>>>>>"+list1.size());
		System.out.println("list1.get(0).getName===>>>>>>>>>"+list1.get(0).getName()+"<<<list1.get(0).getAge===>>>>>>>>>"+list1.get(0).getAge());
		
		List<Person> list3 = new ArrayList<Person>(list1);//放交集
		List<Person> list4 = new ArrayList<Person>(list1);//放list1中有list2中没有的元素
		List<Person> list5 = new ArrayList<Person>(list2);//放list2中有list1中没有的元素
		
		list3.retainAll(list2);
		list4.removeAll(list2);
		list5.removeAll(list1);*/
		List<Person> list3 = new ArrayList<Person>();//放交集
		List<Person> list4 = new ArrayList<Person>();//放list1中有list2中没有的元素
		List<Person> list5 = new ArrayList<Person>();//放list2中有list1中没有的元素
		for (int i = 0; i < list1.size(); i++) {
			boolean isIn = false; 
			for (int j = 0; j < list2.size(); j++) {
				if(list1.get(i).getName().equals(list2.get(j).getName())){
					isIn = true;
				}
			}
			if(isIn){
				list3.add(list1.get(i));//交集放入一个
			}else{
				list4.add(list1.get(i));//差集放入一个
			}
		}
		for (int i = 0; i < list2.size(); i++) {
			boolean isIn = false; 
			for (int j = 0; j < list1.size(); j++) {
				if(list2.get(i).getName().equals(list1.get(j).getName())){
					isIn = true;
				}
			}
			if(!isIn){
				list5.add(list2.get(i));//在list2且不在list1中放入一个
			}
		}
		
		System.out.println("list3==>>>");
		for (Person person : list3) {
			System.out.print(">>>"+person.toString());
		}
		System.out.println();
		System.out.println("list4==>>>");
		for (Person person : list4) {
			System.out.print(">>>"+person.toString());
		}
		System.out.println();
		System.out.println("list5==>>>");
		for (Person person : list5) {
			System.out.print(">>>"+person.toString());
		}
		
		
	}
}
class Person {

	String name;
	int age;
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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

	/*@Override
	public boolean equals(Object obj) {
		if(obj instanceof Person){
			Person p = (Person)obj;
			if(this.name.equals(p.name)){
				return true;
			}
		}
		return false;
	}*/
	@Override
	public java.lang.String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}
