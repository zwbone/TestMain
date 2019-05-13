package com.run;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestStringCompareTo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String nowdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String protocolStartDate="20180315";
		String protocolEndDate="20180322";
		if(protocolStartDate.compareTo(nowdate)<=0 && protocolEndDate.compareTo(nowdate)>=0){
			System.out.println(protocolStartDate.compareTo(nowdate)<=0);
			System.out.println(protocolEndDate.compareTo(nowdate)>=0);
		}
		System.out.println("time="+nowdate);
		
	}

}
