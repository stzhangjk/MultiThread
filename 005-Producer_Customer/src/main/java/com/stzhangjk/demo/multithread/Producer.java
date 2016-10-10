package com.stzhangjk.demo.multithread;

import java.util.List;

/**
 * 生产者
 */
public class Producer {
    /**
     * 同步锁
     */
    private String lock;
    /**
     * 产品集合
     */
    private List<Product> ps;

    /**
     * 构造函数
     * @param lock 由上层传入锁对象
     * @param ps 生产的目的地，也是消费者的消费来源
     */
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
