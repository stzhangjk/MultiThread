package com.stzhangjk.demo.multithread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grady on 2016.7.27.
 */
public class Main {

    private static Customer customer;

    public static void main(String[] args){
        final List<Product> ps = new ArrayList<Product>(1);
        /*充当锁对象*/
        String lock = new String("lock");
        final Producer producer = new Producer(lock,ps);
        final Customer customer = new Customer(lock,ps);

        /*启动生产者线程*/
        new Thread(() -> producer.put()).start();

        /*启动消费者线程*/
        new Thread(()-> customer.get()).start();
    }
}
