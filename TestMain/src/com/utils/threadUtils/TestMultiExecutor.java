package com.utils.threadUtils;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Create with IntelliJ IDEA
 * Create By zhangwenbao
 * Date:{$Date}
 * Time:{TIME}
 */
public class TestMultiExecutor {

    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(6);
        long timeout = 10000;
        List<CommonFutureTask> list = new ArrayList<>();
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        JSONObject json3 = new JSONObject();
        JSONObject json4 = new JSONObject();
        JSONObject json5 = new JSONObject();
        JSONObject json6 = new JSONObject();
        CommonRunnable cr1 = new CommonRunnable(latch,timeout);
        CommonRunnable cr2 = new CommonRunnable(latch,timeout);
        CommonRunnable cr3 = new CommonRunnable(latch,timeout);
        CommonRunnable cr4 = new CommonRunnable(latch,timeout);
        CommonRunnable cr5 = new CommonRunnable(latch,timeout);
        CommonRunnable cr6 = new CommonRunnable(latch,timeout);
        //
        CommonFutureTask task1 = new CommonFutureTask(cr1,json1);
        CommonFutureTask task2 = new CommonFutureTask(cr2,json2);
        CommonFutureTask task3 = new CommonFutureTask(cr3,json3);
        CommonFutureTask task4 = new CommonFutureTask(cr4,json4);
        CommonFutureTask task5 = new CommonFutureTask(cr5,json5);
        CommonFutureTask task6 = new CommonFutureTask(cr6,json6);
        list.add(task1);
        list.add(task2);
        list.add(task3);
        list.add(task4);
        list.add(task5);
        list.add(task6);
        MultiTaskExecutor executor = new MultiTaskExecutor(list,latch);
        executor.start();
        System.out.println(json1.optString("taskName=")+json1.optBoolean("success"));
        System.out.println(json2.optString("taskName=")+json2.optBoolean("success"));
        System.out.println(json3.optString("taskName=")+json3.optBoolean("success"));
        System.out.println(json4.optString("taskName=")+json4.optBoolean("success"));
        System.out.println(json5.optString("taskName=")+json5.optBoolean("success"));
        System.out.println(json6.optString("taskName=")+json6.optBoolean("success"));
    }
}
