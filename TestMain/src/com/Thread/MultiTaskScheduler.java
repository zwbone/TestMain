package com.Thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.Thread.ThreadContext;

public class MultiTaskScheduler {
	static Logger logger = Logger.getLogger(MultiTaskScheduler.class);

	// private ExecutorService execService;
	private static ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2 + 1, Runtime.getRuntime().availableProcessors() * 20, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	private List<Runnable> list = new ArrayList<Runnable>();

	public MultiTaskScheduler() {
	}

	public void add(Runnable... runner) throws NullPointerException {

		if (runner.length == 0) {
			return;
		} else {
			list.addAll(Arrays.asList(runner));
		}
	}

	public void start(boolean await) throws InterruptedException {
		start(await, ThreadContext.getThreadTimeout());
	}

	public void start(boolean await, final int timeout) throws InterruptedException {
		final CountDownLatch counter = await ? new CountDownLatch(list.size()) : null;
		final long startTime = ThreadContext.getRequestStartTime();
		final String pbqName = ThreadContext.getContextPbqName();
		final String requestClass = ThreadContext.getContextRequestClass();
		final Map<String, Object> requestSequenceMap = ThreadContext.getUserDataMap();
		for (int i = 0; i < list.size(); i++) {
			final Runnable runner = list.get(i);
			executorService.execute(new Runnable() {
				public void run() {
					ThreadContext.setThreadTimeout(timeout);
					ThreadContext.setRequestStartTime(startTime);
					ThreadContext.setRequestContext(pbqName, requestClass);
					ThreadContext.setUserDataMap(requestSequenceMap);

					runner.run();

					if (counter != null)
						counter.countDown();
				}
			});
		}
		if (await) {
			if (timeout > 0) {
				counter.await(timeout, TimeUnit.MILLISECONDS);
			} else {
				counter.await();
			}
		}

	}

	public void shutdown() {
		// execService.shutdown();
		list.clear();
	}
}