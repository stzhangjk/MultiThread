package com.stzhangjk.demo.multithread;

/**
 * Created by Grady on 2016.8.12.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadA a = new ThreadA();
        Object lock = new Object();
        a.setLock(lock);
        a.start();
        Thread.sleep(3000);
        a.interrupt();
    }
}
