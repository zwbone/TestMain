package com.run;
/**
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
 */
public class TestEnum2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("isActiveDone2:"+IsDone.isDone.isActiveDone());
		System.out.println("isActiveDone2:"+IsDone.isDone.isSigninDone());
	}

}
