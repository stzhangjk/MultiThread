package com.stzhangjk.demo.multithread;

/**
 * Created by Grady on 2016.8.12.
 */
public class ThreadA extends Thread{
    private Object lock;

    @Override
    public void run() {
        try{
            synchronized (lock){
                System.out.println("begin wait()...");
                lock.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("wait时被interrupt()");
        }
    }

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }
}
