package com.run;

public class TestIntToByte {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] c_2000 = { 2, 2000 };
		byte[] current_2000 = { 0x02, 0x00, 0x00, 0x00, (byte) 0xd0, 0x07, 0x00, 0x00 };
		// 十进制 2 = 0x02 十六进制，int占4个字节，byte占一个字节（8 bit）在byte中表示：
		// 0x02,0x00,0x00,0x00
		// 十进制 2000 = 0x7d0 十六进制，int占4个字节，byte占一个字节（8 bit）在byte中表示：
		// 0xd0,0x07,0x00,0x00
		// 计算机高位在右边，2000 = 0x7d0 = 0 x 16的0次方 + 13（d）x 16的1次方 + 7 x 16的2次方
		/*
		 * for(byte pre : current_2000){ System.out.println((int)pre); }
		 */

		byte[] bb = c_2000.toString().getBytes();
		for (byte pre : bb) {
			System.out.println(pre);
		}
		System.out.println(bb[4]);
		System.out.println(current_2000[4]);
	}

}
