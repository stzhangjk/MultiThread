package com.stzhangjk.demo.multithread;

import java.util.List;

/**
 * Created by Grady on 2016.7.27.
 * 消费者
 */
public class Customer {
    /**
     * 同步锁
     */
    private String lock;
    /**
     * 商品集合
     */
    private List<Product> ps;

    public Customer(String lock, List<Product> ps) {
        this.lock = lock;
        this.ps = ps;
    }

    /**
     * 消费
     */
    public void get() {
        try {
            synchronized (lock){
                while (true) {
                    if (ps.size() == 0) {
                        lock.wait();
                    }
                    System.out.println(ps.get(0).getData());
                    ps.remove(0);
                    lock.notify();
                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
