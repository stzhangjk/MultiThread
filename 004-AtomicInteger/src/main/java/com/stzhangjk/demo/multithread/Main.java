package com.stzhangjk.demo.multithread;

/**
 * Created by Grady on 2016.7.26.
 */
public class Main {
    public static void main(String[] args){
        for(int i=0;i<5;i++){
            AddCountThread thread = new AddCountThread();
            thread.start();
        }


    }
}
