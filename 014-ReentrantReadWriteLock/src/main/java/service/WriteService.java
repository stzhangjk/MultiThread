package service;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by STZHANGJK on 2016.9.12.
 */
public class WriteService implements Runnable{
    private ReentrantReadWriteLock lock;

    public WriteService(ReentrantReadWriteLock lock) {
        this.lock = lock;
    }

    public void write() throws InterruptedException {
        System.out.println("writing....");
        Thread.sleep(5000);
        System.out.println("write finish");
    }

    /**
     * 排他锁
     */
    @Override
    public void run() {
        try{
            lock.writeLock().lock();
            write();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
}
