package com.run;

import java.io.File;
import java.io.UnsupportedEncodingException;
/**
 * File.separator windows os>>>>is:\<<<<
 * File.separator linux os>>>>is:/<<<<
 * @author zhangwenbao
 *
 */
public class TestLastIndexOf {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String fileid ="group1/M00/00/00/201207201702026181000000000000.jpg";
		String fileName= fileid.substring(fileid.lastIndexOf("/"));//[16,end)
		System.out.println(fileid.lastIndexOf("/"));///201207201702026181000000000000.jpg
		System.out.println(fileName);//16
//		System.out.println("File.separator is >>>>"+File.separator+"<<<<");//>>>>\<<<<
		
		String fileid2 = "http://webproxydev2.purang.com:9080/group1/M00/00/0F/CgpAcFtHCd-AS37vAAMrypy4zX8564.jpg";
		String filePath2 = java.net.URLDecoder.decode(fileid2, "UTF-8");
		System.out.println("filePath>>"+filePath2);
		String fileName2= filePath2.substring(filePath2.lastIndexOf("/")+1);
		System.out.println("fileName>>"+fileName2);
		System.out.println("fianl filename>>"+new String(fileName2.getBytes("gb2312"),"ISO-8859-1"));
		
		
	}

}
