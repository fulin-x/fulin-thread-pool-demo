package com.fulin.handle.impl;

import com.fulin.handle.RejectHandle;
import com.fulin.threadpool.MyCustomizeThreadPool;

/**
 * @Author: Fulin
 * @Description: 抛出异常拒绝策略
 * @DateTime: 2025/4/6 下午2:56
 **/
public class ThrowRejectHandle implements RejectHandle {
    @Override
    public void reject(Runnable rejectCommand, MyCustomizeThreadPool threadPool) {
        throw new RuntimeException("阻塞队列已满！");
    }
}
