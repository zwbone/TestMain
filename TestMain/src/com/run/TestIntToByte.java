package com.run;

public class TestIntToByte {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] c_2000 = { 2, 2000 };
		byte[] current_2000 = { 0x02, 0x00, 0x00, 0x00, (byte) 0xd0, 0x07, 0x00, 0x00 };
		// ʮ���� 2 = 0x02 ʮ�����ƣ�intռ4���ֽڣ�byteռһ���ֽڣ�8 bit����byte�б�ʾ��
		// 0x02,0x00,0x00,0x00
		// ʮ���� 2000 = 0x7d0 ʮ�����ƣ�intռ4���ֽڣ�byteռһ���ֽڣ�8 bit����byte�б�ʾ��
		// 0xd0,0x07,0x00,0x00
		// �������λ���ұߣ�2000 = 0x7d0 = 0 x 16��0�η� + 13��d��x 16��1�η� + 7 x 16��2�η�
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
