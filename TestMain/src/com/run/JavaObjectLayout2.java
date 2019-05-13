package com.run;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.util.VMSupport;
/**
 * ����Java����ķ�ʽ��һ���Ǹ�������ʵ��ֱ�Ӽ���
 * ��һ����ͨ��new��������ʵ�ʴ�С
 * ǰ��Ч�ʸߣ����ǲ�ͨ�ã����һ���������ģ�������ȷ��Ӧ�ò��?����Ч�ʵ�
 * �����ַ�ʽ��ͨ��Instrumentation��������С
 *
 */
public class JavaObjectLayout2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// ͨ��pre-agent��ȡInstrumentation����  
//	    Instrumentation instr = AgentGetter.getInstrumentation();  
//	    System.out.println(instr.getObjectSize(new Object()));
		
		System.out.println(VMSupport.vmDetails());  
        System.out.println(ClassLayout.parseClass(TestBigDecimal.class).toPrintable());  
		
	}
	
	/*ִ�н��
	Running 64-bit HotSpot VM.
	Using compressed references with 3-bit shift.
	Objects are 8 bytes aligned.
	Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
	Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]

	com.run.ddd object internals:
	 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
	      0    12        (object header)                           N/A
	     12     4        (loss due to the next object alignment)
	Instance size: 16 bytes
	Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
*/
}
