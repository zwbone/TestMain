package com.run;

import java.net.URLDecoder;

public class TestEncoderDecoder {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
            String chinessLange= "中午是一个企业";
            String tempEcoder = java.net.URLEncoder.encode(chinessLange,"UTF-8");
            String tempDcoder = URLDecoder.decode(tempEcoder,"UTF-8");
            String chinessLange2= "中午是一个企业";
            String tempEcoder2 = java.net.URLEncoder.encode(java.net.URLEncoder.encode(chinessLange2,"UTF-8"),"UTF-8");
            String tempDcoder2 = java.net.URLDecoder.decode(tempEcoder2,"UTF-8");
        } catch (Exception e){
        	System.out.println("==="+e.getMessage());
        	e.printStackTrace();
        }
	}

}
