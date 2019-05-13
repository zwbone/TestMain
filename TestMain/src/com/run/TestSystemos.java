package com.run;

import java.util.Map;
/**
 * 获取当前操作系统的类型 window 和 linux
 * 表示分隔符：与系统有关的默认名称分隔符。
 * 此字段被初始化为包含系统属性 file.separator 值的第一个字符。
 * 在 UNIX 系统上，此字段的值为 ‘/’;
 * 在 Microsoft Windows 系统上，它为 ‘\’。
 * @author zhangwenbao
 *
 */
public class TestSystemos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String os=System.getenv("os");//Windows_NT
		Map<String, String> os=System.getenv();
		/*{USERPROFILE=C:\Users\zhangwenbao, ProgramData=C:\ProgramData,
				USERDNSDOMAIN=PURANG.COM, PATHEXT=.COM;
		.EXE;.BAT;.CMD;.VBS;.VBE;.JS;.JSE;.WSF;.WSH;.MSC,
		windows_tracing_logfile=C:\BVTBin\Tests\installpackage\csilogfile.log, 
				JAVA_HOME=C:\Program Files\Java\jdk1.7.0_79,
				MAVEN_HOME=E:\apache-maven-3.3.9,
				ProgramFiles(x86)=C:\Program Files (x86), 
				windows_tracing_flags=3, 
				TEMP=C:\Users\ZHANGW~1\AppData\Local\Temp, 
				SystemDrive=C:, ProgramFiles=C:\Program Files,
				Path=C:/Program Files/Java/jdk1.7.0_79/bin/../jre/bin/server;C:/Program Files/Java/jdk1.7.0_79/bin/../jre/bin;C:/Program Files/Java/jdk1.7.0_79/bin/../jre/lib/amd64;C:\Program Files\Java\jdk1.7.0_79\bin;C:\ProgramData\Oracle\Java\javapath;C:\Program Files\Java\jdk1.8.0_151\bin;C:\Program Files\Java\jdk1.8.0_101\bin;C:\Program Files\Java\jdk1.5.0_22\bin;E:\apache-maven-3.3.9\bin;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;D:\Program Files\TortoiseSVN\bin;D:\Program Files\SlikSvn\bin;C:\Program Files (x86)\Microsoft SQL Server\Client SDK\ODBC\130\Tools\Binn\;C:\Program Files (x86)\Microsoft SQL Server\140\Tools\Binn\;C:\Program Files (x86)\Microsoft SQL Server\140\DTS\Binn\;C:\Program Files (x86)\Microsoft SQL Server\140\Tools\Binn\ManagementStudio\;E:\eclipse;, HOMEDRIVE=C:, PROCESSOR_REVISION=5e03, USERDOMAIN=PURANG, ALLUSERSPROFILE=C:\ProgramData, ProgramW6432=C:\Program Files, PROCESSOR_IDENTIFIER=Intel64 Family 6 Model 94 Stepping 3, GenuineIntel, SESSIONNAME=Console, TMP=C:\Users\ZHANGW~1\AppData\Local\Temp, MAVEN_OPTS=-Xms512m -Xmx1024m, CommonProgramFiles=C:\Program Files\Common Files, CLASSPATH=.;C:\Program Files\Java\jdk1.7.0_79\jre\lib;C:\Program Files\Java\jdk1.8.0_151\jre\lib;C:\Program Files\Java\jdk1.8.0_101\jre\lib;C:\Program Files\Java\jdk1.5.0_22\jre\lib;,
				=::=::\, LOGONSERVER=\\PWPDHCAP1, PROCESSOR_ARCHITECTURE=AMD64, FP_NO_HOST_CHECK=NO,
				
				OS=Windows_NT, HOMEPATH=\Users\zhangwenbao,
				PROCESSOR_LEVEL=6, CommonProgramW6432=C:\Program Files\Common Files,
				CATALINA_HOME=E:\apache-tomcat-7.0.70, LOCALAPPDATA=C:\Users\zhangwenbao\AppData\Local,
				COMPUTERNAME=PULAN0491, windir=C:\Windows, 
				SystemRoot=C:\Windows, NUMBER_OF_PROCESSORS=4, USERNAME=zhangwenbao, 
				PUBLIC=C:\Users\Public, PSModulePath=C:\Windows\system32\WindowsPowerShell\v1.0\Modules\,
				CommonProgramFiles(x86)=C:\Program Files (x86)\Common Files,
				ComSpec=C:\Windows\system32\cmd.exe, 
				APPDATA=C:\Users\zhangwenbao\AppData\Roaming}*/
		
		
		System.out.println(os);
		
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		
		}
	
	public static boolean isWindowsOS(){
		boolean isWindowsOS = false;
	    String osName = System.getProperty("os.name");
	    if(osName.toLowerCase().indexOf("windows")>-1){
	      isWindowsOS = true;
	    }
	    return isWindowsOS;
	}

}
