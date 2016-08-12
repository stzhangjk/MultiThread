package com.stzhangjk.demo.multithread;

/**
 * Created by Grady on 2015/11/18.
 */
public class Main {
    public static void main(String[] args){
        //继承Thread类
        new MyThread().start();
        //实现Runnable接口
        new Thread(new MyThread2()).start();
        Thread.interrupted();
    }
}
