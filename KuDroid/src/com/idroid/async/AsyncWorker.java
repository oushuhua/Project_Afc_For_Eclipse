package com.idroid.async;

import java.util.concurrent.Executor;

import android.os.AsyncTask;

/**
 * 异步操作类
 * 
 * @author yiyi
 */
public class AsyncWorker {

	public static final Executor sDefaultExecutor = AsyncTask.THREAD_POOL_EXECUTOR;

	private AsyncWorker() {
	}

	public static void execute(Runnable run) {
		sDefaultExecutor.execute(run);
	}

	public static void execute(DroidTask<?> task) {
		task.executeOnExecutor(sDefaultExecutor);
	}

}
