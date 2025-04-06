package com.fulin.handle;

import com.fulin.threadpool.MyCustomizeThreadPool;

/**
 * @Author: Fulin
 * @Description: 拒绝策略处理器
 * @DateTime: 2025/4/6 下午2:51
 **/
public interface RejectHandle {
    void reject(Runnable rejectCommand, MyCustomizeThreadPool threadPool);
}
