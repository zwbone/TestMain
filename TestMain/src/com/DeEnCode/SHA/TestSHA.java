package com.DeEnCode.SHA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestSHA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content = "123456";
		String algorithm = "SHA";
		System.out.println(encodeBySHA(content,algorithm));
		System.out.println(encodeBySHA(content,"SHA1"));
		System.out.println(encodeBySHA(content,"SHA-1"));
		System.out.println(encodeBySHA(content,"SHA-256"));
		System.out.println(encodeBySHA(content,"SHA-512"));
	}
	
	public static String encodeBySHA(String content,String algorithm){
		MessageDigest digest;
		StringBuffer sb = new StringBuffer();
		try {
			sb = new StringBuffer();
			digest = MessageDigest.getInstance(algorithm);
			byte[] result = digest.digest(content.getBytes());
			
			//消息摘要的结果一般都是转换成16 进制字符串形式展示
			//SHA 结果为20 字节（160 个比特位）、转换为16 进制表示后长度是40 个字符
			for (int i = 0; i < result.length; i++) {
				String hex = Integer.toHexString(result[i] & 0xff);
				if(hex.length()==1){
					sb.append("0");
				}
				sb.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

}
