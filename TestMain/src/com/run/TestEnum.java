package com.run;
/**
 * ����ö����
 * Java�ж��main�����Ĺ�ϵ������ 
 * ���ð�main���������ô�ر�͸��ӣ�������ͨ�ľ�̬����ûʲô���𣬣�
ͬһ��package�е����ǿ������Լ���main�����ģ�����������֮���main����Ҳ�ǿ����໥���õģ�ֻ��һ��û����ô����
Ψһ�Ĳ�ͬ������������Ϊ�������ڣ�
�����ڲ���Ҳ�ǿ�����main�����ģ�����ǰ��������ڲ��������static���ε� 

���������Ĵ����ж����ڣ���ֻ��Ҫ������ʲôʱ��Ҫ���ĸ�����Ϊ��ھͿ����ˣ�
����Ҫע����ǣ��ò�ͬ������������������Ǻ�����ص�!!!!!!!!!!!!������ ��ͬ��application������
Q:��һ����̳���һ�����,�Ӵ���̳�˫�׵�main()������?
    ����,�ǲ�����һ�����뵥Ԫ�ж����������?����һ�����뵥ԪӦ��ֻ��һ��main()������?
A:main����Ҳ�Ǹ���������Ȼ�ᱻ�̳С�
   ����ֻ����һ��main����������ֻ����һ����main������main-class����Ϊһ���������ڡ�
   ����main-class���Կ�һ�¹���jar������÷���
 *
 *
 */
public class TestEnum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IsDone.isDone.setActiveDone(true);
		System.out.println("isActiveDone:"+IsDone.isDone.isActiveDone());
		System.out.println("isActiveDone:"+IsDone.isDone.isSigninDone());
		
		try {
			Thread.sleep(5000);//��˯����
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StatusThread t2 = new StatusThread();
		t2.run();
	}
	
}
