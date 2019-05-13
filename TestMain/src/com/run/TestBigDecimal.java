package com.run;

import java.util.List;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.plaf.synth.SynthToggleButtonUI;

public class TestBigDecimal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Integer[][] level_Datas2 = new Integer[10][10];//list_res.size()
		for(int i = 0; i<10 ;i++){
			List<Integer> list = new ArrayList<Integer>();
			Integer[] intArr = new Integer[list.size()];
			level_Datas2[i] = list.toArray(intArr);
		}
		List<Integer[]> level_Datas = Arrays.asList(level_Datas2);
		
		for (int i = 0; i < level_Datas2.length; i++) {
			System.out.println("=="+level_Datas.get(i));
		}*/
		
		/*List<List<Integer>> level_Datas3 = new ArrayList<List<Integer>>(10);//list_res.size()
		List<Integer> list = null;
		for(int i = 0; i<10 ;i++){
			list = new ArrayList<Integer>();
			level_Datas3.add(i, list);
		}*/
//		DecimalFormat df=new DecimalFormat("######0.000000");   
		String tradeAmount = "4324.08";
		
//		BigDecimal big = new BigDecimal(tradeAmount);
//		BigDecimal big10000 = new BigDecimal("10");
//		BigDecimal tradeAmountValue = big.divide(big10000,8,BigDecimal.ROUND_HALF_UP);
//		System.out.println("tradeAmountValue.doubleValue()="+tradeAmountValue.doubleValue());
//		long point = (long)(tradeAmountValue.doubleValue() * 1 * 7);
//		System.out.println("point="+point);
		
		BigDecimal big = new BigDecimal(tradeAmount);
		BigDecimal big10000 = new BigDecimal("10000");
		BigDecimal tradeAmountValue = big.divide(big10000,6,BigDecimal.ROUND_HALF_UP);
		System.out.println("tradeAmountValue="+tradeAmountValue.doubleValue());
		double point = big.divide(big10000,8,BigDecimal.ROUND_HALF_UP).doubleValue() * 1 * 7 * 1000;
		System.out.println("point="+(long)point);
		
		
		/*String str = "goodlucky";
		char[] strArray = str.toCharArray();//ת����char����
		byte[] byteArray = str.getBytes();//ת����byte����
		
		ArrayList<String> list = new ArrayList<String>();
		list.add(str);
		Object[] obj = list.toArray();//ת����Object����
		System.out.println(obj.toString());
		
		StringBuffer conditions = new StringBuffer("AND( ( AND belongBank.F3 IS NOT NULL  AND belongBank.F15 = '8303') OR ");
		
		final String conditionFilter = conditions.substring(0,conditions.lastIndexOf("OR"))+")";
		
		System.out.println(conditionFilter);*/
		
		
	}

}
