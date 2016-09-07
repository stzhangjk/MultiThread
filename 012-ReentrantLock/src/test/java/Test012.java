import org.junit.Test;

/**
 * Created by STZHANGJK on 2016/9/7.
 */
public class Test012 {
    @Test
    public void test() throws InterruptedException {
        final Service service = new Service();

        for(int i=0;i<2;i++){
            Thread a = new Thread(() -> {
                service.taskA();
            });
            a.start();
            a.join();
        }

        for(int i=0;i<2;i++){
            Thread b = new Thread(() -> {
                service.taskB();
            });
            b.start();
            b.join();
        }
    }
}
