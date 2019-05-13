package com.run;

import java.io.*;

public class TestBufferedInputStream {
	public static void main(String[] args) throws Exception {
		InputStream inStream = new FileInputStream("d:/test.txt");
		//�й�ABCDEFGHIJKLMNOPQRST
		
		BufferedInputStream bis = new BufferedInputStream(inStream);

		// read number of bytes available,Ԥ�����Ļ���ֵ��С
		int numByte = bis.available();
		
		// byte array declared
		byte[] buf = new byte[numByte];

		// read byte into buf , starts at offset 2, 3 bytes to read
		bis.read(buf, 2, 5);

		// for each byte in buf
		for (byte b : buf) {
			System.out.print((char) b + ": " + b +"|");
		}
		//
		System.out.println();
		System.out.println("read is:"+ new String(buf)+"==>end");
		//read is:  �й�A                 ==>end
		System.out.println("read is:"+ new String(buf,2,3)+"==>end");
		//read is:��?==>end
		
		readandwrite();
	}
	
	public static void readandwrite() throws IOException{
		InputStream inStream = new FileInputStream("d:/test.txt");
		OutputStream outStream = new FileOutputStream("d:/test1.txt");
		//�й�ABCDEFGHIJKLMNOPQRST
		BufferedInputStream bis = new BufferedInputStream(inStream);
		BufferedOutputStream bos = new BufferedOutputStream(outStream); 
		// read number of bytes available,Ԥ�����Ļ���ֵ��С
		int numByte = bis.available();
		// byte array declared
		byte[] buf = new byte[numByte];
		while(bis.read(buf)!=-1){
			bos.write(buf);
		}
		bos.flush();
		bos.close();
		bis.close();
	}
}