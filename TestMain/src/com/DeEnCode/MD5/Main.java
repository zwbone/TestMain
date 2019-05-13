package com.DeEnCode.MD5;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String content = "123456";
		String encodeString = MD5.stringMD5(content);
		System.out.println(encodeString);
	}

}
