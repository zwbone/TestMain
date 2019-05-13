package com.run;

import java.util.regex.*;

public class TestExe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		findNumFromStr();
		descThenBiggerAddSmaller();
		int n=10;
		System.out.println(n + "! = "+nFactorial(n));
		testFor();
	}
	public static void findNumFromStr(){
		String input = "xt9i5Q2BMWwith7";
		/*System.out.println(str+"�е�����Ϊ:");
		char[] ch = {'0','1','2','3','4','5','6','7','8','9'};
		char [] charr = str.toCharArray();
		for (int i = 0; i < charr.length; i++) {
			char tempch = charr[i];
			for (int j = 0; j < ch.length; j++) {
				if(tempch==ch[j]){
					System.out.print(tempch);
				}
			}
		}
		System.out.println();*/
		
		StringBuffer sb = new StringBuffer(":");
		Pattern p = Pattern.compile("[^0-9]+");//[0-9]+ƥ������ [^0-9]+ƥ�������
		Matcher m = p.matcher(input);
		
//		int groupCount = m.groupCount();
		boolean result = m.find();
//		String group0 = m.group(0);
//		System.out.println("��һ��ƥ�䵽��ֵ�ǣ�"+group0);
		
		while(result){
			//m.appendReplacement(sb,"-");//��input�ж�ȡ��sb������ƥ��ֵ��-�滻��,m�����и�ָ��ָ��input���ϴ�append��λ��
			m.appendReplacement(sb,"");
			
			System.out.println(m);
			//System.out.println(sb.toString());
			result = m.find();
		}
		m.appendTail(sb);
		System.out.println("�ַ��а������"+sb.toString());
		
		
	}
	public static void descThenBiggerAddSmaller(){
		int[] arr ={2,10,11,4,21,5,7,6,19,15};//ż��
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		int temp = arr[0];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length-1; j++) {
				if(arr[j] < arr[j+1]){
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		System.out.print("�������к�Ϊ��");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		System.out.println("�δ�+��С��....");
		for (int i = 0; i < arr.length/2; i++) {
			System.out.println(arr[i]+"+"+arr[arr.length-1-i]+" = "+(arr[i] + arr[arr.length-1-i]));
		}
	}
	
	public static int nFactorial(int n){
		if(n<0){
			return 0;
		}else{
			if(n==0||n==1){
				return 1;
			}else{
				return n*nFactorial(n-1);
			}
		}
	}
	
	public static void testFor(){
		for(fun("A");fun("B")&&fun("C");fun("D")){
			System.out.println("E");
		}
	}
	public static boolean fun(String ss){
		System.out.print(ss+" ");
		return false;
	}
}
