package com.DeEnCode.DES;

import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class DES {
	public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

	/**
	 * DES�㷨������
	 *
	 * @param data
	 *            �������ַ���
	 * @param key
	 *            ����˽Կ�����Ȳ��ܹ�С��8λ
	 * @return ���ܺ���ֽ����飬һ����Base64����ʹ��
	 * @throws CryptException
	 *             �쳣
	 */
	public static String encode(String key, String data) throws Exception {
		return encode(key, data.getBytes());
	}

	/**
	 * DES�㷨������
	 *
	 * @param data
	 *            �������ַ���
	 * @param key
	 *            ����˽Կ�����Ȳ��ܹ�С��8λ
	 * @return ���ܺ���ֽ����飬һ����Base64����ʹ��
	 * @throws CryptException
	 *             �쳣
	 */
	public static String encode(String key, byte[] data) throws Exception {
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key�ĳ��Ȳ��ܹ�С��8λ�ֽ�
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
			IvParameterSpec iv = new IvParameterSpec("********".getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
			byte[] bytes = cipher.doFinal(data);
			return Base64.encodeBase64String(bytes);
//			return Base64.encode(bytes);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * DES�㷨������
	 *
	 * @param data
	 *            �������ַ���
	 * @param key
	 *            ����˽Կ�����Ȳ��ܹ�С��8λ
	 * @return ���ܺ���ֽ�����
	 * @throws Exception
	 *             �쳣
	 */
	public static byte[] decode(String key, byte[] data) throws Exception {
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key�ĳ��Ȳ��ܹ�С��8λ�ֽ�
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
			IvParameterSpec iv = new IvParameterSpec("********".getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
			return cipher.doFinal(data);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new Exception(e);
		}
	}

	/**
	 * ��ȡ������ֵ
	 * 
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static String decode(String key, String data) throws Exception {
		byte[] datas;
		String value = null;
		
		datas = decode(key,Base64.decodeBase64(data.getBytes()));

		value = new String(datas);
		if (value.equals("")) {
			throw new Exception();
		}
		return value;
	}
}
