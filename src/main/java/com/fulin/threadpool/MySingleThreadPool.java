package com.fulin.threadpool;

import com.fulin.threadpool.template.AbstractMyThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: Fulin
 * @Description: 单线程线程池实现
 * @DateTime: 2025/4/6 下午1:20
 **/
public class MySingleThreadPool extends AbstractMyThreadPool {

    // 阻塞队列保存任务
    BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(1024);

    Thread thread = new Thread(() -> {
        while (true) {
            try {
                // 从阻塞队列中获取任务并执行
                Runnable command = blockingQueue.take();
                command.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }, "唯一线程");

    {
        // 启动线程
        thread.start();
    }

    public void execute(Runnable command) {
        // 将任务添加到阻塞队列中
        boolean offer = blockingQueue.offer(command);
    }
}
