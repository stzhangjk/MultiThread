package com.stzhangjk.multithread;

/**
 * Created by Grady on 2015/11/19.
 */
public class Main {
    public static void main(String[] args){
        try{
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();


            System.out.println("1:"+Thread.currentThread().getName()+Thread.interrupted());
            System.out.println("2:"+Thread.currentThread().getName()+Thread.interrupted());

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
