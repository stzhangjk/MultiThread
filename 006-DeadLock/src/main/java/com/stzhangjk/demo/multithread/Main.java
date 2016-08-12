package com.stzhangjk.demo.multithread;

/**
 * Created by Grady on 2016.8.12.
 */
public class Main {
    public static void main(String[] args){
        DeadThread thread = new DeadThread();
        thread.setFlag(true);
        new Thread(thread).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.setFlag(false);
        new Thread(thread).start();
    }
}
