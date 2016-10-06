import org.junit.Test;
import way3.Singleton;

/**
 * Created by STZHANGJK on 2016.10.6.
 */
public class Test016 {
    @Test
    public void test() throws InterruptedException {
        Runnable r = () ->{
            System.out.println(Singleton.getInstance().hashCode());
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }


}
