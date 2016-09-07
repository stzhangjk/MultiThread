import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by STZHANGJK on 2016/9/7.
 */
public class Service {
    private Lock lock = new ReentrantLock();

    public void taskA(){
        try{
            lock.lock();
            System.out.println("taskA begin tName = " + Thread.currentThread().getName()
             + " time = " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("taskA end tName = " + Thread.currentThread().getName()
                    + " time = " + System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void taskB(){
        try{
            lock.lock();
            System.out.println("taskB begin tName = " + Thread.currentThread().getName()
                    + " time = " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("taskB end tName = " + Thread.currentThread().getName()
                    + " time = " + System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
