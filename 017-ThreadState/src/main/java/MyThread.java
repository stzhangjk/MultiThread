/**
 * Created by STZHANGJK on 2016.10.14.
 */
public class MyThread extends Thread{
    public MyThread() {
        System.out.println("constructor:" + getState());
    }

    @Override
    public void run() {
        System.out.println("run:" + getState());
    }
}
