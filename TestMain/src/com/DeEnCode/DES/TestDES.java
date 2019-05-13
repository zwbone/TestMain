package com.DeEnCode.DES;

public class TestDES {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String key = "123456789";
		String data = "012345678";
		try {
			String encodedstr = DES.encode(key, data);
			String decodedstr = DES.decode(key, encodedstr);
			System.out.println(encodedstr);
			System.out.println(decodedstr);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
