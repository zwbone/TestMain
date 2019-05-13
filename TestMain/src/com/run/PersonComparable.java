package com.run;

public class PersonComparable implements Comparable<PersonComparable>{

	String name;
	int age;
	
	public PersonComparable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonComparable(String name, int age) {
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

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PersonComparable){
			PersonComparable p = (PersonComparable)obj;
			if(this.name.equals(p.name)){
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(PersonComparable o) {
		if(this.name.equals(o.name)){
			return 0;
		}
		return -1;
	}
	
}
