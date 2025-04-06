package com.fulin.handle.impl;

import com.fulin.handle.RejectHandle;
import com.fulin.threadpool.MyCustomizeThreadPool;

/**
 * @Author: Fulin
 * @Description: 丢弃阻塞队列中任务拒绝策略
 * @DateTime: 2025/4/6 下午2:58
 **/
public class DiscardRejectHandle implements RejectHandle {
    @Override
    public void reject(Runnable rejectCommand, MyCustomizeThreadPool threadPool) {
        threadPool.blockingQueue.poll();
        threadPool.execute(rejectCommand);
    }
}
