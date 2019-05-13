package com.run;

import java.io.IOException;
/**
 * 验证finally什么时候执行
 *try之前有异常是不执行finally的，执行了try 会执行finally
 *如果try catch finally中都return，那么finally中的return是最后执行的那个
 */
public class TestFinally {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		try {
			testFinally();
		} catch (Exception e) {
			System.out.println(">>>in main");
			e.printStackTrace();
		}
	}
		
	public static void testFinally() throws Exception{
		int a = 0,b = 0;
		int c =0,d =0;
//		d = a/b;
		//try 之前跑异常是不执行finally的，执行了try 会执行finally,
		//如果try catch finally中都return，那么finally中的return是最后执行的那个
		try {
			System.out.println(">>>in try");
			d = a/c;
		} catch (ArithmeticException e) {
			System.out.println(">>>in catch");
			throw new IOException();
			//c = a/b;
		}finally{
			System.out.println(">>>in finally");
		}
	}

}
