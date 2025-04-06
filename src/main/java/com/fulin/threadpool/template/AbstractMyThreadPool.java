package com.fulin.threadpool.template;

/**
 * @Author: Fulin
 * @Description: 自定义线程池模板
 * @DateTime: 2025/4/6 下午3:10
 **/
public abstract class AbstractMyThreadPool {
     public abstract void execute(Runnable command);
}
