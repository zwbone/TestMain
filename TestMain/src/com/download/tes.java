package com.download;

public class tes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String [] obj = new String[]{"123","456"};
		Integer ing = new Integer(123);
		char cc = '1';
		short ss = '1';
		byte bb = '1';
		long ll = 1;
		
		Occupy occ = Occupy.forSun64BitsVM();
		
		System.out.println(occ.sizeof(obj));
	}

}
