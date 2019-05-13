package com.run;

public class TestSwitch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(switchTO("C"));
	}

	public static String switchTO(String selectType){
		String realType="";
		switch(selectType){
		case "A": 
			realType= "A";
			break;
		case "B":
			realType= "B";
			break;
		case "C":
			realType= "C";
			break;
		default:
			realType= "D";
			break;
		}
		return realType;
	}
}
