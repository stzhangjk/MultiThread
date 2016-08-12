package com.stzhangjk.demo.multithread;

import java.util.List;

/**
 * Created by Grady on 2016.7.27.
 * 生产者
 */
public class Producer {
    /**
     * 同步锁
     */
    private String lock;
    /**
     * 商品集合
     */
    private List<Product> ps;

    public Producer(String lock, List<Product> ps) {
        this.lock = lock;
        this.ps = ps;
    }

    /**
     * 生产
     */
    public void put() {
        try {
            synchronized (lock) {
                while (true) {
                    if (ps.size() != 0) {
                        /*等待，阻塞*/
                        lock.wait();
                    } else {
                        Thread.sleep(1000);
                        ps.add(new Product(String.valueOf(System.currentTimeMillis())));

                        /*唤醒消费者线程*/
                        lock.notify();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
