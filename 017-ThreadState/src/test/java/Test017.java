import org.junit.Test;

/**
 * Created by STZHANGJK on 2016.10.14.
 */
public class Test017 {
    /**
     * 测试NEW_RUNNABLE_TERMINATED状态
     * 输出如下：
     * constructor:NEW
     * main1:NEW
     * run:RUNNABLE
     * main2:TERMINATED
     */
    @Test
    public void testNEW_RUNNABLE_TERMINATED(){
        try{
            MyThread t = new MyThread();
            Thread.sleep(1000);
            System.out.println("main1:" + t.getState());
            t.start();
            Thread.sleep(1000);
            System.out.println("main2:" + t.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTIMED_TERMINATED(){
        try{
            MyThread t = new MyThread();
            t.start();
            Thread.sleep(1000);
            System.out.println(t.getState());
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
