package com.utils.threadUtils;

import java.util.concurrent.CountDownLatch;

/**
 * Create with IntelliJ IDEA
 * Create By zhangwenbao
 * Date:{$Date}
 * Time:{TIME}
 */
public class CommonRunnable implements Runnable {

    private CountDownLatch latch;
    private long timeout;

    public CommonRunnable(CountDownLatch latch,long timeout){
        this.latch=latch;
        this.timeout=timeout;
    }

    @Override
    public void run() {
        System.out.println("===="+Thread.currentThread().getName());
        latch.countDown();
        System.out.println("getCount===="+latch.getCount());
    }
}
