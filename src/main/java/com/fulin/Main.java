package com.fulin;

import com.fulin.handle.impl.DiscardRejectHandle;
import com.fulin.threadpool.MyCustomizeThreadPool;
import com.fulin.threadpool.MySingleThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Fulin
 * @Description: 主函数
 * @DateTime: 2025/4/6 下午1:20
 **/
public class Main {
    public static void main(String[] args) {
        // 单线程线程池测试
        /*MySingleThreadPool myThreadPool = new MySingleThreadPool();
        for (int i = 0; i < 5; i++) {
            myThreadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println("主线程没有被阻塞");*/

        // 自定义线程池测试
        MyCustomizeThreadPool myCustomizeThreadPool = new MyCustomizeThreadPool(2,4,1,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(2),
                new DiscardRejectHandle());
        for (int i = 0; i < 6; i++) {
            final int fi = i;
            myCustomizeThreadPool.execute(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+":task-"+ fi);
            });
        }

        System.out.println("主线程没有被阻塞");
    }
}