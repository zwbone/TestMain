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
			
			//��ϢժҪ�Ľ��һ�㶼��ת����16 �����ַ�����ʽչʾ
//			String hex = Hex.encode(result);
			//MD5 ���Ϊ16 �ֽڣ�128 ������λ����ת��Ϊ16 ���Ʊ�ʾ�󳤶���32 ���ַ�
			//SHA ���Ϊ20 �ֽڣ�160 ������λ����ת��Ϊ16 ���Ʊ�ʾ�󳤶���40 ���ַ�
			
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
