package com.run;
/**
 * 测试数据超出int最大范围
 * int(整型4个字节32位) [-2147483648,2147483648)
 * @author zhangwenbao
 */
public class TestOverFlowInt {

	public static void main(String[] args) {
		
		test(2147483647);
	}
	
	public static void  test(int value){
		//int amount = value+1;//2147483647+1= 2147483648 大于了最大值
		int amount = (int)value*100;//214748364 * 100 = 21474836400 大于了最大值
		System.out.println("value="+value);
		System.out.println("amount="+amount);
	}

}
