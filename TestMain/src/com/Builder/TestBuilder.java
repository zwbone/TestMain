package com.Builder;

public class TestBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
class Person {

    private String name;
    private String address;
    private int age;
    private double salarry;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalarry() {
        return salarry;
    }

    public void setSalarry(double salarry) {
        this.salarry = salarry;
    }

    private Person(Builder builder){
        this.age=builder.age;
        this.address=builder.address;
        this.age=builder.age;
        this.salarry=builder.salarry;
    }
    
    //static的Builder类
    public static class Builder{
        
        //成员变量应该会被赋予初始值
        private String name;
        private String address;
        private int age;
        private double salarry;
        
        public Builder(String name){
            this.name=name;
        }
        
        public Builder address(String address){
            this.address=address;
            return this;
        }
        
        public Builder age(int age){
            this.age=age;
            return this;
        }
        
        public Builder salarry(double salarry){
            this.salarry=salarry;
            return this;
        }
        
        public Person build(){
            return new Person(this);
        }
        
    }
}