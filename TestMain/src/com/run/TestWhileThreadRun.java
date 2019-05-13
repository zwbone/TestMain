package com.run;

/**
 * 在Java中有两类线程：User Thread(用户线程)、Daemon Thread(守护线程)
 *Java平台把操作系统的底层进行了屏蔽，在JVM虚拟平台里面构造出对自己有利的机制，这就是守护线程的由来。Daemon的作用是为其他线程的运行提供服务，比如说GC线程。
 *User Thread线程和Daemon Thread唯一的区别之处就在虚拟机的离开，如果User Thread全部撤离，那么Daemon Thread也就没啥线程好服务的了，所以虚拟机也就退出了。
 *守护线程用户也可以自行设定，方法：public final void setDaemon(boolean flag)
 *注意点：
 *　　正在运行的常规线程不能设置为守护线程。
 *　　thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。
 *　　在Daemon线程中产生的新线程也是Daemon的（这里要和linux的区分，linux中守护进程fork()出来的子进程不再是守护进程）
 *　　根据自己的场景使用（在应用中，有可能你的Daemon Thread还没来的及进行操作时，虚拟机可能已经退出了）
 * Create By zhangwenbao
 * Date:{$Date}
 * Time:{TIME}
 * java.lang.Runtime.getRuntime().addShutdownHook(new Thread(){}) jvm退出前清理
 * 使用关闭钩子的注意事项
 * 关闭钩子本质上是一个线程（也称为Hook线程），对于一个JVM中注册的多个关闭钩子它们将会并发执行，所以JVM并不保证它们的执行顺序；由于是并发执行的，那么很可能因为代码不当导致出现竞态条件或死锁等问题，为了避免该问题，强烈建议在一个钩子中执行一系列操作。
 * Hook线程会延迟JVM的关闭时间，这就要求在编写钩子过程中必须要尽可能的减少Hook线程的执行时间，避免hook线程中出现耗时的计算、等待用户I/O等等操作。
 * 关闭钩子执行过程中可能被强制打断,比如在操作系统关机时，操作系统会等待进程停止，等待超时，进程仍未停止，操作系统会强制的杀死该进程，在这类情况下，关闭钩子在执行过程中被强制中止。
 * 在关闭钩子中，不能执行注册、移除钩子的操作，JVM将关闭钩子序列初始化完毕后，不允许再次添加或者移除已经存在的钩子，否则JVM抛出 IllegalStateException。
 * 不能在钩子调用System.exit()，否则卡住JVM的关闭过程，但是可以调用Runtime.halt()。
 * Hook线程中同样会抛出异常，对于未捕捉的异常，线程的默认异常处理器处理该异常，不会影响其他hook线程以及JVM正常退出。
 */
public class TestWhileThreadRun {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start!");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i=100000;
                while(0<i){
                    System.out.println("======>>>"+(i--)+"<<<<");
                }
            }
        };
        Thread t = new Thread(runnable);
        //t.setDaemon(true);//设为守护线程
        t.start();
        t.join();//等待t运行结束之后 在往下执行
        int activeCount = Thread.currentThread().getThreadGroup().activeCount();
        System.out.println("activeCount==="+activeCount);
        System.out.println("main end!");
        //System.exit(0);//推JVM
        //Object dd = ()->{};
        java.lang.Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                super.run();
                System.out.println("=====>JVM 退出前执行清理工作的线程！");
            }
        });
    }
}
