package com.stzhangjk.multithread;

/**
 * Created by Grady on 2015/11/19.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {

                Thread.sleep(2000);
                System.out.println(currentThread().isInterrupted());
                //System.out.println("1:" + Thread.currentThread().getName() + Thread.interrupted());
                //System.out.println("2:" + Thread.currentThread().getName() + Thread.interrupted());
                System.out.println("i=" + i);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
