package com.run;
/**
 * 
 * String.substring(i*90, (i+1)*90);//[) eg.:(0,90)取0-89，90个长度
 *
 */
public class TestStringSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String logMessage="05010112010100000012345600000000000001560156313233343536373839304142434445464748494A00000105020113010100000012345600000000000001560156313233343536373839304142434445464748494A00000205030114010100000012345600000000000001560156313233343536373839304142434445464748494A00000305040115010199999999999900000000000001560156313233343536373839304142434445464748494A00000405050116010199999999999900000000000001560156313233343536373839304142434445464748494A00000505060117010199999999999900000000000001560156313233343536373839304142434445464748494A00000605070118010199999999999900000000000001560156313233343536373839304142434445464748494A00000705080119010199999999999900000000000001560156313233343536373839304142434445464748494A00000805090120010199999999999900000000000001560156313233343536373839304142434445464748494A00000905100121010199999999999900000000000001560156313233343536373839304142434445464748494A00000A";
		
		String [] messages = new String [logMessage.length()/90];
		
		for(int i=0;i<messages.length;i++){
			messages[i]=logMessage.substring(i*90, (i+1)*90);//[) eg.:(0,90)取0-89，90个长度
			System.out.println("第"+(i+1)+"条，长度为："+messages[i].length()+"字符串为："+messages[i]);
		}
	}

}
