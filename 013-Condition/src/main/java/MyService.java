import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by STZHANGJK on 2016/9/8.
 */
public class MyService {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile boolean hasValue = false;
    private String value;

    /**
     * 生产
     */
    public void set(){
        try{
            lock.lock();
            while(hasValue){
                condition.await();
            }
            value = "test data";
            System.out.println("放入" + value);
            hasValue = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 消费
     */
    public void get(){
        try{
            lock.lock();
            while(!hasValue){
                condition.await();
            }
            System.out.println("取得value = " + value);
            value = null;
            hasValue = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
