package com.stzhangjk.demo.multithread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grady on 2016.7.27.
 */
public class Main {
    public static void main(String[] args){
        final List<Product> ps = new ArrayList<Product>(1);
        String lock = new String("lock");
        final Producer producer = new Producer(lock,ps);
        final Customer customer = new Customer(lock,ps);

        new Thread(new Runnable() {
            public void run() {
                producer.put();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                customer.get();
            }
        }).start();
    }
}
