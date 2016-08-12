package com.stzhangjk.demo.multithread;

/**
 * Created by Grady on 2015/11/18.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Mythread name="+Thread.currentThread().getName());
    }
}
