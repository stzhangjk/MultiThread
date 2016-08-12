package com.stzhangjk.demo.multithread;

/**
 * Created by Grady on 2016.8.12.
 */
public class DeadThread implements Runnable{

    private Object lock1;
    private Object lock2;
    private boolean flag;

    public DeadThread(){
        lock1 = new Object();
        lock2 = new Object();
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if(flag){
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName() + "get lock1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + "get lock2");
                }
            }
        }else{
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + "get lock2");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName() + "get lock1");
                }
            }
        }
    }
}
