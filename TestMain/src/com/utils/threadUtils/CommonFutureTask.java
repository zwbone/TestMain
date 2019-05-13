package com.utils.threadUtils;

import net.sf.json.JSONObject;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Create with IntelliJ IDEA
 * Create By zhangwenbao
 * Date:{$Date}
 * Time:{TIME}
 */
public class CommonFutureTask extends FutureTask {

    public CommonFutureTask(Callable callable) {
        super(callable);
    }

    public CommonFutureTask(Runnable runnable, JSONObject result) {
        super(runnable, result);
        result.put("success",false);
        result.put("taskName=",Thread.currentThread().getName());
    }
    //@Override
}
