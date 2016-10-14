/**
 * Created by STZHANGJK on 2016.10.14.
 */
public class MyThread extends Thread{
    public MyThread() {
        System.out.println("constructor:" + getState());
    }

    @Override
    public void run() {
        try {
            System.out.println("run:" );
            System.out.println("before sleep" + getState());
            System.out.println("sleeping");
            Thread.sleep(10000);
            System.out.println("end sleep" + getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
