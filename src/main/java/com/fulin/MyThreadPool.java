package com.fulin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Fulin
 * @Description: TODO
 * @DateTime: 2025/4/6 下午2:20
 **/
public class MyThreadPool {

    // 核心线程数
    private int corePoolSize = 10;

    // 最大线程数量
    private int maxSize = 16;

    private int timeout = 1;

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    // 阻塞队列保存任务
    private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(1024);

    public MyThreadPool() {
    }

    public MyThreadPool(int corePoolSize, int maxSize, int timeout, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        this.corePoolSize = corePoolSize;
        this.maxSize = maxSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.blockingQueue = blockingQueue;
    }


    // 核心线程列表
    List<Thread> coreList = new ArrayList<>();

    // 临时线程列表
    List<Thread> supportList = new ArrayList<>();

    // 判断coreList中有多少个元素，没有到corePoolSize就创建线程
    // TODO 存在线程安全问题
    void execute(Runnable command) {
        if (coreList.size() < corePoolSize) {
            Thread thread = new CoreThread();
            coreList.add(thread);
            thread.start();
        }
        // 将任务添加到阻塞队列中
        if (blockingQueue.offer(command)) {
            return;
        }
        // 若阻塞队列满，则创建临时线程
        if (coreList.size() + supportList.size() < maxSize) {
            Thread thread = new SupportThread();
            supportList.add(thread);
            thread.start();
        }
        if (!blockingQueue.offer(command)) {
            throw new RuntimeException("阻塞队列已满！");
        }
    }

    class CoreThread extends Thread {
        @Override
        public void run() {
            // 核心线程执行阻塞队列中的任务
            while (true) {
                try {
                    // 从阻塞队列中获取任务并执行
                    Runnable command = blockingQueue.take();
                    command.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class SupportThread extends Thread {
        @Override
        public void run() {
            // 临时线程执行阻塞队列中的任务
            while (true) {
                try {
                    // 从阻塞队列中获取任务并执行
                    Runnable command = blockingQueue.poll(timeout, timeUnit);
                    if (command == null) {
                        break;
                    }
                    command.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + "临时线程结束了!");
        }
    }
}
