package com.fulin;

/**
 * @Author: Fulin
 * @Description: 主函数
 * @DateTime: 2025/4/6 下午1:20
 **/
public class Main {
    public static void main(String[] args) {
        MySingleThreadPool myThreadPool = new MySingleThreadPool();
        for (int i = 0; i < 5; i++) {
            myThreadPool.execute(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println("主线程没有被阻塞");
    }
}