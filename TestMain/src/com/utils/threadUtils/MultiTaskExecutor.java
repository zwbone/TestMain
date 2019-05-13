package com.utils.threadUtils;

import java.util.List;
import java.util.concurrent.*;

/**
 * Create with IntelliJ IDEA
 * Date:{$Date}
 * Time:{TIME}
 */
public class MultiTaskExecutor {

    List<FutureTask> taskList;
    CountDownLatch countDownLatch;
    long TIMEOUT=30000;
    boolean a_wait;

    public MultiTaskExecutor(List taskList,CountDownLatch countDownLatch){
        this.taskList=taskList;
        this.countDownLatch=countDownLatch;
    }

    public void start(){
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(taskList.size());
            for (FutureTask task:taskList) {
                executorService.execute(task);
            }
            if(a_wait){
                countDownLatch.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void shutDown(){}
}
