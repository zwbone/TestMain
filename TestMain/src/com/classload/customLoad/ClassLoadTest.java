package com.classload.customLoad;

/**
 * Create with IntelliJ IDEA
 * 虚拟机出于安全等因素考虑，不会加载< Java_Runtime_Home >/lib存在的陌生类，
 * 开发者通过将要加载的非JDK自身的类放置到此目录下期待启动类加载器加载是不可能的
 * 但在JVM中一个类用其全名和一个加载类ClassLoader的实例作为唯一标识，
 * 不同类加载器加载的类将被置于不同的命名空间.我们可以用两个自定义类加载器
 * 去加载某自定义类型（注意，不要将自定义类型的字节码放置到系统路径或者扩展路径中，
 * 否则会被系统类加载器或扩展类加载器抢先加载），
 * 然后用获取到的两个Class实例进行java.lang.Object.equals（…）判断，
 * 将会得到不相等的结果。这个大家可以写两个自定义的类加载器去加载相同的自定义类型，然后做个判断
 * 原文：https://blog.csdn.net/vernonzheng/article/details/8461380
 *
 * 假如我们自己写了一个java.lang.String的类，我们是否可以替换调JDK本身的类？
 * 答案是否定的。我们不能实现。为什么呢？
 * 我看很多网上解释是说双亲委托机制解决这个问题，其实不是非常的准确。
 * 因为双亲委托机制是可以打破的，你完全可以自己写一个classLoader来加载自己写的java.lang.String类，
 * 但是你会发现也不会加载成功，具体就是因为针对java.*开头的类，
 * jvm的实现中已经保证了必须由bootstrp来加载
 */
public class ClassLoadTest {
    public static void main(String[] args){
        try {
            System.out.println("java.class.path=="+System.getProperty("java.class.path"));
            System.out.println("java.class.classpath=="+System.getProperty("java.class.classpath"));
            Class loadedclazz = Class.forName("com.classload.customLoad.ClassLoadBean");
            System.out.println("loadedclazz classLoad=="+loadedclazz.getClassLoader());
            System.out.println("sun.misc.Launcher.getLauncher().getClassLoader()==="+sun.misc.Launcher.getLauncher().getClassLoader());

            /*
            java.class.path==C:\Program Files\Java\jdk1.7.0_79\jre\lib\charsets.jar;
            C:\Program Files\Java\jdk1.7.0_79\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\access-bridge-64.jar;
            C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\jaccess.jar;
            C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunec.jar;
            C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunmscapi.jar;
            C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jce.jar;
            C:\Program Files\Java\jdk1.7.0_79\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jfxrt.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\management- agent.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\resources.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\rt.jar;E:\IntellJ_Works\TestMain\target\classes;E:\apache-maven-3.3.9\repository\net\sf\json-lib\json-lib\2.4\json-lib-2.4-jdk15.jar;E:\apache-maven-3.3.9\repository\net\sf\ezmorph\ezmorph\1.0.6\ezmorph-1.0.6.jar;E:\apache-maven-3.3.9\repository\org\apache\commons\commons-lang3\3.1\commons-lang3-3.1.jar;E:\apache-maven-3.3.9\repository\commons-beanutils\commons-beanutils\1.8.3\commons-beanutils-1.8.3.jar;E:\apache-maven-3.3.9\repository\commons-logging\commons-logging\1.1.1\commons-logging-1.1.1.jar;E:\apache-maven-3.3.9\repository\commons-collections\commons-collections\3.2.1\commons-collections-3.2.1.jar;E:\apache-maven-3.3.9\repository\log4j\log4j\1.2.16\log4j-1.2.16.jar;E:\apache-maven-3.3.9\repository\commons-codec\commons-codec\1.8\commons-codec-1.8.jar;E:\apache-maven-3.3.9\repository\com\googlecode\json-simple\json-simple\1.1\json-simple-1.1.jar;E:\apache-maven-3.3.9\repository\org\codehaus\jackson\jackson-mapper-asl\1.9.3\jackson-mapper-asl-1.9.3.jar;E:\apache-maven-3.3.9\repository\org\codehaus\jackson\jackson-core-asl\1.9.3\jackson-core-asl-1.9.3.jar;E:\apache-maven-3.3.9\repository\commons-fileupload\commons-fileupload\1.3.1\commons-fileupload-1.3.1.jar;E:\apache-maven-3.3.9\repository\org\apache\poi\poi\3.9\poi-3.9.jar;E:\apache-maven-3.3.9\repository\org\apache\poi\poi-ooxml\3.9\poi-ooxml-3.9.jar;E:\apache-maven-3.3.9\repository\org\apache\poi\poi-ooxml-schemas\3.9\poi-ooxml-schemas-3.9.jar;E:\apache-maven-3.3.9\repository\org\apache\xmlbeans\xmlbeans\2.3.0\xmlbeans-2.3.0.jar;E:\apache-maven-3.3.9\repository\stax\stax-api\1.0.1\stax-api-1.0.1.jar;E:\apache-maven-3.3.9\repository\dom4j\dom4j\1.6.1\dom4j-1.6.1.jar;E:\apache-maven-3.3.9\repository\xml-apis\xml-apis\1.0.b2\xml-apis-1.0.b2.jar;E:\apache-maven-3.3.9\repository\org\apache\commons\commons-pool2\2.4.2\commons-pool2-2.4.2.jar;E:\apache-maven-3.3.9\repository\commons-io\commons-io\2.4\commons-io-2.4.jar;E:\apache-maven-3.3.9\repository\org\apache\velocity\velocity\1.7\velocity-1.7.jar;E:\apache-maven-3.3.9\repository\commons-lang\commons-lang\2.5\commons-lang-2.5.jar;E:\IntellJ_Works\TestMain\lib\jol-cli-0.8-full.jar;E:\IntellJ_Works\TestMain\lib\jol-core-0.3.2.jar;E:\IntellJ_Works\TestMain\lib\jol-core-0.8.jar;E:\IDEA\lib\idea_rt.jar

            java.class.classpath==null
            loadedclazz classLoad==sun.misc.Launcher$AppClassLoader@41dee0d7
            sun.misc.Launcher.getLauncher().getClassLoader()===sun.misc.Launcher$AppClassLoader@41dee0d7
            */

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
