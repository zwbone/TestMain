package com.run;

/**
 * int[]不初始化默认是0
 * Object[]不初始化默认是null
 * 说明：
 * 对数组而言虽然其本身是引用类型，
 * 但基本类型的数组，在默认情况下仍然保持基本类型的赋值，
 * 如int[0]其默认值是0而不是null
 */
public class TestInitArray {

	static int[] arr=new int[10];
	static Object[] arr2=new Object[10];

	public static void run() {
		System.out.println(arr[1]);//0
		System.out.println(arr2[1]);//null
	}

}
