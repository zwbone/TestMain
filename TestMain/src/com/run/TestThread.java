package com.run;
/**
 * ע�⣺��Thread�������������������ж��߳��Ƿ�ͨ��interrupt��������ֹ��
 * һ���Ǿ�̬�ķ���interrupted������һ���ǷǾ�̬�ķ���isInterrupted������
 * ������������������interrupted�����жϵ�ǰ���Ƿ��жϣ���isInterrupted���������ж������߳��Ƿ��жϡ�
 * ��ˣ�while ����isInterrupted������Ҳ���Ի���while ����Thread.interrupted��������
 * @author zhangwenbao
 *
 */
public class TestThread {
	public static void main(String[] args) {
		Secondthread st = new Secondthread();
		st.exist = true;
		st.start();
		try {
			Thread.sleep(5000);
			st.exist = false;
			System.out.println("st.exist="+st.exist);
			
			Thread.sleep(10000);
			st.exist = true;
			System.out.println("st.exist="+st.exist);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		st.stop();
		st.destroy();
		st.interrupt();
		System.out.println("�̵߳ĵ�ǰ״̬�ǣ�"+st.getState());
		//�̵߳�run(){}����ִ���� �߳̾ͻ����TERMINATED״̬��Ҳ�����߳�ִ�н��������Ա������ˣ����û�б��������õĻ���
		//TERMINATED ���״̬�±�ʾ ���̵߳�run�����Ѿ�ִ�������, �����Ͼ͵���������(��ʱ����̱߳��־ó���, ���ܲ��ᱻ����)
		//�����߳�
		//1.ʹ�ñ�־λ�˳�
		//2.ʹ��interrupt()�����˳�
	}

}
class Secondthread extends Thread{
	public static volatile boolean exist = true;
	//volatile ���߳�ͬ����ͬһʱ��һ�ν�����һ���߳��޸�ֵ
	public static int couts = 0;
	@Override
	public void run(){
//		while(true){
			while(exist){
				try {
					Thread.sleep(100);
					couts++;
					System.out.println("===>>in while: "+couts+" <<==");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("===>>outer while: "+couts+" <<==");
//		}
	}
}
