package com.run;

import java.io.File;
/**
 * 获取当前虚拟目录
 * @author zhangwenbao
 *windows结果
 *C:\
 *D:\
 *E:\
 *F:\
 *linux结果
 *:一个/
 */
public class TestFilelistRoots {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File[] paths;
	      try{      
	         // returns pathnames for files and directory
	         paths = File.listRoots();
	         // for each pathname in pathname array
	         for(File path:paths)
	         {
	            // prints file and directory paths
	            System.out.println(path);
	         }
	      }catch(Exception e){
	         // if any error occurs
	         e.printStackTrace();
	      }
	}

}
