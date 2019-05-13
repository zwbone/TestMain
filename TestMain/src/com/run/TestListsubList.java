package com.run;

import java.util.ArrayList;
import java.util.List;

/**
 * List.subList(startIndex, endIndex);[0,10)
 *
 */
public class TestListsubList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> tables = new ArrayList<String>();
		tables.add(0, "1");
		tables.add(1, "2");
		tables.add(2, "3");
		tables.add(3, "4");
		tables.add(4, "5");
		tables.add(5, "6");
		tables.add(6, "7");
		tables.add(7, "8");
		tables.add(8, "9");
		tables.add(9, "10");
		tables.add(10, "11");
		
		
		List<String> template = new ArrayList<String>();
		int counts = 10;
		int length = tables.size();
		int loops = ( length % counts==0?(length/counts):(length/counts +1) );
		
		for (int i = 0; i < loops; i++) {
			if( (i+1)*counts<=length ){
				template = tables.subList(i*counts, (i+1)*counts);//[0,10)
			}else{
				template = tables.subList(i*counts, tables.size());
			}
			System.out.println("template:"+i+":"+template.toString());
		}
	}

}
