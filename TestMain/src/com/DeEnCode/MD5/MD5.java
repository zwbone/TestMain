package com.DeEnCode.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
* ʵ��MD5����
*
*/
public class MD5 {

	/**
	 * ��ȡ���ܺ���ַ���
	 * 
	 * @param input
	 * @return
	 */
	public static String stringMD5(String pw) {
		try {

			// �õ�һ��MD5ת�����������ҪSHA1�������ɡ�SHA1����
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// ������ַ���ת�����ֽ�����
			byte[] inputByteArray = pw.getBytes();
			// inputByteArray�������ַ���ת���õ����ֽ�����
			messageDigest.update(inputByteArray);
			// ת�������ؽ����Ҳ���ֽ����飬����16��Ԫ��
			byte[] resultByteArray = messageDigest.digest();
			// �ַ�����ת�����ַ�������
			return byteArrayToHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	/*MessageDigest digest = MessageDigest.getInstance("MD5");
	digest.update(key.getBytes());
	byte[] bytes = digest.digest(key.getBytes());
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < bytes.length; i++) {
	    String hex = Integer.toHexString(bytes[i]&0xff);
	    if (hex.length() == 1){
	        sb.append("0");
	    }
	    sb.append(hex);
	}
	String hexstring = sb.toString();*/

	public static String byteArrayToHex(byte[] byteArray) {

		// ���ȳ�ʼ��һ���ַ����飬�������ÿ��16�����ַ�
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		// char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9',
		// 'A','B','C','D','E','F' };
		// newһ���ַ����飬�������������ɽ���ַ����ģ�����һ�£�һ��byte�ǰ�λ�����ƣ�Ҳ����2λʮ�������ַ���2��8�η�����16��2�η�����
		char[] resultCharArray = new char[byteArray.length * 2];
		// �����ֽ����飬ͨ��λ���㣨λ����Ч�ʸߣ���ת�����ַ��ŵ��ַ�������ȥ
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// �ַ�������ϳ��ַ�������
		return new String(resultCharArray);
	}

}