package com.fulin;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: Fulin
 * @Description: 单线程线程池实现
 * @DateTime: 2025/4/6 下午1:20
 **/
public class MySingleThreadPool {

   BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(1024);

   Thread thread = new Thread(()->{
       while (true){
           try{
               Runnable command = blockingQueue.take();
               command.run();
           }catch (InterruptedException e){
               throw new RuntimeException(e);
           }
       }
   },"唯一线程");
    {
        thread.start();
    }
    void execute(Runnable command) {
        boolean offer = blockingQueue.offer(command);
    }
}
