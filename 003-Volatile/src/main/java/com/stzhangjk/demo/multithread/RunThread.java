package com.stzhangjk.demo.multithread;

/**
 * Created by Grady on 2016.7.26.
 */
public class RunThread  extends Thread{

    /**volatile,强制访问公共堆栈中的值*/
    private volatile boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println("进入循环");
        while(isRunning){

        }
        System.out.println("退出循环");
    }
}
