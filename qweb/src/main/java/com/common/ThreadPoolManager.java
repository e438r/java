package com.common;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理器
 * @author kang.chen
 * @since 2015年11月4日
 */
@Component
public class ThreadPoolManager {

	private ThreadPoolExecutor executor;

	/**
	 * 核心线程数
	 */
	private static final Integer CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

	/**
	 * 最大线程数
	 */
	private static final Integer MAX_POOL_SIZE = CORE_POOL_SIZE * 2;
    /**
     * 队列长度
     */
    private int QUEUE_SIZE = 1000;

	/**
	 * 存活时间
	 */
	private long keepAliveTime = 0;

	public ThreadPoolManager() {
		this.executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(QUEUE_SIZE), new AbortPolicy());
	}

	public void execute(Runnable command) {
		executor.execute(command);
	}

	public <T> Future<T> submitTask(Callable<T> callable) {
		return executor.submit(callable);
	}


	/**
	 * 设置核心线程个数
	 * 
	 * @param corePoolSize
	 */
	public void setCorePoolSize(int corePoolSize) {
		if (null != executor) {
			executor.setCorePoolSize(corePoolSize);
		}
	}

	/**
	 * 设置最大线程个数
	 * 
	 * @param maxPoolSize
	 */
	public void setMaxPoolSize(int maxPoolSize) {
		if (null != executor) {
			executor.setMaximumPoolSize(maxPoolSize);
		}
	}


	public ThreadPoolExecutor getExecutor() {
		return executor;
	}

	public void setExecutor(ThreadPoolExecutor executor) {
		this.executor = executor;
	}
	

	
}
