package com.run;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Persion {

	static class Pson{
		public String name;
		public int age;
		public Pson(String name,int age){
			this.name=name;
			this.age=age;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Pson> pers = new ArrayList<>();
		pers.add(new Pson("����",16));
		pers.add(new Pson("����",17));
		pers.add(new Pson("����",18));
		
		List<Pson> list1 = new ArrayList(pers);//ǳcopy
		List<Pson> list2 = new ArrayList(pers);//ǳcopy ����    Arrays.asList() ArrayList newArray = oldArray.clone();
//		List<Pson> list3 = new ArrayList(Arrays.asList(new Object[3]));
		//3��ʾ�������List����������Ϊ3��������˵des1�о�����3��Ԫ�ء����capacity������������С������ָ�������ָ����
		//��ʼ��ʱlist3.size�Ĵ�С��ԶĬ��Ϊ0��ֻ���ڽ���add��remove����ز��� ʱ��size�Ĵ�С�ű仯
//		Collections.copy(list3, pers);
		Collections.copy(pers, list2);//ǳcopy ����Ҫ��copy ��Ҫ �Ӷ���ʵ��cloneable�ӿڲ���д clone()���� ���� ʹ����
		
		
		pers.get(0).name="���� 1";
		list1.get(1).name="���� 2";
		list2.get(2).name="���� 3";
		list2.retainAll(list1);
		
		System.out.println("pers==");
		for (Iterator iterator = pers.iterator(); iterator.hasNext();) {
			Pson pson = (Pson) iterator.next();
			System.out.println(pson.name);
		}
		System.out.println("list1==");
		for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
			Pson pson = (Pson) iterator.next();
			System.out.println(pson.name);
			
		}
		System.out.println("list2==");
		for (Iterator iterator = list2.iterator(); iterator.hasNext();) {
			Pson pson = (Pson) iterator.next();
			System.out.println(pson.name);
			
		}
		/*System.out.println("list3==");
		for (Iterator iterator = list3.iterator(); iterator.hasNext();) {
			Pson pson = (Pson) iterator.next();
			System.out.println(pson.name);
			
		}*/
	}
}
