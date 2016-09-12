package service;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by STZHANGJK on 2016.9.12.
 */
public class ReadService implements Runnable{
    private ReentrantReadWriteLock lock;

    public ReadService(ReentrantReadWriteLock lock) {
        this.lock = lock;
    }

    public void read() throws InterruptedException {
        System.out.println("reading....");
        Thread.sleep(5000);
        System.out.println("read finish");
    }

    /**
     * 共享锁
     */
    @Override
    public void run() {
        try{
            lock.readLock().lock();
            read();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }

    }
}
