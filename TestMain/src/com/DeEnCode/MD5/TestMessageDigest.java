package com.DeEnCode.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

public class TestMessageDigest {

	public static void main(String[] args) {
		
		String content = "12345600";
		
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			
			byte[] result = digest.digest(content.getBytes());
			
			//消息摘要的结果一般都是转换成16 进制字符串形式展示
//			String hex = Hex.encode(result);
			//MD5 结果为16 字节（128 个比特位）、转换为16 进制表示后长度是32 个字符
			//SHA 结果为20 字节（160 个比特位）、转换为16 进制表示后长度是40 个字符
			
			System.out.println(byteArraytoHex(result));
//			System.out.println(hex);
			
			String encodeString = MD5.stringMD5(content);
			System.out.println(encodeString);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String byteArraytoHex(byte[] byteArray){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			String hex = Integer.toHexString(byteArray[i] & 0xff);
			if(hex.length()==1){
				sb.append("0");
			}
			sb.append(hex);
		}
		return sb.toString();
	}
}
