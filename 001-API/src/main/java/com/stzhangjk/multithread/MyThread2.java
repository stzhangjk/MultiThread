package com.stzhangjk.multithread;

/**
 * Created by Grady on 2015/11/18.
 */
public class MyThread2 implements Runnable{
    public void run() {
        System.out.printf("Mythread2 name=" + Thread.currentThread().getName());
    }
}
