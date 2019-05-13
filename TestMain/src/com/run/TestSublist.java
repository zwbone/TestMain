package com.run;

import java.util.ArrayList;
import java.util.List;

public class TestSublist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> tablesCK = new ArrayList<String>();
		tablesCK.add("1");
		tablesCK.add("2");
		tablesCK.add("3");
		tablesCK.add("4");
		
		List<String> tempTables2 = new ArrayList<String>();
		tempTables2 = tablesCK.subList(0, 0);//[0,1)前闭后开
		tempTables2 = tablesCK.subList(0, 1);//[0,1)前闭后开
		System.out.println("==========="+tempTables2.get(0));
		
	}

}
